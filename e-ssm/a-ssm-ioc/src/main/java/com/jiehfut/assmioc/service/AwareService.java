package com.jiehfut.assmioc.service;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Aware 接口
 * 通过运用感知接口
 *
 */
@Service
public class AwareService implements EnvironmentAware {

    private Environment env;

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    public String getOSType(){
        return env.getProperty("OS");
    }

    

}
