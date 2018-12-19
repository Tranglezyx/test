package com.test;

import com.test.annotation.GeneratedValue;
import com.test.annotation.Id;
import com.test.dto.User;
import com.test.kafka.KafkaTest;
import com.test.zookeeper.ZookeeperTest;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.zookeeper.KeeperException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author Trangle Hello world!
 */
public class App {

    public static void main(String[] args) throws IOException, ScriptException, JSQLParserException, KeeperException, InterruptedException {
//        long startTime = System.currentTimeMillis();
//
//        String script = IOUtils.toString(Thread.currentThread().getContextClassLoader()
//                .getResourceAsStream("test.js"), "utf-8");
//        InputStreamReader babelReader = new InputStreamReader(Thread.currentThread().getContextClassLoader()
//                .getResourceAsStream("script/js/babel.min.js"), "utf-8");
//        System.out.println("构建解析babel.min.js输入流费时：" + (System.currentTimeMillis() - startTime));
//
//        ScriptEngine engine = new ScriptEngineManager().getEngineByMimeType("text/javascript");
//        System.out.println("构建js引擎费时：" + (System.currentTimeMillis() - startTime));
//        SimpleBindings bindings = new SimpleBindings();
//        engine.eval(babelReader, bindings);
//        System.out.println("js引擎解析babel.min.js费时：" + (System.currentTimeMillis() - startTime));
//        bindings.put("input", script);
//        Object output = engine.eval("Babel.transform(input, { presets: ['es2015', 'react', 'stage-0'] }).code", bindings);
//        System.out.println("js引擎解析代码费时：" + (System.currentTimeMillis() - startTime));
//        System.out.println(output);

//        KafkaTest.producer();
//        KafkaTest.consumer();

        ZookeeperTest dm = new ZookeeperTest();
        dm.createZKInstance();
        dm.ZKOperations();
        dm.ZKClose();
    }


}
