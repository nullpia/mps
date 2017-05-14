package com.nullpia.mps.api.common.domain;

import java.io.Serializable;

public class Pagination implements Serializable {
	private int offset;
	private int limit;
	private int rowCnt;
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getRowCnt() {
		return rowCnt;
	}
	public void setRowCnt(int rowCnt) {
		this.rowCnt = rowCnt;
	}
	
	
	
}
