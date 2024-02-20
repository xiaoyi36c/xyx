package cn.wolfcode.business.manage.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: yyy
 * Date: 2023/5/4
 * Time: 15:18
 * Description:
 */
public class BaseUtil {

    public BaseUtil() {
    }

    public static boolean isNullOrEmpty(Object pObj) {
        if (pObj == null) {
            return true;
        } else {
            if (pObj instanceof String) {
                if (((String)pObj).trim().length() == 0) {
                    return true;
                }
            } else if (pObj instanceof Collection) {
                if (((Collection)pObj).size() == 0) {
                    return true;
                }
            } else if (pObj instanceof Map && ((Map)pObj).size() == 0) {
                return true;
            }

            return false;
        }
    }

    public static Map getWeekMap(Date date){
        DateTime beginWeekTime= DateUtil.beginOfWeek(date);
        Map paramMap = new HashMap();
        paramMap.put("monday", beginWeekTime);
        paramMap.put("tuesday", DateUtil.offsetDay(beginWeekTime,1));
        paramMap.put("wednesday", DateUtil.offsetDay(beginWeekTime,2));
        paramMap.put("thursday", DateUtil.offsetDay(beginWeekTime,3));
        paramMap.put("friday", DateUtil.offsetDay(beginWeekTime,4));
        paramMap.put("saturday", DateUtil.offsetDay(beginWeekTime,5));
        paramMap.put("sunday", DateUtil.offsetDay(beginWeekTime,6));
        paramMap.put("nextMonday", DateUtil.offsetDay(beginWeekTime,7));
        return paramMap;
    }

    public static Map getMonthMap(Date date){
        Map paramMap = new HashMap();
        paramMap.put("first", date);
        paramMap.put("second", DateUtil.offsetMonth(date,1));
        paramMap.put("thirdly", DateUtil.offsetMonth(date,2));
        paramMap.put("fourthly", DateUtil.offsetMonth(date,3));
        paramMap.put("fifth", DateUtil.offsetMonth(date,4));
        paramMap.put("sixth", DateUtil.offsetMonth(date,5));
        paramMap.put("seventh", DateUtil.offsetMonth(date,6));
        return paramMap;
    }

    public static List getMonthList(Date date){
        List dataList=new ArrayList();
        SimpleDateFormat sdf=new SimpleDateFormat("MM");
        dataList.add(Integer.valueOf(sdf.format(date))+"月");
        dataList.add(Integer.valueOf(sdf.format(DateUtil.offsetMonth(date,1)))+"月");
        dataList.add(Integer.valueOf(sdf.format(DateUtil.offsetMonth(date,2)))+"月");
        dataList.add(Integer.valueOf(sdf.format(DateUtil.offsetMonth(date,3)))+"月");
        dataList.add(Integer.valueOf(sdf.format(DateUtil.offsetMonth(date,4)))+"月");
        dataList.add(Integer.valueOf(sdf.format(DateUtil.offsetMonth(date,5)))+"月");
        dataList.add(Integer.valueOf(sdf.format(DateUtil.offsetMonth(date,6)))+"月");
        return dataList;
    }

    public static String getDistanceByLongAndLat(Map params,Map orgDetailMap){
        //开始经度
        double lon1 = (Math.PI / 180) * Double.valueOf(params.get("long").toString());
        //结束经度
        double lon2 = (Math.PI / 180) * Double.valueOf(orgDetailMap.get("longitude").toString());
        //开始纬度
        double lat1 = (Math.PI / 180) * Double.valueOf(params.get("lat").toString());
        //结束纬度
        double lat2 = (Math.PI / 180) * Double.valueOf(orgDetailMap.get("latitude").toString());

        double R = 6371;
        double distance = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(distance);
    }
}

