package cn.wolfcode.business.manage.credit.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CelOrgMapper {

    // 市级区域树状
    List<Map> getAllArea(Map params);

    // 市级区域列表
    List<Map> getAreaList(Map params);
}
