package com.course.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @auther jingsi
 * @date Create on 2021/4/2811:49
 * @hotkeys fori-for循环；sout-控制台输出；psvm-main函数
 */

public class MyHttpClient {

    @Test
    public void test1() throws IOException {
        //用来存放我们的结果
        String result;
        HttpGet get =  new HttpGet("http://www.baidu.com");
        //创建httpclient的对象
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity());
        System.out.println("返回httpclient框架的结果：" + result);
    }
}

