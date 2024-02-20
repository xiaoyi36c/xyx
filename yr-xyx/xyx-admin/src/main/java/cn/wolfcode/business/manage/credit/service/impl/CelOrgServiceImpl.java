package cn.wolfcode.business.manage.credit.service.impl;

import cn.wolfcode.business.manage.credit.mapper.CelOrgMapper;
import cn.wolfcode.business.manage.credit.service.CelOrgService;
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

    @Override
    public List<Map> getAreaList(Map params) {
        List<Map> allArea = celOrgMapper.getAllArea(params);
        // 转换为树结构
        List<Map> mapList = TreeUtil.formatTree(allArea, false, "pid");
        return mapList;
    }
}
