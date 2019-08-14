/*

package com.example.cloud.gatewayclient.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.*;

*/
/**
 * @Description 伪装token
 * @Classname AuthSignFilter
 * @Date 2019/7/10 15:13
 * @Author liusc <liushicheng1993@vip.qq.com>
 * @Version 1.0
 *//*

@Component
public class AuthSignFilter2 implements GlobalFilter, Ordered {


*/
/**
     * 全局过滤器 核心方法
     * @param exchange
     * @param chain
     * @return
     *//*

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        HttpHeaders headers = serverHttpRequest.getHeaders();
        URI uri = serverHttpRequest.getURI();
        System.out.println("我想换地址");
        URI requestUrl = exchange.getRequiredAttribute(GATEWAY_REQUEST_URL_ATTR);
        try {
            String url = "http://www.baidu.com/";
            exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, new URI(url));
            Route route = (Route)exchange.getAttribute(GATEWAY_ROUTE_ATTR);
            URI routeUri = new URI(url);
            String scheme = routeUri.getScheme();
            Object attribute = exchange.getAttribute(URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            System.out.println(attribute);
            return chain.filter(exchange);
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        } System.out.println(GATEWAY_REQUEST_URL_ATTR);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 11;
    }
}
*/
