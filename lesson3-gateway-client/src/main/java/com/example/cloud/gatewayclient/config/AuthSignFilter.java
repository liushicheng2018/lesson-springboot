/*
package com.example.cloud.gatewayclient.config;

import com.alibaba.fastjson.JSON;
import com.example.cloud.gatewayclient.pojo.ITest;
import com.example.cloud.gatewayclient.pojo.TestO;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import io.netty.buffer.ByteBufAllocator;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

*/
/**
 * @Description TODO
 * @Classname AuthSignFilter
 * @Date 2019/7/10 15:13
 * @Author liusc <liushicheng1993@vip.qq.com>
 * @Version 1.0
 *//*


@Component
public class AuthSignFilter implements GlobalFilter, Ordered {

    ////初始化  后期从数据库获取
    public final static ConcurrentHashMap<String,String> urlGlobalMap = new ConcurrentHashMap();
    public final static ConcurrentHashMap<String,String> GlobalEurakaMap = new ConcurrentHashMap();

    static {
        urlGlobalMap.put("test","CLIENT-A");
        urlGlobalMap.put("pro","CLIENT-A");
        GlobalEurakaMap.put("test","http://admin:admin@localhost:10005/eureka/");
        GlobalEurakaMap.put("pro","http://admin:admin@localhost:10006/eureka/");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        String method = serverHttpRequest.getMethodValue();
        URI uri = serverHttpRequest.getURI();
        MultiValueMap<String, HttpCookie> cookies = serverHttpRequest.getCookies();
        HttpHeaders headers = serverHttpRequest.getHeaders();
        List<String> cookie = headers.get("Cookie");
        boolean flag = false;
        if (!CollectionUtils.isEmpty(cookie)){
            for (String s : cookie) {
                if (s.contains("test")) {
                    flag = true;
                    break;
                }
            }
        }
        System.out.println("cookies:{}"+ToStringBuilder.reflectionToString(cookies));
        EurekaClient client = null;
        if (flag){
            client = eurakaConfig.getEurakaConfig(GlobalEurakaMap.get("test"));
        }else {
            client = eurakaConfig.getEurakaConfig(GlobalEurakaMap.get("pro"));
        }
        ServerHttpResponse response = exchange.getResponse();
        if ( null == client) {
            ////无法获取,返回授权不成功
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        String valueService = "";
        if (uri != null ){
            String urlStr = uri.toString();
            Set<Entry<String, String>> entries = urlGlobalMap.entrySet();
            for (Entry<String, String> tempObject : urlGlobalMap.entrySet()){
                if (urlStr.contains(tempObject.getKey())) {
                    valueService = tempObject.getValue();
                    break;
                }
            }
        }
        if (uri.toString().contains("get")){
            InstanceInfo info = client.getNextServerFromEureka(valueService, false);
            System.out.println(JSON.toJSONString(info));
            System.out.println(info.getHomePageUrl());
            ITest test = Feign.builder()
                    .decoder(new JacksonDecoder())
                    .encoder(new JacksonEncoder())
                    .target(ITest.class, info.getHomePageUrl());
            byte[] datas = JSON.toJSONString(test.get()).getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(datas);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
        }

        if ("POST".equals(method)) {
            //从请求里获取Post请求体
            String bodyStr = resolveBodyFromRequest(serverHttpRequest);
            //TODO 得到Post请求的请求参数后，做你想做的事

            {
                InstanceInfo info = client.getNextServerFromEureka(valueService, false);
                System.out.println(JSON.toJSONString(info));
                System.out.println(info.getHomePageUrl());
                ITest test = Feign.builder()
                        .decoder(new JacksonDecoder())
                        .encoder(new JacksonEncoder())
                        .target(ITest.class, info.getHomePageUrl());
                Object parse = JSON.parse(bodyStr);
                String set = test.set(parse);
                byte[] datas = JSON.toJSONString("test").getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = response.bufferFactory().wrap(datas);
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                return response.writeWith(Mono.just(buffer));
            }

            */
/*//*
/下面的将请求体再次封装写回到request里，传到下一级，否则，由于请求体已被消费，后续的服务将取不到值
            ServerHttpRequest request = serverHttpRequest.mutate().uri(uri).build();
            DataBuffer bodyDataBuffer = stringBuffer(bodyStr);
            Flux<DataBuffer> bodyFlux = Flux.just(bodyDataBuffer);

            request = new ServerHttpRequestDecorator(request) {
                @Override
                public Flux<DataBuffer> getBody() {
                    return bodyFlux;
                }
            };
            //封装request，传给下一级
            return chain.filter(exchange.mutate().request(request).build());*//*


        } else if ("GET".equals(method)) {

            Map requestQueryParams = serverHttpRequest.getQueryParams();

            {
                InstanceInfo info = client.getNextServerFromEureka(valueService, false);
                System.out.println(JSON.toJSONString(info));
                System.out.println(info.getHomePageUrl());
                ITest test = Feign.builder()
                        .decoder(new JacksonDecoder())
                        .encoder(new JacksonEncoder())
                        .target(ITest.class, info.getHomePageUrl());
                //Object parse = JSON.parse(bodyStr);
                String set = test.set(1);
                byte[] datas = JSON.toJSONString(test.get()).getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = response.bufferFactory().wrap(datas);
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                return response.writeWith(Mono.just(buffer));
            }
           */
/* if (!cookies.containsKey("token")){
                //TODO 得到Get请求的请求参数后，做你想做的事
                System.out.println("cookies:{}"+ToStringBuilder.reflectionToString(cookies));
                response.setStatusCode(HttpStatus.SEE_OTHER);
                String url = "http://localhost:10020/demo/exception?key=abc&count=2";
                response.getHeaders().set(HttpHeaders.LOCATION, url);
                return response.setComplete();
            }*//*

        }
        return chain.filter(exchange);
    }

    */
/**
     * 从Flux<DataBuffer>中获取字符串的方法
     * @return 请求体
     *//*

    private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
        //获取请求体
        Flux<DataBuffer> body = serverHttpRequest.getBody();

        AtomicReference<String> bodyRef = new AtomicReference<>();
        body.subscribe(buffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
            DataBufferUtils.release(buffer);
            bodyRef.set(charBuffer.toString());
        });
        //获取request body
        return bodyRef.get();
    }

    private DataBuffer stringBuffer(String value) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);

        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
        DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
        buffer.write(bytes);
        return buffer;
    }

    @Override
    public int getOrder() {
        return -1;
    }
}*/
