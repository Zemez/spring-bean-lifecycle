package com.example.spring_bean_lifecycle;

import com.example.spring_bean_lifecycle.annotation.*;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Data
@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BeanLifeComponent implements InitializingBean, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(BeanLifeComponent.class);
    private static int phase;

    public static BeanLifeComponent factoryBean() {
        logPhase("bean definition factory bean");
        return new BeanLifeComponent();
    }

    private BeanLifeComponent() {
        logPhase("construct");
    }

    @BeanFactory
    public static BeanLifeComponent factory() {
        phase = 0;
        logPhase("bean definition factory method");
        return new BeanLifeComponent();
    }

    @BeforeInit
    public void beforeInit() {
        logPhase("before init post process");
    }

    @PostConstruct
    public void postConstruct() {
        logPhase("post construct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logPhase("after properties set");
    }

    @BeanInit
    public void beanInit() {
        logPhase("bean definition init");
    }

    @AfterInit
    public void afterInit() {
        logPhase("after init post process");
    }

    @EventListener(ContextRefreshedEvent.class)
    public void refreshedEvent() {
        logPhase("context refreshed event");
    }

    @EventListener(ContextClosedEvent.class)
    public void closedEvent() {
        logPhase("context closed event");
    }

    @BeforeDestroy
    public void beforeDestroy() {
        logPhase("before destroy post process");
    }

    @PreDestroy
    public void preDestroy() {
        logPhase("pre destroy");
    }

    @Override
    public void destroy() {
        logPhase("destroy");
    }

    @BeanDestroy
    public void beanDestroy() {
        logPhase("bean definition destroy");
    }

    private static void logPhase(String str) {
        logger.warn("Life phase {}: {}", ++phase, str);
    }

}
