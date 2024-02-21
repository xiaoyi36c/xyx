import request from '@/utils/request'

// 查询机构异常信息列表
export function listCredit(query) {
  return request({
    url: '/manage/credit/list',
    method: 'get',
    params: query
  })
}

// 查询机构异常信息详细
export function getCredit(id) {
  return request({
    url: '/manage/credit/' + id,
    method: 'get'
  })
}

// 新增机构异常信息
export function addCredit(data) {
  return request({
    url: '/manage/credit',
    method: 'post',
    data: data
  })
}

// 修改机构异常信息
export function updateCredit(data) {
  return request({
    url: '/manage/credit',
    method: 'put',
    data: data
  })
}

export function selectOrgName(organizationName) {
    return request({
        url: `/manage/credit/getOrgByName?organizationName=${organizationName}`,
        method: 'get',
    })
}

// 删除机构异常信息
export function delCredit(id) {
  return request({
    url: '/manage/credit/' + id,
    method: 'delete'
  })
}
