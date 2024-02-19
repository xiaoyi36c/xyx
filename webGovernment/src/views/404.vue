<template>
    <div class="box">
        <img :src="require('@/assets/404.png')" />
    </div>
</template>
<script>
    export default {
        name: "NF404",
        data() {
            return {
                countDown: 3
            }
        },
        methods: {
            goHome() {
                this.$router.push('/')
            }
        },
        created() {
            // setTimeout(() => {
            //     this.$router.push('/')
            // }, 3000)
        }
    }
</script>

<style lang="scss" scoped>
.box{
    display: inline-flex;
    width: 100%;
    align-items: center;
    justify-content: center;
    height: calc(100% - 60px);
    background-color: #fff;
}
</style>