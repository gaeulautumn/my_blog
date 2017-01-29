package com.java.ex.blogdto;

import java.sql.Timestamp;

public class BlogDto {
	private int pId;
	private String pName;
	private String pTitle;
	private String pContent;
	private Timestamp pDate;
	
	public BlogDto(){
	}
	
	public BlogDto(int pId, String pName, String pTitle, String pContent, Timestamp pDate) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.pTitle = pTitle;
		this.pContent = pContent;
		this.pDate = pDate;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpTitle() {
		return pTitle;
	}

	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}

	public String getpContent() {
		return pContent;
	}

	public void setpContent(String pContent) {
		this.pContent = pContent;
	}

	public Timestamp getpDate() {
		return pDate;
	}

	public void setpDate(Timestamp pDate) {
		this.pDate = pDate;
	}
}
