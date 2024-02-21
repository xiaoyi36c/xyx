package cn.wolfcode.business.manage.credit.controller;

import cn.wolfcode.business.manage.credit.domain.CelOrgCredit;
import cn.wolfcode.business.manage.credit.domain.vo.CelOrgCreditVO;
import cn.wolfcode.business.manage.credit.service.ICelOrgCreditService;
import cn.wolfcode.common.annotation.Log;
import cn.wolfcode.common.core.controller.BaseController;
import cn.wolfcode.common.core.domain.AjaxResult;
import cn.wolfcode.common.core.page.TableDataInfo;
import cn.wolfcode.common.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 机构异常信息Controller
 * 
 * @author Bycor
 * @date 2024-02-20
 */
@RestController
@RequestMapping("/manage/credit")
public class CelOrgCreditController extends BaseController
{
    @Autowired
    private ICelOrgCreditService celOrgCreditService;

    /**
     * 查询机构异常信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(CelOrgCredit celOrgCredit)
    {
        startPage();
        List<CelOrgCreditVO> list = celOrgCreditService.selectCelOrgCreditList(celOrgCredit);
        return getDataTable(list);
    }


    /**
     * 获取机构异常信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(celOrgCreditService.selectCelOrgCreditById(id));
    }

    /**
     * 新增机构异常信息
     */
    @Log(title = "机构异常信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CelOrgCredit celOrgCredit)
    {
        return toAjax(celOrgCreditService.insertCelOrgCredit(celOrgCredit));
    }

    /**
     * 修改机构异常信息
     */
    @Log(title = "机构异常信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CelOrgCredit celOrgCredit)
    {
        return toAjax(celOrgCreditService.updateCelOrgCredit(celOrgCredit));
    }

    /**
     * 删除机构异常信息
     */
    @Log(title = "机构异常信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(celOrgCreditService.deleteCelOrgCreditByIds(ids));
    }
}
