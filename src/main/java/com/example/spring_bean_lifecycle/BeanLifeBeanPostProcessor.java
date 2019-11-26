package com.example.spring_bean_lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class BeanLifeBeanPostProcessor implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(BeanLifeBeanPostProcessor.class);

    public BeanLifeBeanPostProcessor() {
        logPhase("construct");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("beanLifeComponent")) {
            logPhase("before initialization");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("beanLifeComponent")) {
            logPhase("after initialization");
        }
        return bean;
    }

    private void logPhase(String str) {
        logger.warn("Configure: {}", str);
    }

}
