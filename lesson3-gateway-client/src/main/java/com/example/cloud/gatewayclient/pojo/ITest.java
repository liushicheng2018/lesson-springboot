package com.example.cloud.gatewayclient.pojo;

import com.example.cloud.gatewayclient.config.RequestTimeGatewayFilterFactory;
import feign.Headers;
import feign.RequestLine;

import java.util.HashSet;
import java.util.Map;

/**
 * @Auther: hcsy
 * @Date: 2019-07-12 14:10
 * @Description:
 */
public interface ITest {
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /simple/sequence/next")
    HystreamResultVo<Integer> nextSequence(CfgSequence request);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /exception")
    String testException();

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /set")
    String set(Object t);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /get")
    Map<String,Object> get();

    public static void main(String[] args) {
        String he = "hello";
        String llo = "he" + new String("llo");
        System.out.println(he == llo);




    }
}
