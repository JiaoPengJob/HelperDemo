package com.helper.jiaop.helpers;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by jiaop on 2016/6/2.
 * List常用操作
 */
public class ListUtils {
    /**
     * 获取集合长度
     *
     * @param sourceList
     * @param <V>
     * @return
     */
    public static <V> int getSize(List<V> sourceList) {
        return sourceList == null ? 0 : sourceList.size();
    }

    /**
     * 判断List是否为空或长度为0
     *
     * @param sourceList
     * @param <V>
     * @return
     */
    public static <V> boolean isEmpty(List<V> sourceList) {
        return (sourceList == null || sourceList.size() == 0);
    }

    /**
     * List转换为字符串，并以固定分隔符分割
     *
     * @param list
     * @param separator
     * @return
     */
    public static String join(List<String> list, char separator) {
        return join(list, new String(new char[]{separator}));
    }

    public static String join(List<String> list, String separator) {
        return list == null ? "" : TextUtils.join(separator, list);
    }

}
