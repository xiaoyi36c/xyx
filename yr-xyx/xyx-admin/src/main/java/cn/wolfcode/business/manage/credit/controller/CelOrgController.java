package cn.wolfcode.business.manage.credit.controller;

import cn.wolfcode.business.manage.credit.service.CelOrgService;
import cn.wolfcode.common.annotation.Log;
import cn.wolfcode.common.core.domain.AjaxResult;
import cn.wolfcode.common.enums.BusinessType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 * 机构基本信息 controller
 *
 */

@RestController
@RequestMapping("/business/org")
public class CelOrgController {
    private final CelOrgService celOrgService;

    public CelOrgController(CelOrgService celOrgService) {
        this.celOrgService = celOrgService;
    }


    @PostMapping("/getAreaList")
    @Log(title = "获取省市区树形菜单 ", businessType = BusinessType.INSERT)
    public AjaxResult getAreaList(@RequestBody Map params) {
        return AjaxResult.success(celOrgService.getAreaList(params));
    }

}
