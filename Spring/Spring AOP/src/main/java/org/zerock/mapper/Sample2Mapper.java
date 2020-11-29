package org.zerock.mapper;

import org.apache.ibatis.annotations.Insert;

public interface Sample2Mapper {
	@Insert("insert into tbl_sample2 (col2) values (#{data}) ")
	public int inserCol2(String data);
}
