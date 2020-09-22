package com.myboard.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReplyDTO {
	private int rnum; //댓글번호
	private int bnum; //게시물번호
	private String content; //내용
	private String writer; //작성자
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul") //포맷형식지정
	private Date regdate; //등록일자
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
