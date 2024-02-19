module.exports ={
    publicPath:process.env.NODE_ENV === 'production'
    ? './'
    : '/',
    devServer:{
      port:'9797',
      proxy: {
        [process.env.VUE_APP_BASE_API]: {
          target: `https://glm.frp.leeg.cc/`,
          changeOrigin: true,
          // pathRewrite: {
          //   ['^' + process.env.VUE_APP_BASE_API]: ''
          // }
        }
      },
    }
}

