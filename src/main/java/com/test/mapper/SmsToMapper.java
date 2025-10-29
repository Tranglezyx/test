package com.test.mapper;

import com.company.dm.fix.TemplateParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SmsToMapper {

    Long selectSmsSignId(@Param("sign") String sign,
                         @Param("developerId") String developerId);

    int selectSmsSignTemplateProductCount(@Param("smsSignId") Long smsSignId,
                                          @Param("productId") String productId);

    List<TemplateParam> selectTemplateParamByIdList(@Param("idList") List<String> idList);
}
