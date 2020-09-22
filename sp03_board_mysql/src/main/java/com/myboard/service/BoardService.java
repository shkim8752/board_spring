package com.myboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.myboard.dto.BoardDTO;
import com.myboard.dto.PageDTO;

public interface BoardService {
	//��ü��ȸ
	public List<BoardDTO> selectList(PageDTO pdto) throws Exception;
	//�Ѱ���ȸ
	public Map<String, Object> selectOne(int bnum) throws Exception;
	//�߰�
	public int insert(BoardDTO bdto, List<MultipartFile> bfiles) throws Exception;
	//����
	public int update(BoardDTO bdto, List<Integer> fnumList,List<MultipartFile> bfiles) throws Exception;
	//����
	public int delete(int bnum) throws Exception;
	//��ȸ�� +1
	public int readcnt_update(int bnum) throws Exception;
	//��ۼ� + 1
	public int replycntUp_update(int bnum) throws Exception;
	//��ۼ� -1
	public int replycntDown_update(int bnum) throws Exception;

}
