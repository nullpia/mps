package com.nullpia.mps.api.blog.domain;

public class Post {
	private int postId;
	private String postTitl;
	private String postCn;
	private int	readCnt;
	private String creUsrId;
	private String creDttm;
	private String modUsrId;
	private String modDttm;
	private Long catgId;
	private String catgNm;
	
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getPostTitl() {
		return postTitl;
	}
	public void setPostTitl(String postTitl) {
		this.postTitl = postTitl;
	}
	public String getPostCn() {
		return postCn;
	}
	public void setPostCn(String postCn) {
		this.postCn = postCn;
	}
	public int getReadCnt() {
		return readCnt;
	}
	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}
	public String getCreUsrId() {
		return creUsrId;
	}
	public void setCreUsrId(String creUsrId) {
		this.creUsrId = creUsrId;
	}
	public String getCreDttm() {
		return creDttm;
	}
	public void setCreDttm(String creDttm) {
		this.creDttm = creDttm;
	}
	public String getModUsrId() {
		return modUsrId;
	}
	public void setModUsrId(String modUsrId) {
		this.modUsrId = modUsrId;
	}
	public String getModDttm() {
		return modDttm;
	}
	public void setModDttm(String modDttm) {
		this.modDttm = modDttm;
	}
	public Long getCatgId() {
		return catgId;
	}
	public void setCatgId(Long catgId) {
		this.catgId = catgId;
	}
	public String getCatgNm() {
		return catgNm;
	}
	public void setCatgNm(String catgNm) {
		this.catgNm = catgNm;
	}
	
}
