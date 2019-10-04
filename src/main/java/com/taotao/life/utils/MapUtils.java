package com.taotao.life.utils;

import com.alibaba.fastjson.JSON;
import com.taotao.life.dto.AccessTokenDto;
import lombok.val;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapUtils {
    private static String compile = "[A-Z]";
    private static Object obj;

    private MapUtils(){}

    public static<T> Map transform(T object){
        if(object == null){
            return null;
        }

        Map<String, Object> map = new HashMap<>();
        // 获取实体类所有属性，返回Field数组
        Field[] fields = object.getClass().getDeclaredFields();

        try {
            for (int i = 0 ; i < fields.length; i++){
                // 属性名称
                String fieldName = fields[i].getName();

                // 转换驼峰形式属性名称成下划线风格，获取map的key 例：fieldName 》 field_name
                String transformFieldName =  MapUtils.getTransformFieldName(fieldName);
                // map 的 value ，属性的值
                Object FieldValue = null;

                // 将属性的首字符大写，方便构造get，set方法
                String name =  fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                // 获取属性的类型
                String type = fields[i].getGenericType().toString();
                Method m = object.getClass().getMethod("get" + name);

                switch (type){
                    // 如果有需要,可以仿照下面继续进行扩充,再增加对其它类型的判断
                    case "class java.lang.String":
                    case "class java.lang.Boolean":
                    case "class java.util.Date":
                    case "class java.lang.Integer":
                    case "class java.lang.Long":
                        // 调用getter方法获取属性值
                        FieldValue =  m.invoke(object);
                        break;
                    default:
                        // 属性类型为bean,则递归
                        Object obj =  m.invoke(object);
                        FieldValue = MapUtils.transform(obj);
                }
                map.put(transformFieldName,FieldValue);
            }
        }catch (Exception e){
            // 系统异常
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 转换风格 驼峰转下划线
     * @param fieldName 属性名称
     * @return
     */
    private static String getTransformFieldName(String fieldName) {
        Pattern humpPattern = Pattern.compile(compile);
        Matcher matcher = humpPattern.matcher(fieldName);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, "_"+matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String isNotEmptyBatch(Object... objs) {
        for (Object obj : objs) {
            if (obj == null || "".equals(obj)) {
                return "必传参数有空值";
            }
        }
        return "";
    }

    public static void main(String[] args) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode("111");
        accessTokenDto.setClientId("675c188385ae6c8c9e5e");
        accessTokenDto.setClientSecret("359b9764a13d983d68287d2a87653a0a317b7f94");
        accessTokenDto.setRedirectUri("http://localhost:8080/callback");
        accessTokenDto.setState("1111");
        Map transform = MapUtils.transform(accessTokenDto);
        String jsonString = JSON.toJSONString(transform);
        // {"shop_id":"9527","product_type":2,"shop":{"shop_phone":"13800138000","shop_name":"人民大会堂","shop_address":"北京市天安门"},"user_address":"北京市朝阳区","weight":100,"is_appoint":0,"pay_type":0,"millisecond_time":1539225333920,"create_date":1539225333920,"push_time":1539225333,"dev_id":123456789}
        System.out.println(jsonString);
    }

}
