package cn.wolfcode.business.manage.utils;


import cn.wolfcode.common.exception.UtilException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: yyy
 * Date: 2023/5/4
 * Time: 15:19
 * Description:
 */

public class MapValidator {
    private static ThreadLocal<Map> sourceMap = new ThreadLocal();
    private static ThreadLocal<List<Map>> errMsg = new ThreadLocal();
    private static ThreadLocal<String> key = new ThreadLocal();
    private static volatile MapValidator instance = null;

    private MapValidator() {
    }

    public static MapValidator init(Map source) {
        if (instance == null) {
            synchronized(MapValidator.class) {
                if (instance == null) {
                    instance = new MapValidator();
                }
            }
        }

        sourceMap.set(source);
        errMsg.set(new ArrayList());
        return instance;
    }

    public MapValidator key(String keyName) {
        key.set(keyName);
        return this;
    }


    public MapValidator required(String message) {
        List collect = this.getErrMsgByKey((String)key.get());
        System.out.println(key.get());
        System.out.println(sourceMap.get());
        if (collect.size() == 0 && BaseUtil.isNullOrEmpty(((Map)sourceMap.get()).get(key.get()))) {
            ((List)errMsg.get()).add(new LinkedHashMap() {
                {
                    this.put("key", MapValidator.key.get());
                    this.put("msg", message);
                }
            });
        }

        return this;
    }

    public MapValidator minLength(Integer length, String message) {
        List collect = this.getErrMsgByKey((String)key.get());
        if (collect.size() == 0 && (((Map)sourceMap.get()).get(key.get()) + "").length() < length) {
            ((List)errMsg.get()).add(new LinkedHashMap() {
                {
                    this.put("key", MapValidator.key.get());
                    this.put("msg", message);
                }
            });
        }

        return this;
    }

    public MapValidator maxLength(Integer length, String message) {
        List collect = this.getErrMsgByKey((String)key.get());
        if (collect.size() == 0 && (((Map)sourceMap.get()).get(key.get()) + "").length() > length) {
            ((List)errMsg.get()).add(new LinkedHashMap() {
                {
                    this.put("key", MapValidator.key.get());
                    this.put("msg", message);
                }
            });
        }

        return this;
    }

    public MapValidator regex(String pattern, String message) {
        List collect = this.getErrMsgByKey((String)key.get());
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(((Map)sourceMap.get()).get(key.get()) + "");
        if (collect.size() == 0 && !m.matches()) {
            ((List)errMsg.get()).add(new LinkedHashMap() {
                {
                    this.put("key", MapValidator.key.get());
                    this.put("msg", message);
                }
            });
        }

        return this;
    }

    public void validate() {
        if (((List)errMsg.get()).size() > 0) {
            List msg = (List)(errMsg.get()).stream().map(p ->
                    p.get("msg")
            ).collect(Collectors.toList());
            errMsg.remove();
            sourceMap.remove();
            throw new UtilException(String.join(",",msg));
        }
    }

    private List getErrMsgByKey(String keyName) {
        List collect = (List)(errMsg.get()).stream().filter(p ->
                p.get("key").equals(keyName)
        ).collect(Collectors.toList());
        return collect;
    }
}
