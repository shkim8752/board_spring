package com.myboard.dao;

import java.util.List;

import com.myboard.dto.ReplyDTO;

public interface ReplyDAO {
		//�߰�
		public int insert(ReplyDTO dto)throws Exception ;
		//����
		public int update(ReplyDTO dto)throws Exception;
		//����
		public int delete(int rnum)throws Exception;
		//��ü�˻�
		public List<ReplyDTO> selectList(int bnum)throws Exception;
		//�Ѱǰ˻� 
		public ReplyDTO selectOne(int num)throws Exception;
		//�Խù��� �ش��ϴ� ��� ���� 
		public int delete_bnum(int bnum) throws Exception; //�Խù��� �ش��ϴ� ��� ����
}
