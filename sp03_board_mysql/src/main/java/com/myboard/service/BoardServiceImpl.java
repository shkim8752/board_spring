package com.myboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.myboard.dao.BoardDAO;
import com.myboard.dto.BoardDTO;
import com.myboard.dto.PageDTO;

@Service
public class BoardServiceImpl implements BoardService{

	@Resource
	private BoardDAO bdao;
	
	@Resource
	private BFileService fservice;
	
	@Resource
	private ReplyService rservice;
	
	@Override
	public List<BoardDTO> selectList(PageDTO pdto) throws Exception {
		//------pdto ���ϱ�----------
		//��ü�Խù���
		int totCnt = bdao.totolCount(pdto);
		//��ü��������
		int totPage = totCnt / pdto.getPerPage();
		if ((totCnt % pdto.getPerPage()) != 0 ) totPage = totPage + 1;
		pdto.setTotPage(totPage);
		
		//����������
		int curPage = pdto.getCurPage();
		//���۹�ȣ(mysql������ 0������ ���� )
		int startNo = (curPage-1) * pdto.getPerPage();
		pdto.setStartNo(startNo);
		//����ȣ
		int endNo = startNo + pdto.getPerPage() -1;
		pdto.setEndNo(endNo);
		//����������
		int startPage = curPage - ((curPage-1) % pdto.getPerBlock());
		pdto.setStartPage(startPage);
		//��������
		int endPage = startPage + pdto.getPerBlock()-1;
		if (endPage > totPage) endPage = totPage;
		pdto.setEndPage(endPage);
		
		// ��ü��ȸ
		return bdao.selectList(pdto);
	}

	@Override
	public Map<String, Object> selectOne(int bnum) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		// �Խù� �Ѱ���ȸ
		map.put("board", bdao.selectOne(bnum));
		//���� ����Ʈ ��ȸ
		map.put("flist",fservice.selectList(bnum));
		
		return map;
		
	}
	
	//Ʈ����� ó��
	@Transactional
	@Override
	public int insert(BoardDTO bdto, List<MultipartFile> bfiles) throws Exception {
		// �Խù� �߰�
		bdao.insert(bdto);
		int bnum = bdto.getBnum(); //�Խù� ��ȣ
		
		//���� ����
		List<String> filenameList = fservice.fileUpload(bfiles);
		//���� �߰�
		fservice.insert(bnum, filenameList);
		
		return bnum;
	}

	@Override
	public int update(BoardDTO bdto, List<Integer> fnumList,List<MultipartFile> bfiles) throws Exception {
		// �Խù� ����
		bdao.update(bdto);
		int bnum = bdto.getBnum();
		//���� ����
		fservice.delete_part(bnum,fnumList);
		
		
		//���� ����
		List<String> filenameList = fservice.fileUpload(bfiles);
		//���� �߰�
		fservice.insert(bnum, filenameList);
		return 0;
	}

	@Transactional
	@Override
	public int delete(int bnum) throws Exception {
		//��ۻ���
		rservice.delete_bnum(bnum);
		//���� : �ڽ����̺� ������ �θ����̺� ����(foreign key����)
		//���� ����
		fservice.delete(bnum);
		
		// �Խù� ����
		bdao.delete(bnum);

		return 0;
	}

	@Override
	public int readcnt_update(int bnum) throws Exception {
		// ��ȸ�� +1
		return bdao.readcnt_update(bnum);
	}

	@Override
	public int replycntUp_update(int bnum) throws Exception {
		// ��ۼ� +1
		return bdao.replycntUp_update(bnum);
	}

	@Override
	public int replycntDown_update(int bnum) throws Exception {
		// ��ۼ� -1
		return bdao.replycntDown_update(bnum);
	}

}
