package com.example.spring_bean_lifecycle;

import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@EqualsAndHashCode
@Component
public class BeanLifeComponent implements DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(BeanLifeComponent.class);
    private int phase = 0;

    public BeanLifeComponent() {
        logPhase("construct");
    }

    @PostConstruct
    public void postConstruct() {
        logPhase("post construct");
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
