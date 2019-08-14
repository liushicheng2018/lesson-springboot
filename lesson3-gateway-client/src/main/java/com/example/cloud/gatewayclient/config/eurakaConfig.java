package com.example.cloud.gatewayclient.config;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.commons.util.InetUtilsProperties;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.cloud.netflix.eureka.InstanceInfoFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Classname eurakaConfig
 * @Date 2019/7/15 11:19
 * @Author liusc <liushicheng1993@vip.qq.com>
 * @Version 1.0
 */
public class eurakaConfig {

    eurakaConfig(){

    }

    public static EurekaClient getEurakaConfig(String euraka){
    try{
            InetUtilsProperties properties = new InetUtilsProperties();
            InetUtils utils = new InetUtils(properties);
            EurekaInstanceConfig config = new EurekaInstanceConfigBean(utils);
            InstanceInfo instanceInfo = new InstanceInfoFactory().create(config);
            ApplicationInfoManager applicationInfoManager = new ApplicationInfoManager(config, instanceInfo);
            EurekaClientConfigBean cfg = new EurekaClientConfigBean();
            Map<String, String> pp = new HashMap<>();
            pp.put("defaultZone", euraka);
            cfg.setServiceUrl(pp);
            EurekaClient eurekaClient = new com.netflix.discovery.DiscoveryClient(applicationInfoManager, cfg);
            DiscoveryClient client = new EurekaDiscoveryClient(config, eurekaClient);
            return eurekaClient;
        }catch (Exception ee){
            ee.printStackTrace();
        }
            return null;
    }
}
