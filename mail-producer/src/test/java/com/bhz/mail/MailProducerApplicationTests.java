package com.bhz.mail;

import com.bhz.mail.entity.MstDict;
import com.bhz.mail.mapper.MstDictMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailProducerApplicationTests {

    public Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource(name = "masterDataSource")
    private DataSource masterDataSource;
    @Resource(name = "slaveDataSource")
    private DataSource slaveDataSource;


    @Test
    public void contextLoads() throws Exception {

        Connection master = masterDataSource.getConnection("root", "123456");
        logger.info("-------------master:{}" , master.getMetaData().getURL());
        Connection slave = slaveDataSource.getConnection("root", "123456");
        logger.info("-------------slave:{}" , slave.getMetaData().getURL());
    }

    @Autowired
    private MstDictMapper mstDictMapper;
    @Test
    public void testMapper(){
//        PageHelper.startPage(1,2);
        List<MstDict> mstDicts = mstDictMapper.findById("1");
        for (MstDict md:mstDicts) {
            System.out.println(md.toString());
        }
    }
}
