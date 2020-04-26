package com.zpb.cloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import javax.xml.ws.Service;
import java.util.List;

/**
 * @dec :
 * @Date: 2020/3/31
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
public interface LoadBlancer {

    ServiceInstance instance(List<ServiceInstance> serviceInstances);

}
