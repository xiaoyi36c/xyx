package cn.wolfcode.business.manage.credit.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CelOrgMapper {

    List<Map> getAllArea(Map params);
}
