package com.swjtu.mybatis.main;

import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class Main2 {
    public static void main(String[] args) {
        main3();
    }
    // 自定义变量
    public static void main3() {
        Properties envProps = new Properties();
        try {
            // 使用 mybatis Resources工具读取环境属性文件
            envProps.load(Resources.getResourceAsStream("env.properties"));
        } catch (IOException e) {
        }
        // 把自定义变量填充到系统变量 SystemProperties
        envProps.stringPropertyNames().forEach(x->{
            System.getProperties().setProperty(x, envProps.getProperty(x));
        });
        System.getProperties().list(System.out);
        System.out.println("==================================");
        Properties sysProps = System.getProperties();
        System.out.println("user.dir=" + sysProps.getProperty("user.dir"));
        System.out.println("java.class.path=" + sysProps.getProperty("java.class.path"));
        System.out.println("sun.java.command=" + sysProps.getProperty("sun.java.command"));
        System.out.println("file.separator=" + sysProps.getProperty("file.separator"));
        System.out.println("file.encoding=" + sysProps.getProperty("file.encoding"));
    }

    public static void main2() {
        Properties props = System.getProperties();
        props.list(System.out);
    }
    public static void main1() {
        Map<String, String> envMap = System.getenv();
        envMap.entrySet().forEach(x-> System.out.println(x.getKey() + "=" + x.getValue()));
    }
}
