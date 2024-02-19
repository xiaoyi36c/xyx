export const ListMinxins = {
    data() {
        return {
            pagination:{
                pageSize:10,
                total:0,
                currentPage:1,
                pageSizes:[10, 20, 30, 40, 50, 100],
                layout:"prev, pager, next" 
            }
        }
    },
    mounted() {
        
    },
    methods: {
        currentChange(e){
            this.pagination.currentPage= e
            this.initData()
            document.getElementsByClassName('Box')[0].scrollIntoView();
        }
    },
}
