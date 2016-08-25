package com.xiaowu.news.model;

public class Category {
	//类型编号
	private int cid;
	//类型名称
	private String title;
	//类型次数
	private String sequnce;

	private boolean deleted;
	
	public Category() {
		super();
	}

	public Category(int cid, String title) {
		super();
		this.cid = cid;
		this.title = title;
	}

	public Category(int cid, String title, String sequnce) {
		super();
		this.cid = cid;
		this.title = title;
		this.sequnce = sequnce;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSequnce() {
		return sequnce;
	}

	public void setSequnce(String sequnce) {
		this.sequnce = sequnce;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return title;
	}
	
}
