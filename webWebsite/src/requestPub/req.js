import axios from 'axios';
import QS from 'qs';
import router from '../router'
import { Message ,Loading} from 'element-ui';
import store from '../store/index'

// 环境的切换
// if (process.env.NODE_ENV == 'development') {
// //    axios.defaults.baseURL = 'https://lawapi.lawdoo.com';
// //    axios.defaults.baseURL = 'http://192.168.31.240:8083';
//     axios.defaults.baseURL = 'http://47.97.3.24:8083';
//     // axios.defaults.baseURL  = 'https://mpzjcs.lawdoo.com/Manger';
// } else if (process.env.NODE_ENV == 'debug') {
//     axios.defaults.baseURL = '';
// } else if (process.env.NODE_ENV == 'production') {
//     // axios.defaults.baseURL = 'https://mpzjcs.lawdoo.com/Manger';
//     axios.defaults.baseURL = 'http://47.97.3.24:8083';
//     //   axios.defaults.baseURL = 'https://lawapi.lawdoo.com';
//     // axios.defaults.baseURL = 'http://192.168.31.232:1996/';
// }
// axios.defaults.baseURL = '/test';
// 请求超时时间
axios.defaults.timeout = 10000;
axios.defaults.baseURL = process.env.VUE_APP_BASE_API;
// post请求头
axios.defaults.headers.post['Accept'] = '*/*'; 
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8;';
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;multipart/form-data;charset=UTF-8;';
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8;';
axios.defaults.headers.post['Content-Type'] = 'application/json';
// axios.defaults.headers.post['Access'] = '*';
let loading;
// 请求拦截器
axios.interceptors.request.use(
    config => {
        // 每次发送请求之前判断是否存在token，如果存在，则统一在http请求的header都加上token，不用每次请求都手动添加了
        // 即使本地存在token，也有可能token是过期的，所以在响应拦截器中要对返回状态进行判断
        const token = store.state.token;
        token && (config.headers.Token = token);
        // config.headers.Authorization = token
        // console.log(store.state.token,config)
        loading =   Loading.service({
            lock: true,
            text: '加载中……',
            background: 'rgba(255, 255, 255, 0.7)'
          });
        return config;
    },
    error => {
        return Promise.error(error);
    })

// 响应拦截器
axios.interceptors.response.use(
    response => {
        loading.close()
        // console.log('response===',response)
        if (response.status == 200) {   //服务器返回状态码         
          if(response.data.code == 200 || response.data.code==0){   //接口返回状态码
            return Promise.resolve(response);
          }else if(response.data.code == 202){  //202 登录状态已过期
            Message.error({message:response.data.Msg});
            setTimeout(()=>{
                router.replace({
                    path: '/login',
                    query: {
                        redirect: router.currentRoute.fullPath
                    }
                });
            },1500)
          }else if(response.data.code == 400 || response.data.code == 201 || response.data.code == 203  ){
            Message.error({message:response.data.Msg});
          }else{
            Message.error({message:response.data.Msg});
          }
        }
        // else{
        //     return Promise.reject(response);
        // }
    },
    // 服务器状态码不是200的情况
    error => {
        loading.close()
        // console.log('eee',error.response.status)
        if (error.response.status) {           
            switch (error.response.status) {
                //其他状态码   自定义的状态码
                // 404请求不存在
                case 404:
                    Message.error({message:'请求不存在'});
                break;
                case 401:
                    Message.error({message:error.response.data.Message});
                    setTimeout(()=>{
                        router.replace({
                            path: '/login',
                            query: {
                                redirect: router.currentRoute.fullPath
                            }
                        });
                    },1500)
                break;
                // 其他错误，直接抛出错误提示
                default:
                // error.response.data.message
                Message.error({message:'系统错误,请稍后再试(或者联系管理员)'});
                   
            }
            return Promise.reject(error.response);
        }else{
            Message.error({message:'系统错误,请稍后再试(或者联系管理员)'});
        }
    }
);
/**
 * get方法，对应get请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function get(url, params){
    return new Promise((resolve, reject) =>{
        // const loading =   Loading.service();
        axios.get(url, {
            params: params
        })
        .then(res => {
            // loading.close();
            resolve(res.data);
        })
        .catch(err => {
            // loading.close();
            reject(err.data)
        })
    });
}
/**
 * post方法，对应post请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function post(url, params) {
    return new Promise((resolve, reject) => {
        // const loading =   Loading.service();
        // QS.stringify(params)
        // JSON.stringify(params)
        axios.post(url, JSON.stringify(params))
        .then(res => {
            // loading.close();
            resolve(res.data);
        })
        .catch(err => {
            // loading.close();
            reject(err.data)
        })
    });
}
