package com.example.spring_bean_lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class BeanLifeDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(BeanLifeDestructionAwareBeanPostProcessor.class);

    public BeanLifeDestructionAwareBeanPostProcessor() {
        logPhase("construct");
    }

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (beanName.equals("beanLifeComponent")) {
            logPhase("before destruction");
        }
    }

    private void logPhase(String str) {
        logger.warn("Destruct: {}", str);
    }

}
