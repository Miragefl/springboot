package com.bhz.mail.mapper;

import com.bhz.mail.config.database.BaseMapper;
import com.bhz.mail.config.database.ReadOnlyConnetion;
import com.bhz.mail.entity.MstDict;

import java.util.List;

public interface MstDictMapper extends BaseMapper<MstDict> {
	@ReadOnlyConnetion
	List<MstDict> findById(String id);
}
