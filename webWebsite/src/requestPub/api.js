import { get, post } from './req'

//省市  type=1&regionId=50000    参数;type：类型123国省市    regionId：地区id
export const getRegionMap = data => get('org/officialWebsite/getRegionMap',data)

export const getCurrArea = data => get('org/officialWebsite/getAreaByCity',data)

export const getPushType = data => get('org/officialWebsite/getPushType',data)
export const getIdentityType = data => get('org/officialWebsite/getIdentityType',data)

export const getAllOrgAndCourse = data => get('org/officialWebsite/getAllOrgAndCourse',data)
//文章列表 
export const getArticleList = data => get('org/officialWebsite/getArticleList',data)

//文章详情 org/officialWebsite/getArticleDetail?id=1   参数;type：id
export const getArticleDetail = data => get('org/officialWebsite/getArticleDetail',data)

// 普通机构排名		org/officialWebsite/getOrgRanking 无参数
export const orgCreditOrderBy = data => get('org/officialWebsite/getOrgRanking',data)
// k12机构排名		org/officialWebsite/getOrgRanking 无参数
export const getOrgOtherRanking = data => get('org/officialWebsite/getOrgOtherRanking',data)
// 地区机构排名		org/officialWebsite/getOrgRanking 无参数
export const orgRegionCreditOrderBy = data => get('org/officialWebsite/orgRegionCreditOrderBy',data)

// 首页轮播:三文章加两广告		http://localhost:8080/org/officialWebsite/getArticleAdvertisingList	无参数
export const getArticleAdvertisingList = data => get('org/officialWebsite/getArticleAdvertisingList',data)

// 行业树形菜单	 无参数
export const getIssueindustryTree = data => get('org/officialWebsite/getIssueindustryTree',data)

// 机构与其相关信息列表 http://localhost:8080/org/officialWebsite/getOrgJoinList?pageNum=1&pageSize=3   	分页参数
export const getOrgJoinList = data => get('org/officialWebsite/getOrgJoinList',data)

// 机构信用公示	 分页参数+信用状态：creditStatus：信用状态1：良好，2：一般，3：轻微失信，4：严重失信
export const getOrgShowList = data => get('org/officialWebsite/getOrgShowList',data)

//企业下拉框搜索 	search：条件
export const getOrgBySearch = data => get('org/officialWebsite/getOrgBySearch',data)


// 机构详情    参数：id：机构id
export const getOrgDetail = data => get('org/officialWebsite/getOrgDetail',data)

//通用文件上传[post] 
export const uploads = data => post('org/officialWebsite/orgClaim/common/uploads', data);
//社会荣誉 orgId=1    参数：orgId：机构id
export const getSocialPrestige = data => get('org/officialWebsite/getSocialPrestige',data)
//获取投诉 orgId=1    参数：orgId：机构id
export const getComplaintByOrg = data => get('org/officialWebsite/getComplaintByOrg',data)
//获取课程 orgId=1    参数：orgId：机构id
export const getCourse = data => get('org/officialWebsite/getCourse',data)
//获取处罚 orgId=1    参数：orgId：机构id
export const getAdministrativePenalty = data => get('org/officialWebsite/getAdministrativePenalty',data)
//获取师资 orgId=1    参数：orgId：机构id
export const getTeachersPower = data => get('org/officialWebsite/getTeachersPower',data)
//获取诚实守信 orgId=1    参数：orgId：机构id
export const getHonestyTrustworthiness = data => get('org/officialWebsite/getHonestyTrustworthiness',data)
//获取诚失信名单 orgId=1    参数：orgId：机构id
export const getLoseCredit = data => get('org/officialWebsite/getLoseCredit',data)
//获取信用承诺 orgId=1    参数：orgId：机构id
export const getCreditCommitment = data => get('org/officialWebsite/getCreditCommitment',data)
//获取信用评价 orgId=1    参数：orgId：机构id
export const getCreditEvaluate = data => get('org/officialWebsite/getCreditEvaluate',data)

//
export const getFeeOther = data => get('org/officialWebsite/getFeeOther',data)
export const getOrgEvaluate = data => get('org/officialWebsite/getOrgEvaluate',data)

//订阅申请 orgId=1    参数：orgId：机构id
export const subscriptionApply = data => post('org/officialWebsite/subscriptionApply',data)
//机构认领 orgId=1    参数：orgId：机构id
export const orgClaim = data => post('org/officialWebsite/orgClaim',data)

//获取信用下的子分类
export const getChildType = data => get('org/officialWebsite/getCreditChildrenList',data)

//发送短信验证码
export const getSmsCode = p => post('org/officialWebsite/getLoginCode',p)
