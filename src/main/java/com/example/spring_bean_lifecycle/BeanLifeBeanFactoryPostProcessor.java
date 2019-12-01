package com.example.spring_bean_lifecycle;

import com.example.spring_bean_lifecycle.annotation.BeanDestroy;
import com.example.spring_bean_lifecycle.annotation.BeanInit;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class BeanLifeBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(BeanLifeBeanFactoryPostProcessor.class);

    public BeanLifeBeanFactoryPostProcessor() {
        logPhase("construct");
    }

    @Override
    @SneakyThrows
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        logPhase("process");
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
            String beanClassName = beanDefinition.getBeanClassName();
            if (beanClassName != null) {
                Class<?> beanClass = Class.forName(beanClassName);
                for (Method method : beanClass.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(BeanInit.class)) {
                        beanDefinition.setInitMethodName(method.getName());
                        logPhase(beanDefinitionName + ": set init method");
                    }
                    if (method.isAnnotationPresent(BeanDestroy.class)) {
                        beanDefinition.setDestroyMethodName(method.getName());
                        logPhase(beanDefinitionName + ": set destroy method");
                    }
                }
            }
        }
    }

    private void logPhase(String str) {
        logger.debug("Definition: {}", str);
    }

}
