package com.viscum;

import com.viscum.entity.User;
import com.viscum.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatisApplicationTests {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserMapper userMapper;

	@Test
	public void contextLoads() {
		List<User> users = userMapper.selectAll();
		for (User user : users) {
			System.out.println(user.toString());
		}
	}

//	@Test
//	public void httpTest() {
//		String filePath = "C:/Users/sh/Desktop/subSectionHTTP.txt";
//		File file = new File(filePath);
//		List<String> r = FileUtils.readLines(file, "UTF-8");
//		int len = r.size();
//		String url = "http://192.168.1.104:8090/manyibaoServer/bus/subsection.do";
//		long startTime = System.currentTimeMillis();
//		for (int i = 0; i < len; i++) {
//			String postStr = r.get(i);
//			JSONObject postData = JSONObject.fromObject(postStr);
//			JSONObject json = HttpCilentUtil.callPost(url, null, postData);
//			System.out.println(json.toString());
//		}
//		long endTime = System.currentTimeMillis();
//		System.out.println("时长："+(endTime - startTime));
//	}

}
