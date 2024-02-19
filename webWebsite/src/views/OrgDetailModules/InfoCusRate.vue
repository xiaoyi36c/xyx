<template>
    <div class="Box">
        <div class="industyBox">
            <div class="industyItem" :class="actKey===i?'act':''" v-for="(item, i) in options" :key="i" @click="tap(i,item)">
                {{ item.title }}
            </div>
        </div>
        <template>
            <component :is="path"></component>
        </template>
    </div>
</template>
<script >
import {
    teacher,
    lesson,
    rate,
    tousu
} from '@/components/CusRate'
export default {
    components: {
        teacher,
        lesson,
        rate,
        tousu,
    },
    data() {
        return {
            path:teacher,
            actKey: 0,
            typeIndusty: 0,//行业选择
            areaKey: 0,//选中的区域
            // options: [{ title: '师资介绍',path:teacher }, { title: '课程介绍',path:lesson }, { title: '学员评价',path:rate }, { title: '投诉',path:tousu }]
            options: [{ title: '师资介绍',path:teacher }, { title: '课程介绍',path:lesson }, { title: '学员评价',path:rate }, { title: '投诉',path:tousu }]
        }
    },
    methods:{
        tap(i,item){
            this.actKey=i;
            this.path = item.path
        }
    }
}

</script>
<style lang="scss" scoped>
.Box {
    background: #fff;
    border-radius: 5px;
    padding: 15px 0;
}

.industyBox {
    display: inline-flex;
    width: 100%;
    position: relative;

    .industyItem {
        padding: 8px 15px;
        border: 1px solid #f2f2f2;
        font-size: 12px;
        box-sizing: border-box;

        span {
            font-size: 14px;
            font-weight: bold;
            color: #333333;
            display: block;
            text-align: center;
        }

        &:last-child {
            margin-right: 0;
        }

        &:hover {
            cursor: pointer;
        }
    }

    .act {
        transition: all .3s;
        font-weight: 500;
        color: #3994FF;
        // border: 1px solid #3994FF;
    }
}</style>