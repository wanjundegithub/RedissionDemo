package com.company.desinPatterns.strategyPattern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
@Slf4j
public class CFileTypeResolver implements IFileResolver{
    @Override
    public FileTypeEnum getFileType() {
        return FileTypeEnum.FILE_TYPE_C;
    }

    @Override
    public void resolve(Object param) {
        log.info("开始解析类型C文件");
    }
}
