package com.swjtu.mybatis.utils;

import com.swjtu.mybatis.exp.FrameException;
import com.swjtu.mybatis.utils.constant.BusiConst;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description mybatis 工具
 * @title MyBatisUtils
 * @version 1.0 by tang rong at 2021-9-26 13:11
 */
public class MyBatisUtils {
    private final static String PATH_SEPARATOR = System.getProperty("file.separator");
    private final static String MYBATIS_ROOT_PATH = System.getProperty("user.dir") + PATH_SEPARATOR + "target" + PATH_SEPARATOR + "classes" + PATH_SEPARATOR;

    static {
        try {
            Properties envProps = new Properties();
            envProps.load(Resources.getResourceAsStream("env.properties"));
            envProps.stringPropertyNames().forEach(x->
                System.getProperties().setProperty(x, envProps.getProperty(x))
            );
        } catch (IOException e) {
        }
    }

    public final static SqlSessionFactory getSqlSessionFactory(String db) {
        String resourceFile = System.getProperty(BusiConst.ENV_KEY) == null || "dev".equals(System.getProperty(BusiConst.ENV_KEY))
                                    ? "mybatis-config.xml"
                                    : String.format("mybatis-config%s.xml", "-"+System.getProperty(BusiConst.ENV_KEY));
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resourceFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        /* 解析mybatis的mapper */
        XMLConfigBuilder parser = new XMLConfigBuilder(inputStream, db, null);
        parser.parse();
        /* 解析指定目录的mapper文件 */
        List<String> files = scanFile(MYBATIS_ROOT_PATH);
        files.forEach(x->loadManualMapper(x, parser.getConfiguration()));
        return new DefaultSqlSessionFactory(parser.getConfiguration());
    }

    private final static void loadManualMapper(String resource, Configuration configuration) {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            System.out.printf("解析mapper文件【%s】失败\n", resource);
            e.printStackTrace();
            throw new FrameException(e.getMessage());
        }
        XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, resource, configuration.getSqlFragments());
        mapperParser.parse();
        System.out.printf("解析mapper文件【%s】成功\n", resource);
    }
    /**
     * @description 获取所有sql文件路径  *  * @param: 文件路径  * @return void
     */
    public final static List<String> scanFile(String path) {
        List<String> filePathList = new ArrayList<>();
        /*打开文件*/
        File dir = new File(path);
        if (!dir.isDirectory()) {
            filePathList.add(dir.getAbsolutePath());
            return filePathList;
        }
        /* 获取文件数组 */
        File[] fileArr = dir.listFiles();
        /* 获取子文件夹列表 */
        LinkedList<File> subDirList = new LinkedList<>(Stream.of(fileArr).filter(File::isDirectory)
                                                                         .collect(Collectors.toList()));
        /* 获取文件列表 */
        filePathList.addAll(
                Stream.of(fileArr).filter(x -> !x.isDirectory())
                        .filter(x -> x.getName().endsWith("mapper.xml")).map(File::getAbsolutePath)
                        .collect(Collectors.toList())
        );
        /* 再次扫描所有子文件夹 */
        while (!subDirList.isEmpty()) {
            File headFile = subDirList.removeFirst();
            File[] files = headFile.listFiles();
            for (File file : files) {
                if (file.isDirectory()) { // file是目录
                     subDirList.add(file);
                } else { // file 是文件
                    if (file.getName().endsWith("mapper.xml")) { // 且file是sql文件
                        filePathList.add(file.getAbsolutePath());
                    }
                }
            }
        }
        return filePathList.stream().map(x->getRelativePath(x)).filter(x->x!=null).collect(Collectors.toList());
    }
    private final static String getRelativePath(String absPath) {
        return absPath.contains(MYBATIS_ROOT_PATH)
                    ? absPath.replace(MYBATIS_ROOT_PATH, "")
                    : null;
    }
}


