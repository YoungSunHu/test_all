/*
package com.hqy.ApiTest.controller;


import com.hqy.ApiTest.Util.SignUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * @Auther: cjw
 * @Date: 2018/11/26 19:12
 * @Description: 测试控制器
 * <p>
 * 模拟客户端请求API接口
 * @param request
 * @return 模拟服务的API接口
 * @param request
 * @return
 *//*

@RestController
public class SignTestController {

    */
/**
 * 模拟客户端请求API接口
 *
 * @param request
 * @return
 *//*

    @RequestMapping("send")
    public String send(HttpServletRequest request) {
        Map<String, String> param = new HashMap();
        param.put("userId", "9527");
        param.put("amount", "9.99");
        param.put("productId", "9885544154");
        param.put("secretKey", "mysecret123456");
        try {
            String postResult = HttpClient.post("http://localhost:8099/test", SignUtil.sign(param));
            return postResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    */
/**
 * 模拟服务的API接口
 *
 * @param request
 * @return
 *//*

    @RequestMapping("checkSign")
    public String checkSign(HttpServletRequest request) {
        //从request中获取参数列表，转成map
        Map<String, String> map = SignUtil.toVerifyMap(request.getParameterMap(), false);
        String secretKey = map.get("secretKey");
        if (StringUtils.isEmpty(secretKey) || !map.get("secretKey").equals(SignUtil.SECRET_KEY)) {
            System.out.println("secretKey is err");
            return "fail";
        }
        if (SignUtil.verify(map)) {
            return "success";
        } else {
            return "fail";
        }
    }

}
*/
