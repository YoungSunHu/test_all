package com.hqy.httpTest;

import com.alibaba.druid.support.json.JSONUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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


public class httpClientTest {
    private static TestDTO testDTO = new TestDTO();

    public static void main(String[] args) {
        try {
            HttpPost httpPost = new HttpPost("http://127.0.0.1:8888/web/getAccessToken");
            //post参数,放入entity
            testDTO.setAccessID("636970D2B70E4D8A93FCA52296960AA9");
            testDTO.setAccessSecret("8E070965D57044A0A8C08B30D9741474");
            Gson gson = new Gson();
            String s = gson.toJson(testDTO);
            StringEntity stringEntity = new StringEntity(s, "UTF-8");
            //entity放入httpPost
            httpPost.setEntity(stringEntity);
            httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
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
        }
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

    }
}
