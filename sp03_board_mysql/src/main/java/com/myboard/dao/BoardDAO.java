package com.myboard.dao;

import java.util.List;

import com.myboard.dto.BoardDTO;
import com.myboard.dto.PageDTO;

public interface BoardDAO {
	//��ü �Ǽ�
	public int totolCount(PageDTO pdto) throws Exception;
	//��ü��ȸ
	public List<BoardDTO> selectList(PageDTO pdto) throws Exception;
	//�Ѱ���ȸ
	public BoardDTO selectOne(int bnum) throws Exception;
	//�߰�
	public int insert(BoardDTO bdto) throws Exception;
	//����
	public int update(BoardDTO bdto) throws Exception;
	//����
	public int delete(int bnum) throws Exception;
	//��ȸ�� +1
	public int readcnt_update(int bnum) throws Exception;
	//��ۼ� + 1
	public int replycntUp_update(int bnum) throws Exception;
	//��ۼ� -1
	public int replycntDown_update(int bnum) throws Exception;
}
