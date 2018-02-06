package com.example.administrator.nyxxcqyz;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 李海慧 on 2018-02-01.
 */

public  class HttpRequest {
    /**
     * 获取网络中的JSON数据

     * @return
     * @throws Exception
     */
    public static void main(String args[]){
        String path = "http://10.164.13.245:8080/zhcc/nx/login.action?userId=2&password=zhcc";
        try {
           String str= getHttpURL(path,"GET");
            List cc= getJSONObject(str);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static byte[] readStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            bout.write(buffer, 0, len);
        }
        bout.close();
        inputStream.close();
        return bout.toByteArray();
    }

    public static String  getHttpURL(String path,String requestMethod) throws Exception {

        URL url = new URL(path);
        // 利用HttpURLConnection对象，我们可以从网页中获取网页数据
        HttpURLConnection conn = null;
        // 单位为毫秒，设置超时时间为5秒
        conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(15 * 1000);
        String returnStr=null;
        // HttpURLConnection对象是通过HTTP协议请求path路径的，所以需要设置请求方式，可以不设置，因为默认为get
        conn.setRequestMethod(requestMethod);
        if (conn.getResponseCode() == 200) {// 判断请求码是否200，否则为失败
            InputStream is = conn.getInputStream(); // 获取输入流
            byte[] data = readStream(is); // 把输入流转换成字符串组
            returnStr = new String(data); // 把字符串组转换成字符串
        }
        return returnStr;
    }


    public static List<Map<String, String>> getJSONObject(String json)
            throws Exception {

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        // 数据形式：{"total":2,"success":true,"arrayData":[{"id":1,"name":"张三"},{"id":2,"name":"李斯"}]}
        JSONObject jsonObject = new JSONObject(json); // 返回的数据形式是一个Object类型，所以可以直接转换成一个Object
        int total = jsonObject.getInt("count");
        String keywords = jsonObject.getString("keywords");

        // 里面有一个数组数据，可以用getJSONArray获取数组
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for (int i = 1; i < jsonArray.length(); i++) {
            JSONObject item = jsonArray.getJSONObject(i); // 得到每个对象
            int id = item.getInt("id");
            String title = item.getString("title");
            String description = item.getString("description");
            int time = item.getInt("time");
            map = new HashMap<String, String>();
            map.put("id", id + "");
            map.put("title", title);
            map.put("description", description);
            map.put("time", time + "");
            list.add(map);
        }

        return list;
    }


}
