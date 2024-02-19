const Interface =   {
	// 文件上传
	uploadFile: '/org/officialWebsite/uploadFile',
	//机构列表 orgType为null就是全部，1:普通机构，2：k12机构
	orgList:'/org/officialWebsite/mobileOrgCommon',
	//获取行业
	getIssueindustryTree:'/org/officialWebsite/getIssueindustryTree',
	//机构详情
	getOrgDetail:'/org/officialWebsite/getOrgDetail',
	//获取子级分类  /org/officialWebsite/getCreditChildrenList?type=1
	getCreditChildrenList:'/org/officialWebsite/getCreditChildrenList',
	
	//获取信用信息 -  行政管理的内容
	getAdministrativePenalty:'/org/officialWebsite/getAdministrativePenalty',
	//获取诚实守信
	getHonestyTrustworthiness:'/org/officialWebsite/getHonestyTrustworthiness',
	//获取失信名单
	getLoseCredit:'/org/officialWebsite/getLoseCredit',
	//获取信用承诺
	getCreditCommitment:'/org/officialWebsite/getCreditCommitment',
	getFeeOther:'/org/officialWebsite/getFeeOther',
	
	//获取教师、/获取课程、评价、投诉
	getTeachersPower:'/org/officialWebsite/getTeachersPower',
	getCourse:'/org/officialWebsite/getCourse',
	getOrgEvaluate:'/org/officialWebsite/getOrgEvaluate',
	getComplaintByOrg:'/org/officialWebsite/getComplaintByOrg',
	//荣誉与责任
	getSocialPrestige:'/org/officialWebsite/getSocialPrestige',
	//获取校区
	getOrgSchoolRegion:'/org/officialWebsite/getOrgSchoolRegion',
	getAppraiseById:'/org/officialWebsite/getAppraiseById',
	// 获取地区
	getAreaMap: '/org/officialWebsite/getAreaMap',
	// 用户注册
	register: '/mobile/manage/register',
	// 获取注册验证码
	getRegisterCode: '/org/officialWebsite/getRegisterCode',
	// 获取登录验证码
	getLoginCode: '/org/officialWebsite/getLoginCode',
	//登录 参数参考  {"username":"用户名字&4","password":"密码"} ，&4为强制携带
	login:'/login',
	// 获取个人信息
	getMyselfDetail: '/mobile/manage/getMyselfDetail',
	// 获取我的投诉列表
	getComplaintList: '/mobile/manage/getComplaintList',
	// 获取投诉详情
	getComplaintDetail: '/org/officialWebsite/getComplaintDetail',
	// 获取我的评价列表
	getMyComplaint: '/mobile/manage/getMyComplaint',
	
	//评价新增、修改
	evaluate:'/mobile/manage/evaluate',
	//获取评价详情
	getEvaluateDetail:'/mobile/manage/getEvaluateDetail',
	getComplaintType:'/org/officialWebsite/getComplaintType',
	//修改个人信息
	updateMyselfDetail:'/mobile/manage/updateMyselfDetail',
	//
	getOrgJoinList:'/org/officialWebsite/getOrgJoinList',
	addComplaint:'/mobile/manage/addComplaint',
	//删除评价
	AppraiseDel:'/mobile/manage/delAppraise',
	//获取是否有进行中的投诉
	getComplaintPross:'/mobile/manage/checkComplaintInProgress',
}


export {
	Interface
}