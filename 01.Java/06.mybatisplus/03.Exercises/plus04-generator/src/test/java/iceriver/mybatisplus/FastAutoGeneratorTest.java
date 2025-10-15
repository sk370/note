package iceriver.mybatisplus;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/5 17:54
 */
public class FastAutoGeneratorTest {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:13306/mybatis_plus?characterEncoding=utf-8&userSSL=false", "root", "dimitre123")
                .globalConfig(builder -> {
                    builder.author("zhuyuqi") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\PracticalExercise\\16.MyBatis-Plus\\plus04-generator\\mybatis_plus_generate_code"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("iceriver") // 设置父包名
                            .moduleName("mybatisplus") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\PracticalExercise\\16.MyBatis-Plus\\plus04-generator\\mybatis_plus_generate_code")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user") // 设置需要生成的表名
                            //.addTablePrefix("t_", "c_")// 设置过滤表前缀
                            ;
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
