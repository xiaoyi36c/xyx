<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="创建时间" prop="createdTime">
        <el-date-picker clearable
                        v-model="queryParams.createdTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="机构名字" prop="organizationName">
        <el-input
          v-model="queryParams.params.organizationName"
          placeholder="请输入机构名字"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['manage:credit:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['manage:credit:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['manage:credit:remove']"
        >删除
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="creditList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>

      <el-table-column label="机构名字" align="center" prop="organizationName"/>
      <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="事件时间" align="center" prop="eventData" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.eventData, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="事件类型" align="center" prop="eventType"/>
      <el-table-column label="事件描述" align="center" prop="eventDescribe"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manage:credit:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manage:credit:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改机构异常信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="机构名字" prop="organizationName">
          <el-autocomplete
            class="inline-input"
            v-model="form.organizationName"
            :fetch-suggestions="querySearch"
            placeholder="请输入机构名字"
            :trigger-on-focus="false"
            @select="handleSelect"
          ></el-autocomplete>
        </el-form-item>
        <el-form-item label="事件时间" prop="eventData">
          <el-date-picker
            v-model="form.eventData"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择事件时间"
          />
        </el-form-item>
        <el-form-item label="事件类型" prop="eventType">
          <el-select v-model="form.eventType" clearable placeholder="请输入事件类型">
            <el-option label="劳动纠纷" value="劳动纠纷"/>
            <el-option label="财务纠纷" value="财务纠纷"/>
            <el-option label="经营异常" value="经营异常"/>
            <el-option label="行政处罚" value="行政处罚"/>
            <el-option label="官司纠纷" value="官司纠纷"/>
          </el-select>
        </el-form-item>
        <el-form-item label="事件描述" prop="eventDescribe">
          <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" v-model="form.eventDescribe" clearable
                    placeholder="请输入事件描述"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {listCredit, getCredit, delCredit, addCredit, updateCredit, selectOrgName} from "@/api/business/credit/credit";

export default {
  name: "Credit",
  data() {
    return {
      re:[],
      restaurants: [],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 机构异常信息表格数据
      creditList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        createdTime: null,
        params: {
          organizationName: null,
        }
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询机构异常信息列表 */
    getList() {
      this.loading = true;
      listCredit(this.queryParams).then(response => {
        this.creditList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        restaurants: [],
        id: null,
        version: null,
        createdBy: null,
        createdByName: null,
        createdTime: null,
        updatedBy: null,
        updatedByName: null,
        updatedTime: null,
        deleted: null,
        orgId: null,
        eventData: null,
        eventType: null,
        eventDescribe: null,
        sourceType: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加机构异常信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCredit(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改机构异常信息";
      });
    },
    querySearch(queryString, cb) {
      console.log(queryString+"---");
      selectOrgName(queryString).then(response => {
        this.restaurants = response.data;
      });
      let restaurants = this.restaurants;
      let results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilter(queryString) {
      return (restaurant) => {
        return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCredit(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCredit(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除机构异常信息编号为"' + ids + '"的数据项？').then(function () {
        return delCredit(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('manage/credit/export', {
        ...this.queryParams
      }, `credit_${new Date().getTime()}.xlsx`)
    }
  },

};
</script>
