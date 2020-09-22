package com.myboard.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.myboard.dto.ReplyDTO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{

	@Resource
	private SqlSession session;
	
	@Override
	public int insert(ReplyDTO rdto) throws Exception {
		return session.insert("replyMapper.insert",rdto);
	}

	@Override
	public int update(ReplyDTO rdto) throws Exception {
		return session.update("replyMapper.update", rdto);
	}

	@Override
	public int delete(int rnum) throws Exception{
		return session.delete("replyMapper.delete", rnum);
	}

	@Override
	public List<ReplyDTO> selectList(int bnum) throws Exception {
		return session.selectList("replyMapper.selectList",bnum);
	}

	@Override
	public ReplyDTO selectOne(int rnum) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int delete_bnum(int bnum) throws Exception {
		// 게시물의 댓글 삭제
		return session.delete("replyMapper.delete_bnum",bnum);
	}
}
