export default{
    token:window.sessionStorage.getItem('utoken') || '', 
    isLogin:window.sessionStorage.getItem('isLogin') || false,
    LoginInfo:{
        uName:window.sessionStorage.getItem('uName') || '',//当前登录用户名
    }
}