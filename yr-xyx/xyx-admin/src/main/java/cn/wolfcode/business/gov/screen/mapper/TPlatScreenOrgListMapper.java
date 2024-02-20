package cn.wolfcode.business.gov.screen.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TPlatScreenOrgListMapper {


    List<Map> getAreaCount(Map params);

    List<Map> getOrgLongAndLat(Map params);
}
