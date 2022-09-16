package com.company.desinPatterns.ChainOfResponsibilityPattern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@Slf4j
public class AHandle extends AbstractChainHandler {

    @Override
    protected void doFilter(Object request, Object response) {
        log.info("处理A事件:"+request+","+response);
    }
}
