package com.example.spring_bean_lifecycle.util;

import lombok.SneakyThrows;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class BeanUtils {

    private BeanUtils() {
    }

    @SneakyThrows
    public static void invokeAnnotatedMethod(Object bean, Class<?> beanClass, Class<? extends Annotation> annotationClass) {
        for (Method method : beanClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotationClass)) {
                Method beanMethod = bean.getClass().getMethod(method.getName());
                beanMethod.invoke(bean);
            }
        }
    }

}
