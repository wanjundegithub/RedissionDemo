package com.company.desinPatterns.templatePattern;

import org.springframework.stereotype.Component;

@Component
public class BMerchantService extends MerchantService{
    @Override
    protected boolean isRequestByProxy() {
        return false;
    }
}
