package com.nullpia.mps.api.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SequenceMapper {
	
	@Select("SELECT FN_GET_NEW_SQNO(#{sqnoNm})")
	Long getNewSqno(String sqnoNm);
}
	