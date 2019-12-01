package com.example.spring_bean_lifecycle;

import com.example.spring_bean_lifecycle.annotation.BeforeDestroy;
import com.example.spring_bean_lifecycle.annotation.BeforeInit;
import com.example.spring_bean_lifecycle.util.BeanUtils;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class BeanLifeDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(BeanLifeDestructionAwareBeanPostProcessor.class);
    private static final String BEAN_LIFE = StringUtils.uncapitalize(BeanLifeComponent.class.getSimpleName());

    private final Map<String, Class<?>> beanClassMap = new HashMap<>();

    public BeanLifeDestructionAwareBeanPostProcessor() {
        logPhase("construct");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals(BEAN_LIFE)) {
            logPhase("before initialization");
            beanClassMap.put(beanName, bean.getClass());
        }
        return bean;
    }

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (beanName.equals(BEAN_LIFE)) {
            logPhase("before destruction");
            Class<?> beanClass = beanClassMap.get(beanName);
            BeanUtils.invokeAnnotatedMethod(bean, beanClass, BeforeDestroy.class);
        }
    }

    private void logPhase(String str) {
        logger.debug("Destruction: {}", str);
    }

}
