module.exports ={
    publicPath:process.env.NODE_ENV === 'production'
    ? './'
    : '/',
    devServer:{
      port:'9797',
      proxy: {
        [process.env.VUE_APP_BASE_API]: {
          // target: `https://glm.frp.leeg.cc/`,
            target: `http://localhost:8698/`,
          changeOrigin: true,
          pathRewrite: {
           // ['^' + process.env.VUE_APP_BASE_API]: ''
              "^/dev-api": ""
          }
        }
      },
    }
}

