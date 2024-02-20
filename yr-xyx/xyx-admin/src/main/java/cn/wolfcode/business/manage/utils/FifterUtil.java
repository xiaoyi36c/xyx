package cn.wolfcode.business.manage.utils;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: yyy
 * Date: 2023/6/29
 * Time: 14:12
 * Description:数据过滤
 */
public class FifterUtil {
    public static String fifterWebLabel(Object source, List<Map> webLabel) {
        if(source!=null){
            String temp=(String) source;
            for (Map label : webLabel) {
                temp =  (String)temp.replaceAll((String) label.get("value"),"");
            }
            return temp;
        }
       return null;
    }
}
