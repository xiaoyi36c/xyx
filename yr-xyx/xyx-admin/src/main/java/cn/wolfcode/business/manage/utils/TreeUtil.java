package cn.wolfcode.business.manage.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: yyy
 * Date: 2023/5/9
 * Time: 10:13
 * Description:
 */
public class TreeUtil {
    public static <T extends Map> List<T> formatTree(List<T> list, boolean expandAll,String site) {
        List<T> nodeList = new ArrayList<T>();
        for (T node1 : list) {
            boolean mark = false;
            for (T node2 : list) {
                if (node1.get(site) != null && node1.get(site).toString().equals(node2.get("id").toString())) {
                    node2.put("isLeaf", 0);
                    mark = true;

                    if (node2.get("children") == null) {
                        node2.put("children", new ArrayList<T>());
                    }
                    ArrayList children = (ArrayList) node2.get("children");
                    children.add(node1);
                    node2.put("children", children);

                    if (expandAll) {
                        node2.put("expand", true);
                    } else {
                        //node2.put("expand", false);
                    }
                    break;
                }
            }

            if (!mark) {
                nodeList.add(node1);
                if (expandAll) {
                    //all expand
                }
            } else {
                node1.put("expand", false);
            }
        }
        return nodeList;
    }

    public static String getParentPath(Map parentNode) {
        if (BaseUtil.isNullOrEmpty(parentNode.get("pid"))) {
            return parentNode.get("id").toString();
        }
        return parentNode.get("pid") +","+ parentNode.get("id").toString();
    }

    public static String getParentCode(Map parentNode) {
        if (BaseUtil.isNullOrEmpty(parentNode.get("pid"))) {
            return parentNode.get("code").toString();
        }
        return parentNode.get("codeDesc").toString();
    }
}
