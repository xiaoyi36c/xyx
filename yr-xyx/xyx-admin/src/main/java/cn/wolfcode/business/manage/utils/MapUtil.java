package cn.wolfcode.business.manage.utils;

import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: yyy
 * Date: 2023/6/14
 * Time: 16:09
 * Description:
 */
public class MapUtil {

    public MapUtil() {
    }

    public static Map toPascal(Map source) {
        Map res = new HashMap();
        source.forEach((k, v) -> {
            res.put(StrUtil.toUnderlineCase(k.toString()), v);
        });
        return res;
    }

    public static Map toCamel(Map source) {
        if(source==null){
            return null;
        }
        Map res = new HashMap();
        source.forEach((k, v) -> {
            res.put(StrUtil.toCamelCase(k.toString()), v);
        });
        return res;
    }
}
