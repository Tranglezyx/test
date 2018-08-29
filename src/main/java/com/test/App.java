package com.test;

import com.test.util.CommUtils;
import net.sf.jsqlparser.JSQLParserException;

/**
 * @author Trangle Hello world!
 */
public class App {

    public static void main(String[] args) throws JSQLParserException {
        System.out.println(CommUtils.judgeDataNotNull(1,1,null,null));
    }
}
