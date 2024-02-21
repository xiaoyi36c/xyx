package cn.wolfcode.business.manage.credit.mapper;

import cn.wolfcode.business.manage.credit.domain.CelOrgCredit;
import cn.wolfcode.business.manage.credit.domain.vo.CelOrgCreditVO;
import cn.wolfcode.business.manage.credit.domain.vo.TPlatformOrgVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 机构异常信息Mapper接口
 * 
 * @author Bycor
 * @date 2024-02-20
 */
@Mapper
public interface CelOrgCreditMapper 
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
    public List<CelOrgCreditVO> selectCelOrgCreditList(CelOrgCredit celOrgCredit);

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
     * 删除机构异常信息
     * 
     * @param id 机构异常信息主键
     * @return 结果
     */
    public int deleteCelOrgCreditById(Long id);

    /**
     * 批量删除机构异常信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCelOrgCreditByIds(Long[] ids);

    List<TPlatformOrgVo> getOrgByName(String name);
}
