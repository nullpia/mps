package com.nullpia.mps.api.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nullpia.mps.api.common.mapper.SequenceMapper;

@Service
public class SequenceService {
	
	public static String POST = "POST";
	public static String MENU = "MENU";
	
	
	@Autowired
	private SequenceMapper sequenceMapper;
	
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Long getNewSqno(String sqnoNm) {
		return sequenceMapper.getNewSqno(sqnoNm);
	}
}
