package com.bhz.mail.mapper;

import com.bhz.mail.config.database.BaseMapper;
import com.bhz.mail.entity.MstDict;

import java.util.List;

public interface MstDictMapper extends BaseMapper<MstDict> {

	List<MstDict> findById(String id);
}
