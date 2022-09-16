package com.company;

import com.company.desinPatterns.ChainOfResponsibilityPattern.ChainHandle;
import com.company.desinPatterns.observablePattern.Observable;
import com.company.desinPatterns.strategyPattern.FileTypeEnum;
import com.company.desinPatterns.strategyPattern.StrategyFileResolver;
import com.company.desinPatterns.templatePattern.MerchantHandle;
import com.company.desinPatterns.templatePattern.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class strategyPatternTest {

    @Autowired
    private StrategyFileResolver strategyFileResolver;

    @Autowired
    private ChainHandle chainHandle;

    @Autowired
    private MerchantHandle merchantHandle;

    @Autowired
    private Observable observable;

    @Test
    public void testFileResolver(){
        strategyFileResolver.FileResolver(FileTypeEnum.FILE_TYPE_A,"File_Type_A");
    }

    @Test
    public  void testChainHandle(){
        chainHandle.exec("request","response");
    }

    @Test
    public void testMerchantHandle(){
        merchantHandle.handleMerchant();
    }

    @Test
    public void testObservable(){
        observable.setState(1);
        observable.notifyAllObservers();
    }
}
