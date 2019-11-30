package com.example.spring_bean_lifecycle;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Data
@Component
//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.DEFAULT)
public class BeanLifeComponent implements InitializingBean, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(BeanLifeComponent.class);
    private int phase = 0;

    public BeanLifeComponent() {
        logPhase("construct");
    }

    @PostConstruct
    public void postConstruct() {
        logPhase("post construct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logPhase("after properties set");
    }

    @EventListener(ContextRefreshedEvent.class)
    public void refreshed() {
        logPhase("context refreshed event");
    }

    @EventListener(ContextClosedEvent.class)
    public void closed() {
        logPhase("context closed event");
    }

    @PreDestroy
    public void preDestroy() {
        logPhase("pre destroy");
    }

    @Override
    public void destroy() {
        logPhase("destroy");
    }

    private void logPhase(String str) {
        logger.warn("Life phase {}: {}", ++phase, str);
    }

}
