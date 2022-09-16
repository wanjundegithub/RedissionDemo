package com.company.desinPatterns.strategyPattern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BFileTypeResolver implements IFileResolver {
    @Override
    public FileTypeEnum getFileType() {
        return FileTypeEnum.FILE_TYPE_B;
    }

    @Override
    public void resolve(Object param) {
        log.info("开始解析文件类型B:"+param.toString());
    }
}
