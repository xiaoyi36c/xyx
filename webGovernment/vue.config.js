module.exports ={
    publicPath:process.env.NODE_ENV === 'production'
    ? './'
    : '/',
    devServer:{
      port:'9797',
      /*proxy: {
        "/dev-api": {
          target: "http://192.168.31.30:8080/",//本地调试地址
          // target: "http://xyxadmin.frp.leeg.cc/",//测试地址
          changeOrigin: true,
          pathRewrite:{
            '^/dev-api':''
          }
        },
        "/prod-api": {
          // target: "http://192.168.31.30:8080/",//本地调试地址
          target: "http://xyxadmin.frp.leeg.cc/",//测试地址
          changeOrigin: true,
          pathRewrite:{
            '^/prod-api':''
          }
        },
      },*/
      proxy: {
        [process.env.VUE_APP_BASE_API]: {
          // target: `http://192.168.31.54:8080`,
          // target: `https://yyy.frp.leeg.cc`,
         // target: "https://xyxadmin.frp.leeg.cc/prod-api",//测试地址
          // target: `https://op.lorisai.cn/`,//生产
            target: `http://localhost:8698/`,
          changeOrigin: true,
          pathRewrite: {
            // ['^' + process.env.VUE_APP_BASE_API]: ''
              '^/dev-api':''
          }
        }
      },
      disableHostCheck: true
    }
}
