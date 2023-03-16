package com.stonezpl.hr;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * mybatis-plus 自动生成类
 *
 * @author  zhangpeilei
 */
public class MybatisPlusGen {

    private static String URL = "jdbc:mysql://127.0.0.1:3306/heart_record?serverTimezone=Asia/Shanghai&useUnicode=true" +
            "&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false" +
            "&allowPublicKeyRetrieval=true";
    private static String USERNAME = "root";
    private static String PASSWORD = "stonezpl";

    private static String OUTPUT_DIR = "src/main/java";

    private static String AUTHOR = "stonezpl";

    private static String PARENT_PACKAGE = "com.stonezpl";

    private static String MODULE_NAME = "hr";

    private static String XML_OUTPUT = "/Users/zhangpeilei/code/github/heart-record-java/src/main/resources/mapper";

    public static void main(String[] args) {
        FastAutoGenerator.create(URL,USERNAME,PASSWORD)
                .globalConfig(builder -> {
                    builder.author(AUTHOR) // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(OUTPUT_DIR); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(PARENT_PACKAGE) // 设置父包名
                            .moduleName(MODULE_NAME) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, XML_OUTPUT)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("wx_user") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
