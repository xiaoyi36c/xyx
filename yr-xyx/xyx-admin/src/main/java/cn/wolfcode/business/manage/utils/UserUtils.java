package cn.wolfcode.business.manage.utils;


import cn.wolfcode.common.constant.HttpStatus;
import cn.wolfcode.common.core.domain.entity.SysUser;
import cn.wolfcode.common.core.domain.model.LoginUser;
import cn.wolfcode.common.exception.ServiceException;

import java.util.List;
import java.util.stream.Collectors;

import static cn.wolfcode.common.utils.SecurityUtils.getAuthentication;


/**
 * 当前用户Session工具类
 * @author: Shiro
 * @create: 2023-05-09 16:10
 **/
public class UserUtils {
    /**
     * 获取当前用户
     * @return
     */
    public static SysUser currentUser(){
        try
        {
            LoginUser loginUser = (LoginUser)getAuthentication().getPrincipal();
            return loginUser.getUser();
        }
        catch (Exception e)
        {
            throw new ServiceException("获取用户信息异常", HttpStatus.UNAUTHORIZED);
        }
    }
    /**
     * 获取当前用户登录详情
     * @return
     */
    public static LoginUser getLoginUser(){
        try
        {
            return  (LoginUser)getAuthentication().getPrincipal();
        }
        catch (Exception e)
        {
            throw new ServiceException("获取用户信息异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取当前用户下所有角色 并返回 List<String>
     * @return
     */
    public static List<String> currentUserRoleKeys(){
        try
        {
            LoginUser loginUser = (LoginUser)getAuthentication().getPrincipal();
            //获取角色并转成List<String>
            List<String> roleKeys = loginUser.getUser().getRoles().stream().map(role -> role.getRoleKey()).collect(Collectors.toList());
            return roleKeys;
        }
        catch (Exception e)
        {
            throw new ServiceException("获取用户信息异常", HttpStatus.UNAUTHORIZED);
        }
    }

}
