package cn.wolfcode.business.gov.screen.service.impl;

import cn.wolfcode.business.gov.screen.mapper.TPlatScreenOrgListMapper;
import cn.wolfcode.business.gov.screen.service.TPlatScreenOrgListService;
import cn.wolfcode.business.manage.utils.MapValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TPlatScreenOrgListServiceImpl implements TPlatScreenOrgListService {
    private final TPlatScreenOrgListMapper tPlatScreenOrgListMapper;

    public TPlatScreenOrgListServiceImpl(TPlatScreenOrgListMapper tPlatScreenOrgListMapper) {
        this.tPlatScreenOrgListMapper = tPlatScreenOrgListMapper;
    }

    /* 获取政府大屏机构地图 */
    @Override
    public List<Map> getAllOrgMap(Map params) {
        // 参数校验
        MapValidator.init(params)
                .key("type").required("类型不能为空")
                .key("areaId").required("地区id不能为空")
                .validate();

        // 判断类型
        if(params.get("type")!=null&&Integer.parseInt(params.get("type").toString())<=1){
            return tPlatScreenOrgListMapper.getAreaCount(params);
        }else{
            return tPlatScreenOrgListMapper.getOrgLongAndLat(params);
        }
    }
}
