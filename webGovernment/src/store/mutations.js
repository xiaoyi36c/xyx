import router from "../router";

export default{
    UpLoginStates(state , currTypes){
        return new Promise((resolve, reject) => {
            state.isLogin = window.sessionStorage.getItem('isLogin');
            state.LoginInfo.uName = window.sessionStorage.getItem('uName');
            state.token = window.sessionStorage.getItem('utoken');
            resolve()
        })
    },
    LoginOut(state){       
         window.sessionStorage.clear();
         state.isLogin = window.sessionStorage.getItem('isLogin');
         state.LoginInfo.uName = window.sessionStorage.getItem('uName');
         state.token = window.sessionStorage.getItem('utoken');
    }
}