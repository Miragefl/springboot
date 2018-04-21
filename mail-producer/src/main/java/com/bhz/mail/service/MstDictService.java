package com.bhz.mail.service;

import com.bhz.mail.config.database.ReadOnlyConnetion;
import com.bhz.mail.entity.MstDict;
import com.bhz.mail.mapper.MstDictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MstDictService {

	@Autowired
	private MstDictMapper mstDictMapper;
	@ReadOnlyConnetion
	public List<MstDict> findById(String id){
		List<MstDict> list = mstDictMapper.findById(id);
		return list;
	}
}
