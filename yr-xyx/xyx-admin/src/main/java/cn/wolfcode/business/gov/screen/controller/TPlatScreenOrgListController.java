package cn.wolfcode.business.gov.screen.controller;


import cn.wolfcode.business.gov.screen.service.TPlatScreenOrgListService;
import cn.wolfcode.common.annotation.Log;
import cn.wolfcode.common.core.domain.AjaxResult;
import cn.wolfcode.common.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 *
 * 政府大屏 controller
 *
 * */

@RestController
@RequestMapping("/business/screen")
public class TPlatScreenOrgListController {
    private final TPlatScreenOrgListService tPlatScreenOrgListService;

    public TPlatScreenOrgListController(TPlatScreenOrgListService tPlatScreenOrgListService) {
        this.tPlatScreenOrgListService = tPlatScreenOrgListService;
    }


    /* 政府大屏获取机构区域地图 */
    @Log(title = "机构地图 ", businessType = BusinessType.OTHER)
    @GetMapping("/getAllOrgMap")
    public AjaxResult getAllOrgMap(@RequestParam Map params){
        return AjaxResult.success(tPlatScreenOrgListService.getAllOrgMap(params));
    }


    /* 政府大屏累计数据 */
    @Log(title = "获取累计数 ", businessType = BusinessType.OTHER)
    @GetMapping("/getOrgCreditCount")
    public AjaxResult getOrgCreditCount(@RequestParam Map params){
        return AjaxResult.success(tPlatScreenOrgListService.getOrgCreditCount(params));
    }

}
