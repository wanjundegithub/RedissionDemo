package com.company.desinPatterns.ChainOfResponsibilityPattern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@Slf4j
public class BHandle extends AbstractChainHandler{
    @Override
    protected void doFilter(Object request, Object response) {
        log.info("处理B事件:"+request+","+response);
    }
}
