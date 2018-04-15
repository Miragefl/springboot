package com.viscum.chapter01;

import com.viscum.chapter01.properties.BlogProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter01ApplicationTests {

    @Autowired
    private BlogProperties blogProperties;

    @Test
    public void getHello() throws Exception {
        Assert.assertEquals(blogProperties.getName(), "fenglei");
        Assert.assertEquals(blogProperties.getTitle(), "spring boot");
        System.out.println("描述：" + blogProperties.getDesc());
        System.out.println("随机字符串：" + blogProperties.getRadomStr());
        System.out.println("随机int：" + blogProperties.getRandomInt());
        System.out.println("随机范围int：" + blogProperties.getRamdonIntRange());
        System.out.println("随机long：" + blogProperties.getRandomLong());
    }

    @Test
    public void contextLoads() {
    }

}
