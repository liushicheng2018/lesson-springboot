
package com.example.cloud.gatewayclient.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

/**
 * @Description 伪装token
 * @Classname AuthSignFilter
 * @Date 2019/7/10 15:13
     * @Author liusc <liushicheng1993@vip.qq.com>
     * @Version 1.0
 */
@Component
public class AuthSignFilter3  implements GlobalFilter, Ordered {


/**
     * 全局过滤器 核心方法
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        HttpHeaders headers = serverHttpRequest.getHeaders();
        System.out.println("我来过了");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
