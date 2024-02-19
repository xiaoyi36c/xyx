import { get, post } from './req'
//注册

//获取区域  business/org/getAreaList
export const getAreaList = p => get('/business/org/getAreaList', p);
//机构地图 参数: type[类型1:统计，非1: 具体机构经纬度]areaId[地区id)
export const getAllOrgMap = p => get('/business/screen/getAllOrgMap', p);
//右上角机构列表 参数: creditstatus[信用状态:1: 良好，2:一般，3: 轻微失信，4: 严重失信]
export const getOrgList = p => get('/business/screen/screenList', p);


//信用分布饼状图  参数: type[类型:1,2,3: 省市区]，regionId[地区id : 该地区id
export const getCreditGroup = p => get('/business/screen/getCreditGroup', p);
export const getUserAppraise = p => get('/business/screen/getUserAppraise', p);

//行业分析
export const getIndutryList = p => get('/business/screen/getIssueindustryAnalyse', p);
//获取top10
export const getTop10 = p => get('/business/screen/getGoodOrg', p);


// tousuApi,tuisongApi,weekTotalApi
export const tousuApi = p => get('/business/screen/getOrgComplaint', p);
export const tuisongApi = p => get('/business/topic/getPushNum', p);
export const weekTotalApi = p => get('/business/screen/getTheWeekCount', p);

//人流量
export const peopleTotalApi = p => get('/business/topic/getVisitorsFlowRate', p);
export const getOrgCreditCount = p => get('/business/screen/getOrgCreditCount', p);
//登录
export const sysLogin = p => post('/login', p);




export const Register = p => post('api/WebAPI/Register?phoneCode='+p.phoneCode+'&token='+p.token, p);
//找回密码验证码 /api/WebAPI/RetrievePasswordforSMS
export const RetrievePasswordforSMS = p => get('api/WebAPI/RetrievePasswordforSMS', p);

//重置密码 /api/WebAPI/SetNewPassWord
export const SetNewPassWord = p => get('api/WebAPI/SetNewPassWord', p);

//获取验证码  /api/WebAPI/UserLogin
export const UserLogin = p => get('api/WebAPI/UserLogin?token='+p.token, p);

//文件上传 f
export const UpDataFile = p => post('api/UserInfoAPI/UpDataFile',p)

//获取用户信息 /api/UserInfoAPI/GetUserInfo
export const GetUserInfo = p => get('api/UserInfoAPI/GetUserInfo',p)

//获取当前用户立案记录   /api/UserInfoAPI/GetUserCaseRecord
export const GetUserCaseRecord = p => post('api/UserInfoAPI/GetUserCaseRecord',p)

//更新当前用户信息    /api/UserInfoAPI/UpdateUserInfo
export const UpdateUserInfo = p => post('api/UserInfoAPI/UpdateUserInfo',p)

//创建立案记录   /api/UserInfoAPI/AddCaseRecord
export const AddCaseRecord = p => post('api/UserInfoAPI/AddOrEditCaseRecord',p)

//获取文字说明 /api/WebAPI/GetSinglePage/{id} id:1 、2 、3 
export const GetSinglePage = p => get('api/WebAPI/GetSinglePage',p)

//获取  名单
export const GetArbitrators = p => post('api/WebAPI/GetArbitrators',p)

//文书下载
export const GetDocumentTemplate = p => post('api/WebAPI/GetDocumentTemplate',p)

//获取仲裁详情  /api/UserInfoAPI/GetCaseRecord/{id}
export const GetCaseRecord = p => get('api/UserInfoAPI/GetCaseRecord',p)

//删除当条仲裁  /api/UserInfoAPI/DeleteCaseRecord/{id}
export const DeleteCaseRecord = p => get('api/UserInfoAPI/DeleteCaseRecord',p)
