package com.myboard.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReplyDTO {
	private int rnum; //��۹�ȣ
	private int bnum; //�Խù���ȣ
	private String content; //����
	private String writer; //�ۼ���
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul") //������������
	private Date regdate; //�������
	public ReplyDTO() {
		super();
	}
	public ReplyDTO(int rnum, int bnum, String content, String writer, Date regdate) {
		super();
		this.rnum = rnum;
		this.bnum = bnum;
		this.content = content;
		this.writer = writer;
		this.regdate = regdate;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "ReplyDTO [rnum=" + rnum + ", bnum=" + bnum + ", content=" + content + ", writer=" + writer
				+ ", regdate=" + regdate + "]";
	}
	
	
	
}
