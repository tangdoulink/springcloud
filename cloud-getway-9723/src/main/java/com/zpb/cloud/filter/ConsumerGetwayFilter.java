package com.zpb.cloud.filter;

import com.netflix.ribbon.proxy.annotation.Http;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * @dec :
 * @Date: 2020/4/2
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@Component
@Slf4j
public class ConsumerGetwayFilter implements GlobalFilter,Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("consumer filter...", LocalDateTime.now());
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        if(username == null ){
            log.info("...有非法用户...");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);//经过本次网关后,把请求的信息透传给下一个节点进行验证
    }

    @Override
    public int getOrder() {
        return 0;   //加载过滤器的顺序,数字越小,优先级越高
   }

}
