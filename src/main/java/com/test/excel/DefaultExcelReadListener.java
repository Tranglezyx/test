package com.test.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Getter
public class DefaultExcelReadListener extends AnalysisEventListener {

    private final List<Map<Integer,String>> dataList = new ArrayList<>();

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        log.info("{}", JSONObject.toJSONString(o));
        if(o instanceof Map){
            dataList.add((Map<Integer, String>) o);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("");
    }
}
