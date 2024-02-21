package cn.wolfcode.business.gov.screen.service;

import java.util.List;
import java.util.Map;

public interface TPlatScreenOrgListService {

    // 政府大屏机构地图
    List<Map> getAllOrgMap(Map params);

    Map getOrgCreditCount(Map params);
}
