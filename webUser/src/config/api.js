import * as common from './common.js' //引入common
import * as db from './db.js' //引入db
import {
	Interface
} from './Interface.js' //引入db

// export let domain = '/';
// export let domain = 'https://yyy.frp.leeg.cc';
// export let domain = 'https://jiuxia.frp.leeg.cc';
// export let domain = 'http://192.168.31.43:8081';//
// export let domain = 'https://xyxadmin.frp.leeg.cc';//测试
export let domain = 'https://h5.lorisai.cn/';//生产
// export let domain = 'https://xyxh5.frp.leeg.cc';//测试

if (process.env.NODE_ENV == 'development') {
	// domain = domain + '/api'
	domain = domain + '/prod-api';
} else if (process.env.NODE_ENV == 'production') {
	domain = domain + '/prod-api'
}
// 需要登陆的，都写到这里
const methodsLogin = [
	Interface.getMyselfDetail, 
	Interface.getComplaintList,
	Interface.getComplaintDetail,
	Interface.getEvaluateDetail,
	Interface.updateMyselfDetail,
	Interface.evaluate,
	Interface.getMyComplaint,
	Interface.addComplaint,
	Interface.AppraiseDel,
	Interface.getComplaintPross
];
//POST方法
export const post = async (url, data) => {
	return new Promise((resole, reject) => {
		uni.showLoading({
			title: '加载中'
		});
		let header = {};
		// 判断token是否存在
		if (methodsLogin.indexOf(url) != -1) {
			// 获取用户token
			let token = db.token();
			if (!token) {
				uni.hideLoading()
				// uni.showModal({
				// 	content: '请先登录',
				// 	success(res) {
				// 		if(res.confirm){							
				// 			uni.navigateTo({
				// 				url: '/pages/login/login'
				// 			})
				// 		}
				// 	}
				// })
				uni.navigateTo({
					url: '/pages/login/login'
				})
				return;
			} else {
				header['Authorization'] = db.token()
			}
		}
		uni.request({
			url: domain + url,
			header: header,
			data: data,
			method: 'POST',
			success: (response) => {
				const result = response.data;
				if(result.code == 200){
				   resole(result);
				}
				if (result.code != 200) {
					// 登录信息过期或者未登录
					if (result.code == 403 || result.code == 401) {
						db.del("token");
						return uni.showToast({
							title: result.msg,
							icon: 'none',
							duration: 1000,
							complete: function() {
								setTimeout(function() {
									uni.hideToast();
									uni.navigateTo({
										url: '/pages/login/login'
									})
								}, 1000)
							}
						});
					} else {
						uni.showToast({
							title: result.msg ?? '请求失败，请稍后重试',
							icon: 'none',
							duration: 1000
						});
					}
				}
				
			},
			complete: () => {
				setTimeout(function() {
					uni.hideLoading();
				}, 250);
			},
			fail: (error) => {
				resole({});
				if (error && error.response) {
					showError(error.response);
				}
			},
		});
	})
}
//GET方法
export const get = async (url, data = {}) => {
	return new Promise((resole, reject) => {
		uni.showLoading({
			title: '加载中'
		});
		let header = {}
		// 判断token是否存在
		if (methodsLogin.indexOf(url) != -1) {
			// 获取用户token
			let token = db.token();
			if (!token) {
				uni.hideLoading()
				// uni.showModal({
				// 	content: '请先登录',
				// 	success(res) {
				// 		if(res.confirm){							
				// 			uni.navigateTo({
				// 				url: '/pages/login/login'
				// 			})
				// 		}
				// 	}
				// })
				uni.navigateTo({
					url: '/pages/login/login'
				})
				return;
			} else {
				header['Authorization'] = db.token()
			}
		}
		uni.request({
			url: domain + url,
			data: data,
			header: header,
			method: 'GET',
			success: (response) => {
				uni.hideLoading();
				const result = response.data
				if (result.code != 200) {
					// 登录信息过期或者未登录
					if (result.code == 403  || result.code == 401) {
						db.del("token");
					return	uni.showToast({
							title: result.msg,
							icon: 'none',
							duration: 1000,
							complete: function() {
								setTimeout(function() {
									uni.hideToast();
									uni.navigateTo({
										url: '/pages/login/login'
									})
								}, 1000)
							}
						});
					} else {
						uni.showToast({
							title: result.msg ?? '请求失败，请稍后重试',
							icon: 'none',
							duration: 1000
						});
					}
				}else{	
					resole(result);
				}
			},
			fail: (error) => {
				uni.showToast({
					title: '服务异常，请稍后重试',
					icon: 'none',
					duration: 1000,
					complete: function() {
						setTimeout(function() {
							uni.hideToast();
						}, 1000);
					}
				});
				resole({});
				// if (error && error.response) {
				// 	showError(error.response);
				// }
			},
			complete: (cop) => {
				setTimeout(function() {
					uni.hideLoading();
				}, 250);
			}
		});
	})
}
//统一处理请求错误
const showError = error => {
	let errorMsg = ''
	switch (error.status) {
		case 400:
			errorMsg = '请求参数错误'
			break
		case 401:
			errorMsg = '未授权，请登录'
			break
		case 408:
			errorMsg = '请求超时'
			break
		case 500:
			errorMsg = '服务器内部错误'
			break
		case 501:
			errorMsg = '服务未实现'
			break
		case 502:
			errorMsg = '网关错误'
			break
		case 503:
			errorMsg = '服务不可用'
			break
		case 504:
			errorMsg = '网关超时'
			break
		case 505:
			errorMsg = 'HTTP版本不受支持'
			break
		default:
			errorMsg = error.msg
			break
	}
	uni.showToast({
		title: errorMsg,
		icon: 'none',
		duration: 1000,
		complete: function() {
			setTimeout(function() {
				uni.hideToast();
			}, 1000);
		}
	});
}


// 底部栏 ok
//export const banner = async (data) => await get('/api/v1/banner', data);