package com.example.cloud.gatewayclient.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR;

/**
 * @Description TODO
 * @Classname RequestTimeGatewayFilterFactory
 * @Date 2019/7/10 11:54
 * @Author liusc <liushicheng1993@vip.qq.com>
 * @Version 1.0
 */
@Component
public class RequestTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestTimeGatewayFilterFactory.Config> {


    @PostConstruct
    public void PostConstruct(){
        System.out.println("sss");
    }

    private static final Log log = LogFactory.getLog(GatewayFilter.class);
    private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";
    private static final String KEY = "withParams";
    public final static Map<String,List<String>> GlobalUserMap = new HashMap<>();

    static {
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        GlobalUserMap.put("uid",list);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(KEY);
    }

    public RequestTimeGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {
            //取得参数  判断
            ServerHttpRequest req = exchange.getRequest();
            String rawPath = req.getURI().getRawPath();
            exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
            ////判断是否是灰度
            String url = "http://h5.993861.net:8000/api/hystream/menu/kyjzfw?uid=2222&sid=5c9f2393f835656580fa4c12";
            boolean flag = false;
            MultiValueMap<String, String> queryParams = req.getQueryParams();
            if (queryParams.containsKey("uid")) {
                for (String uid : GlobalUserMap.get("uid")) {
                    if (queryParams.get("uid").contains(uid)) {
                        flag = true;
                        url = "http://beta.h5.993861.net:8000/api/hystream/menu/kyjzfw?uid="+queryParams.get("uid")+"&sid=5c9f2393f835656580fa4c12";
                        break;
                    }
                }
            }


            URI requestUrl = UriComponentsBuilder.fromHttpUrl(url).build().toUri();
            ServerHttpRequest request = req.mutate().uri(requestUrl).build();
            Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
            ////构建路由 覆盖路由
            Route newRoute = Route.async()
                    .asyncPredicate(route.getPredicate())
                    .filters(route.getFilters())
                    .id(route.getId())
                    .order(route.getOrder())
                    .uri(requestUrl).build();
            exchange.getAttributes().put(GATEWAY_ROUTE_ATTR,newRoute);
           // exchange.getAttributes().put(GATEWAY_ORIGINAL_REQUEST_URL_ATTR, url);

            return chain.filter(exchange).then(
                    Mono.fromRunnable(() -> {
                        Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
                        if (startTime != null) {
                            StringBuilder sb = new StringBuilder(req.getURI().getRawPath())
                                    .append(": ")
                                    .append(System.currentTimeMillis() - startTime)
                                    .append("ms");
                            if (config.isWithParams()) {
                                sb.append(" params:").append(queryParams);
                            }
                            log.info(sb.toString());
                        }
                    })
            );
        };
    }

    public static class Config {

        private boolean withParams;

        public boolean isWithParams() {
            return withParams;
        }

        public void setWithParams(boolean withParams) {
            this.withParams = withParams;
        }

    }
}