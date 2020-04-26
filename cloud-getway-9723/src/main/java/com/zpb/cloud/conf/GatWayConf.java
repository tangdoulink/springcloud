package com.zpb.cloud.conf;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @dec :
 * @Date: 2020/4/1
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@Configuration
public class GatWayConf {

//    @Bean
    public RouteLocator consumerRoute(RouteLocatorBuilder routeLocatorBuilder){

        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        //规定了一个id为baidu_01的网关, 当在路径中出现baidi时,会映射到www.baidu.com的地址
        routes.route("baidu_01",r -> r.path("/baidu").uri("https://www.baidu.com/")).build();

        return routes.build();
    }
}
