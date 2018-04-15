package com.bhz.mail.service;

import com.bhz.mail.mapper.MstDictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MstDictService {

	@Autowired
	private MstDictMapper mstDictMapper;
}
