package com.test.mapper;

import com.test.entity.SmsTo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SmsToMapper {

    int batchInsert(@Param("list") List<SmsTo> list);

    int batchInsertUpdateOnDuplicateKey(@Param("list") List<SmsTo> list);
}
