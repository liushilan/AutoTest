package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @auther jingsi
 * @date Create on 2021/4/2814:20
 * @hotkeys fori-for循环；sout-控制台输出；psvm-main函数
 */

public class MyCookiesForGet {

    private String url;
    private ResourceBundle bundle;
    // 用来存储cookies
    private CookieStore store;
    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        //从配置文件中 拼接测试的url
        String uri = bundle.getString("getCookies.uri");
        String testUrl = this.url + uri;
//        System.out.println("--------" + testUrl);

        //测试逻辑代码书写
        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result  = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("============" + result);

        // 获取cookies信息
        this.store = client.getCookieStore();
        List<Cookie> cookies_list = store.getCookies();

        for (Cookie c : cookies_list){
            String name = c.getName();
            String value = c.getValue();
            System.out.println("cookies name = " + name +
                    "\ncookies value = " + value);
        }
    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testGetWithCookies() throws IOException {
        // 拼接url+路径
        String uri = bundle.getString("test.get.with.cookies");
        String testurl = this.url + uri;

        HttpGet get = new HttpGet(testurl);
        DefaultHttpClient client = new DefaultHttpClient();

        // 设置cookies信息
        client.setCookieStore(this.store);

        HttpResponse response = client.execute(get);

        // 获取响应的状态码
        int statuscode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode = " + statuscode);

        if (statuscode==200){
            String result = EntityUtils.toString(response.getEntity());
            System.out.println("=======" + result);
        }

    }
}

