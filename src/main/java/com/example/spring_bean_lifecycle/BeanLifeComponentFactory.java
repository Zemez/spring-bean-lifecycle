package com.example.spring_bean_lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;

//@Component
public class BeanLifeComponentFactory extends AbstractFactoryBean<BeanLifeComponent> {

    private static final Logger logger = LoggerFactory.getLogger(BeanLifeComponentFactory.class);

    public BeanLifeComponentFactory() {
        logPhase("construct");
    }

    @Override
    public Class<?> getObjectType() {
//        logPhase("get object type");
        return BeanLifeComponent.class;
    }

    @Override
    protected BeanLifeComponent createInstance() throws Exception {
        logPhase("create instance");
        return BeanLifeComponent.factoryBean();
    }

    private void logPhase(String str) {
        logger.debug("FactoryBean: {}", str);
    }

}
