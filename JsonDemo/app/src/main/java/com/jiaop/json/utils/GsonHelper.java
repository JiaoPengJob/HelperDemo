package com.jiaop.json.utils;

import com.google.gson.Gson;

/**
 * 使用Gson
 * 将json字符串解析为集合时例子：
 * List<Person> ps = gson.fromJson(str, new TypeToken<List<Person>>(){}.getType());
 */
public class GsonHelper{

    private static Gson gson;

    public GsonHelper(){
         gson = new Gson();
    }

    /**
     * 将JavaBean转换成json字符串，JavaBean除了基本数据类型还包含了List和Map集合
     * @param bean
     * @return
     */
    public static String beanToJson(Class<?> bean){
        return gson.toJson(bean);
    }

    /**
     * 将Json字符串转换成JavaBean
     * @param jsonStr
     * @param bean
     * @return
     */
    public static Class<?> jsonToBean(String jsonStr,Class<?> bean){
        return gson.fromJson(jsonStr, bean.getClass());
    }

}
