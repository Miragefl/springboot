package com.viscum.controller;

import com.viscum.entity.User;
import com.viscum.exception.FailException;
import com.viscum.mapper.UserMapper;
import com.viscum.utils.AliPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserMapper userMapper;


	@RequestMapping("/selectAll.do")
	public String selectAll() {
		List<User> users = userMapper.selectAll();
		for (User user : users) {
			System.out.println(user.toString());
		}
		return null;
	}

	@RequestMapping(value="/testSign.do",produces={"application/json;charset=UTF-8"})
	public void testSign(@RequestBody Map<String,String> reqMap) throws Exception {
		String aliPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqvIU2xZOgpnttZ75+kfeC8icr2FCW51WWFt7FUcfiHv+4rSR6O7i9iEErdjhqlHfgifMb8EbUMyTRgkbSFm1so9J8G+8Rk3YBZDW1H4Nhwp3b2W8CV8NRik6zyGASE1JW6gBpZf/PSv/A3DtWAhb8FAkw3aZqg2NOmDXKuaTuhLaKtpzUlduFcZpu45YA+Dp+NoY8+K908z9xRDiondtNW3/TbGQ2unBshHTamf4uIEHDJeK7DkpomA2WtvO/PJb8yhS+TH5MUooHS6enp1HrMU/CUVxD4K8lwsU3tNqphLcJDpph0oGWcxTE5JTXVq9wC7Uq7Ar84qWjG54LHHOoQIDAQAB";
		AliPayUtil.checkSign(reqMap,aliPublicKey);
	}

}
