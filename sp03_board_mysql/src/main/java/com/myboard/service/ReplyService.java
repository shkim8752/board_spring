package com.myboard.service;

import java.util.List;

import com.myboard.dto.ReplyDTO;

//답글
public interface ReplyService {
	//추가
	public int insert(ReplyDTO dto) throws Exception;
	//변경
	public int update(ReplyDTO dto) throws Exception;
	//삭제
	public int delete(int rnum,int bnum) throws Exception;
	//전체검색
	public List<ReplyDTO> selectList(int bnum) throws Exception;
	//한건검색 
	public ReplyDTO selectOne(int num) throws Exception;
	//게시물에 해당하는 댓글 삭제 
	public int delete_bnum(int bnum) throws Exception;
}
