package cn.wolfcode.business.manage.credit.service;

import cn.wolfcode.business.manage.credit.domain.CelOrgCredit;

import java.util.List;

/**
 * 机构异常信息Service接口
 * 
 * @author Bycor
 * @date 2024-02-20
 */
public interface ICelOrgCreditService 
{
    /**
     * 查询机构异常信息
     * 
     * @param id 机构异常信息主键
     * @return 机构异常信息
     */
    public CelOrgCredit selectCelOrgCreditById(Long id);

    /**
     * 查询机构异常信息列表
     * 
     * @param celOrgCredit 机构异常信息
     * @return 机构异常信息集合
     */
    public List<CelOrgCredit> selectCelOrgCreditList(CelOrgCredit celOrgCredit);

    /**
     * 新增机构异常信息
     * 
     * @param celOrgCredit 机构异常信息
     * @return 结果
     */
    public int insertCelOrgCredit(CelOrgCredit celOrgCredit);

    /**
     * 修改机构异常信息
     * 
     * @param celOrgCredit 机构异常信息
     * @return 结果
     */
    public int updateCelOrgCredit(CelOrgCredit celOrgCredit);

    /**
     * 批量删除机构异常信息
     * 
     * @param ids 需要删除的机构异常信息主键集合
     * @return 结果
     */
    public int deleteCelOrgCreditByIds(Long[] ids);

    /**
     * 删除机构异常信息信息
     * 
     * @param id 机构异常信息主键
     * @return 结果
     */
    public int deleteCelOrgCreditById(Long id);
}
