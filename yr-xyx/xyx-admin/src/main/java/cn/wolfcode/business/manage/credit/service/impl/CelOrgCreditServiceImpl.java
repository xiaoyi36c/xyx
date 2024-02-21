package cn.wolfcode.business.manage.credit.service.impl;

import cn.wolfcode.business.manage.credit.domain.CelOrgCredit;
import cn.wolfcode.business.manage.credit.domain.vo.CelOrgCreditVO;
import cn.wolfcode.business.manage.credit.domain.vo.TPlatformOrgVo;
import cn.wolfcode.business.manage.credit.mapper.CelOrgCreditMapper;
import cn.wolfcode.business.manage.credit.service.ICelOrgCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 机构异常信息Service业务层处理
 * 
 * @author Bycor
 * @date 2024-02-20
 */
@Service
public class CelOrgCreditServiceImpl implements ICelOrgCreditService
{
    @Autowired
    private CelOrgCreditMapper celOrgCreditMapper;

    /**
     * 查询机构异常信息
     * 
     * @param id 机构异常信息主键
     * @return 机构异常信息
     */
    @Override
    public CelOrgCredit selectCelOrgCreditById(Long id)
    {
        return celOrgCreditMapper.selectCelOrgCreditById(id);
    }

    /**
     * 查询机构异常信息列表
     *
     * @param celOrgCredit 机构异常信息
     * @return 机构异常信息
     */
    @Override
    public List<CelOrgCreditVO> selectCelOrgCreditList(CelOrgCredit celOrgCredit)
    {
        return celOrgCreditMapper.selectCelOrgCreditList(celOrgCredit);
    }

    /**
     * 新增机构异常信息
     * 
     * @param celOrgCredit 机构异常信息
     * @return 结果
     */
    @Override
    public int insertCelOrgCredit(CelOrgCredit celOrgCredit)
    {
        return celOrgCreditMapper.insertCelOrgCredit(celOrgCredit);
    }

    /**
     * 修改机构异常信息
     * 
     * @param celOrgCredit 机构异常信息
     * @return 结果
     */
    @Override
    public int updateCelOrgCredit(CelOrgCredit celOrgCredit)
    {
        return celOrgCreditMapper.updateCelOrgCredit(celOrgCredit);
    }

    /**
     * 批量删除机构异常信息
     * 
     * @param ids 需要删除的机构异常信息主键
     * @return 结果
     */
    @Override
    public int deleteCelOrgCreditByIds(Long[] ids)
    {
        return celOrgCreditMapper.deleteCelOrgCreditByIds(ids);
    }

    /**
     * 删除机构异常信息信息
     * 
     * @param id 机构异常信息主键
     * @return 结果
     */
    @Override
    public int deleteCelOrgCreditById(Long id)
    {
        return celOrgCreditMapper.deleteCelOrgCreditById(id);
    }

    @Override
    public List<TPlatformOrgVo> getOrgByName(String name) {
        return celOrgCreditMapper.getOrgByName(name);
    }
}
