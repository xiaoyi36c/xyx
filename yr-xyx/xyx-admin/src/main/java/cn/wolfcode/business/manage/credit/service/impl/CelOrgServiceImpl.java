package cn.wolfcode.business.manage.credit.service.impl;

import cn.wolfcode.business.manage.credit.mapper.CelOrgMapper;
import cn.wolfcode.business.manage.credit.service.CelOrgService;
import cn.wolfcode.business.manage.utils.MapValidator;
import cn.wolfcode.business.manage.utils.TreeUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class CelOrgServiceImpl implements CelOrgService {
    private final CelOrgMapper celOrgMapper;

    public CelOrgServiceImpl(CelOrgMapper celOrgMapper) {
        this.celOrgMapper = celOrgMapper;
    }

    /*市级列表查询*/
    @Override
    public List<Map> getAreaList(Map params) {
        // 参数校验
        MapValidator.init(params)
                .key("type").required("type不能为空")
                .key("regionId").required("编码不能为空")
                .validate();

        return celOrgMapper.getAreaList(params);
    }
}
