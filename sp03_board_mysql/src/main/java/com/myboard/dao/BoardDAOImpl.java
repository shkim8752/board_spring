package com.myboard.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.myboard.dto.BoardDTO;
import com.myboard.dto.PageDTO;

@Repository
public class BoardDAOImpl implements BoardDAO{

	@Resource
	private SqlSession session;

	//��ü �Ǽ�
	@Override
	public int totolCount(PageDTO pdto) throws Exception {
		return session.selectOne("boardMapper.totCount", pdto);
	}

	//��ü ��ȸ
	@Override
	public List<BoardDTO> selectList(PageDTO pdto) throws Exception {
		return session.selectList("boardMapper.selectList", pdto);
	}
	//�Ѱ���ȸ
	@Override
	public BoardDTO selectOne(int bnum) throws Exception {
		return session.selectOne("boardMapper.selectOne", bnum);
	}

	//�߰�
	@Override
	public int insert(BoardDTO bdto) throws Exception {
		return session.insert("boardMapper.insert",bdto );
	}
	//����
	@Override
	public int update(BoardDTO bdto) throws Exception {
		return session.update("boardMapper.update",bdto);
	}
	//����
	@Override
	public int delete(int bnum) throws Exception {
		return session.delete("boardMapper.delete",bnum);
	}
	//��ȸ�� +1
	@Override
	public int readcnt_update(int bnum) throws Exception {
		return session.update("boardMapper.readcnt_update", bnum);
	}
	//��ۼ� +1 
	@Override
	public int replycntUp_update(int bnum) throws Exception {
		return session.update("boardMapper.replycntUp_update", bnum);
	}

	//��ۼ� -1 
	@Override
	public int replycntDown_update(int bnum) throws Exception {
		return session.update("boardMapper.replycntDown_update", bnum);
	}

}
