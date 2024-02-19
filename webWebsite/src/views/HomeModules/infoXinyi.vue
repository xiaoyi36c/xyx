<template>
    <div class="indexBox">
        <div class="cont">
            <div class="marb15" v-for="(v,i) in list" >
                <div class="title">
                    {{v.title}}
                    <span>/{{v.info}}</span>
                    <i></i>
                </div>
                <listItem :item="v" :len="list.length" :ItemIndex="i+1"  :key="i"/>
            </div>
        </div>
    </div>
</template>
<script>
import listItem from "@/components/listBox/infoXinyiItem.vue";
export default {
    components: {
        listItem,
    },
    data() {
        return {
            list: [
                    {
                        title:'信易学+政务',
                        info:'为信用良好机构提供简便政务行政审批、优先审批等便捷服务',
                        cont:'依托信用信息共享平台，在保护个人信息安全前提下，将信用信息查询嵌入各级政府部门的审批流程，提升行政审批部门工作效率，让诚信主体享受优先办理、简化手续、容缺受理、一网通办等便捷审批服务措施，深入推进审批服务便民'
                    }, 
                    {
                        title:'信易学+金融',
                        info:'为信用良好机构提供金融服务',
                        cont:'支持金融机构创新开发“信易学+金融”产品和服务，优化信贷审批流程，降低运营管理成本，提高贷款发放效率和服务便利程度，有效缓解银企信息不对称、中小微企业融资难融资贵问题，提高金融服务实体经济质效',
                    }, 
                    {
                        title:'信易学+消费',
                        info:'为信用良好机构提供消费激励服务',
                        cont:'致力于倡导向守信者提供更便捷更实惠的消费激励服务。主要措施包括享受更优惠的租金折扣，或更长久的的租赁期限，降低企业经营成本，实现“信用越好，租赁越容易”'
                    }]
        }
    }
}
</script>
<style lang="scss" scoped>
.indexBox {
    width: 100%;
    margin-top: 15px;
    background: #fff;
    .cont{
        display: inline-flex;
        width:100%;
        padding-bottom: 50px;
        flex-wrap: wrap;
        justify-content: space-between;
        .marb15{
            width:calc(50% - 15px);
            margin-bottom: 10px;
            .title{
                width:100%;
                border-bottom: 1px solid #f2f2f2;
                text-align: left;
                font-weight: 400;
                color: #333333;
                line-height: 50px;
                margin-bottom: 15px;
                position: relative;
                font-size: 18px;
                font-weight: 600;
                color: #333333;
                span{
                    color: #3994FF;
                    font-size:14px;
                }
                i{
                    height: 3px;
                    background: #3994FF;
                    width: 30px;
                    display: inline-block;
                    position: absolute;
                    left: 0;
                    bottom: -2px;
                }
            }
        }
    }

}
</style>