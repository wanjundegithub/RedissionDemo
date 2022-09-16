package com.company.desinPatterns.templatePattern;

import org.springframework.stereotype.Component;

@Component
public class AMerchantService extends MerchantService{
    @Override
    protected boolean isRequestByProxy() {
        return true;
    }
}
