<template>
    <div class="Box">
        <div class="industyBox">
            <div class="industyItem" :class="actKey === i ? 'act' : ''" v-for="(item, i) in options" :key="i"
                @click="tap(i, item)">
                {{ item.value }}({{item.countNum || 0}})
            </div>
        </div>
        <div class="content">
            <table class="table" border="0" cellpadding="0" cellspacing="0" v-for="(v, i) in list" :key="i">
                <tr v-for="(item, idx) in v" :key="idx">
                    <td>{{ idx }}</td>
                    <td>{{ item }}</td>
                </tr>
            </table>
        </div>
    </div>
</template>
<script >

export default {
    props:{
        options:{
            type:Array,
            default:()=> []
        },
        list:{
            default:Array,
            default:()=>[]
        }
    },
    components: {

    },
    data() {
        return {
            actKey: 0,

        }
    },
    methods: {
        tap(i, item) {
            this.actKey = i;
            this.$emit('change',item.key)
        }
    }
}

</script>
<style lang="scss" scoped>
.content{
    margin-top: 20px;
}
.table {
    width: 100%;
    margin-bottom: 20px;

    tr {
        height: fit-content;

        td {
            border: 1px solid #f2f2f2;
            box-sizing: border-box;
            padding:10px 15px;
        }

        td:nth-child(1) {
            width: 30%;
            color: #333333;
            font-size: 14px;
            font-weight: bold;
            background: #F7F7F7;
        }

        td:nth-child(2) {
            color: #333333;
            font-size: 13px;
        }
    }
}

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
        border: 1px solid #3994FF;
        color: #fff;
        background: #3994FF;
    }
}</style>