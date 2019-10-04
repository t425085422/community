package com.taotao.life.provider;

import com.alibaba.fastjson.JSON;
import com.taotao.life.dto.AccessTokenDto;
import com.taotao.life.dto.GithubUser;
import com.taotao.life.utils.MapUtils;
import okhttp3.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class GithubProvider {

    @Autowired(required = false)
    private MapUtils mapUtils;


    public String  GithubProvider(AccessTokenDto accessTokenDto){
         MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
//        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
//        Request request = new Request.Builder()
//                .url("https://github.com/login/oauth/access_token")
//                .post(body)
//                .build();
        try {
            Map<String, String> map = objectToMap(accessTokenDto);
            Map map1 = mapUtils.transform(accessTokenDto);
            String url = getUrl("https://github.com/login/oauth/access_token",(HashMap<String, String>) map1);
            Request request = new Request.Builder()
                .url(url)
                .build();
         Response response = client.newCall(request).execute();
            String string = response.body().string();
            String a = string.split("&")[0].split("=")[1];
            return a;
        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;
    }

    public GithubUser getUsger(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            GithubUser user = JSON.parseObject(response.body().string(), GithubUser.class);
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getUrl(String url,HashMap<String, String> params) {
        // 添加url参数
        if (params != null) {
            Iterator<String> it = params.keySet().iterator();

            StringBuffer sb = null;
            while (it.hasNext()) {
                String key = it.next();
                String value = params.get(key);
                if (sb == null) {
                    sb = new StringBuffer();
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(key);
                sb.append("=");
                sb.append(value);
            }
            url += sb.toString();
        }
        return url;
    }

    public static Map<String,String> objectToMap(Object requestParameters) throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();
        // 获取f对象对应类中的所有属性域
        Field[] fields = requestParameters.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            // 获取原来的访问控制权限
            boolean accessFlag = fields[i].isAccessible();
            // 修改访问控制权限
            fields[i].setAccessible(true);
            // 获取在对象f中属性fields[i]对应的对象中的变量
            Object o = fields[i].get(requestParameters);
            if (o != null && StringUtils.isNotBlank(o.toString().trim())) {
                map.put(varName, o.toString().trim());
                // 恢复访问控制权限
                fields[i].setAccessible(accessFlag);
            }
        }
        return map;
    }

}
