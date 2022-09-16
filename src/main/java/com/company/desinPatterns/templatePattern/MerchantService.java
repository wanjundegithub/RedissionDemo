package com.company.desinPatterns.templatePattern;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class MerchantService  {

    public final void handleMerchant(){
        queryMerchantInfo();

        signature();

        httpRequest();

        verifySignature();
    }

    protected void queryMerchantInfo(){
        log.info("查询商户信息");
    }

    protected void signature() {
        log.info("给商户加签");
    }

    protected void httpRequest(){
        log.info("http请求");
    }

    protected void verifySignature(){
        log.info("验证签名");
    }

    protected abstract boolean isRequestByProxy();
}
