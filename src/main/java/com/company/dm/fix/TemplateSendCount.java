package com.company.dm.fix;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhouyunxiang
 * @Date: 2025/10/15 17:05
 * @Description:
 */
@Data
@Slf4j
public class TemplateSendCount {

    @ExcelProperty(value = "developerId")
    private String developerId;
    @ExcelProperty(value = "productId")
    private String productId;
    @ExcelProperty(value = "sign")
    private String sign;
    @ExcelProperty(value = "templateId")
    private String templateId;
    @ExcelProperty(value = "count")
    private String count;

    public static class ImportExcelDataListener<T> extends AnalysisEventListener<T>{
        private List<T> list = new ArrayList<T>();

        public List<T> getDatas() {
            return list;
        }

        public void setDatas(List<T> list) {
            this.list = list;
        }

        /**
         * 这个每一条数据解析都会来调用
         *
         * @param data
         * one row value. Is is same as {@link AnalysisContext#readRowHolder()}
         * @param context
         */
        @Override
        public void invoke(T data, AnalysisContext context) {
            list.add(data);
        }

        /**
         * 所有数据解析完成了 都会来调用
         *
         * @param context
         */
        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            log.info("所有数据解析完成！");
        }
    }
}
