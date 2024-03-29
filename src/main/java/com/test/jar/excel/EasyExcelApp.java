package com.test.jar.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class EasyExcelApp {

    public static void main(String[] args) throws IOException {
//        String fileName = "D:\\data\\phone\\DMPhone.csv";
//        String fileName = "D:\\data\\phone\\DMPhone.xls";
        String fileName = "D:\\data\\phone\\DMPhone.xlsx";
        File file = new File(fileName);
        DefaultExcelReadListener readListener = new DefaultExcelReadListener();
        EasyExcel.read(file, readListener).excelType(ExcelTypeEnum.XLSX).sheet().headRowNumber(0).doRead();
        List dataList = readListener.getDataList();
        System.out.println(JSON.toJSONString(dataList));
    }
}
