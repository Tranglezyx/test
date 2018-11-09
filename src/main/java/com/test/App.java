package com.test;

import org.apache.commons.io.IOUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Trangle Hello world!
 */
public class App {

    public static void main(String[] args) throws IOException, ScriptException {
        long startTime = System.currentTimeMillis();

        String script = IOUtils.toString(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("test.js"), "utf-8");
        InputStreamReader babelReader = new InputStreamReader(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("script/js/babel.min.js"), "utf-8");
        System.out.println("构建解析babel.min.js输入流费时：" + (System.currentTimeMillis() - startTime));

        ScriptEngine engine = new ScriptEngineManager().getEngineByMimeType("text/javascript");
        System.out.println("构建js引擎费时：" + (System.currentTimeMillis() - startTime));
        SimpleBindings bindings = new SimpleBindings();
        engine.eval(babelReader, bindings);
        System.out.println("js引擎解析babel.min.js费时：" + (System.currentTimeMillis() - startTime));
        bindings.put("input", script);
        Object output = engine.eval("Babel.transform(input, { presets: ['es2015', 'react', 'stage-0'] }).code", bindings);
        System.out.println("js引擎解析代码费时：" + (System.currentTimeMillis() - startTime));
        System.out.println(output);
    }
}
