package com.example.spring_bean_lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class BeanLifeBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(BeanLifeBeanFactoryPostProcessor.class);

    public BeanLifeBeanFactoryPostProcessor() {
        logPhase("construct");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        logPhase("process");
    }

    private void logPhase(String str) {
        logger.warn("Definition: {}", str);
    }

}
