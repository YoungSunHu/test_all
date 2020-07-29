package com.hqy.httpTest;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;

public class HikIBuilding {

    public static void main(String[] args) {

        Date date = new Date();
        String key = "29991987";
        String secret = "SIQTdn9Zu3wAaM0F68GI";
        String httpHeaders = "POST" + "\n" + "/" + "\n" + "Contenmd5" + "\n" + "application/json" + "\n" + date.toString() + "\n";
        String customHeaders = "x-ca-key" + ":" + key + "\n";
        Util util = new Util();

        try {
            //设置url
            String url = "/api/acs/v1/door/events";
            String ipAddr = "https://203.110.216.130:430" + url;

            HttpPost httpPost = new HttpPost(ipAddr);
            //编辑body实体类
            DoorDTO doorDTO = new DoorDTO();
            doorDTO.setPageNo(1);
            doorDTO.setPageSize(10);
            doorDTO.setStartTime("2020-01-04T16:39:00Z");
            doorDTO.setStartTime("2020-01-05T16:39:00Z");
            //转换json
            Gson gson = new Gson();
            String body = gson.toJson(doorDTO);
            //entity放入httpPost
            StringEntity stringEntity = new StringEntity(body, "UTF-8");
            httpPost.setEntity(stringEntity);


            //header参数处理
            String Contenmd5 = Base64.getEncoder().encodeToString(body.getBytes("UTF-8"));
            String XCaSignature = util.HMACSHA256(httpHeaders + customHeaders + url, secret);
            httpPost.setHeader("Accept", "/");
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Content-MD5", Contenmd5);
            httpPost.setHeader("Date", date.toString());
            httpPost.setHeader("X-Ca-Key", key);
            httpPost.setHeader("X-Ca-Signature", Base64.getEncoder().encodeToString(XCaSignature.getBytes("UTF-8")));
            httpPost.setHeader("X-Ca-Signature-Headers", "x-ca-key");


            //httpClientBuilder获取CloseableHttpClient执行httpPost获取CloseableHttpResponse
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
            CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String s1 = EntityUtils.toString(entity);
            System.out.println(response);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

    }
}
