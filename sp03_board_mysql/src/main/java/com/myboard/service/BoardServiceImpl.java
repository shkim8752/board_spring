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
		//------pdto 구하기----------
		//전체게시물수
		int totCnt = bdao.totolCount(pdto);
		//전체페이지수
		int totPage = totCnt / pdto.getPerPage();
		if ((totCnt % pdto.getPerPage()) != 0 ) totPage = totPage + 1;
		pdto.setTotPage(totPage);
		
		//현재페이지
		int curPage = pdto.getCurPage();
		//시작번호(mysql에서ㅏ 0번부터 시작 )
		int startNo = (curPage-1) * pdto.getPerPage();
		pdto.setStartNo(startNo);
		//끝번호
		int endNo = startNo + pdto.getPerPage() -1;
		pdto.setEndNo(endNo);
		//시작페이지
		int startPage = curPage - ((curPage-1) % pdto.getPerBlock());
		pdto.setStartPage(startPage);
		//끝페이지
		int endPage = startPage + pdto.getPerBlock()-1;
		if (endPage > totPage) endPage = totPage;
		pdto.setEndPage(endPage);
		
		// 전체조회
		return bdao.selectList(pdto);
	}

	@Override
	public Map<String, Object> selectOne(int bnum) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		// 게시물 한건조회
		map.put("board", bdao.selectOne(bnum));
		//파일 리스트 조회
		map.put("flist",fservice.selectList(bnum));
		
		return map;
		
	}
	
	//트랜잭션 처리
	@Transactional
	@Override
	public int insert(BoardDTO bdto, List<MultipartFile> bfiles) throws Exception {
		// 게시물 추가
		bdao.insert(bdto);
		int bnum = bdto.getBnum(); //게시물 번호
		
		//파일 저장
		List<String> filenameList = fservice.fileUpload(bfiles);
		//파일 추가
		fservice.insert(bnum, filenameList);
		
		return bnum;
	}

	@Override
	public int update(BoardDTO bdto, List<Integer> fnumList,List<MultipartFile> bfiles) throws Exception {
		// 게시물 수정
		bdao.update(bdto);
		int bnum = bdto.getBnum();
		//파일 삭제
		fservice.delete_part(bnum,fnumList);
		
		
		//파일 저장
		List<String> filenameList = fservice.fileUpload(bfiles);
		//파일 추가
		fservice.insert(bnum, filenameList);
		return 0;
	}

	@Transactional
	@Override
	public int delete(int bnum) throws Exception {
		//댓글삭제
		rservice.delete_bnum(bnum);
		//주의 : 자식테이블 삭제후 부모테이블 삭제(foreign key관계)
		//파일 삭제
		fservice.delete(bnum);
		
		// 게시물 삭제
		bdao.delete(bnum);

		return 0;
	}

	@Override
	public int readcnt_update(int bnum) throws Exception {
		// 조회수 +1
		return bdao.readcnt_update(bnum);
	}

	@Override
	public int replycntUp_update(int bnum) throws Exception {
		// 댓글수 +1
		return bdao.replycntUp_update(bnum);
	}

	@Override
	public int replycntDown_update(int bnum) throws Exception {
		// 댓글수 -1
		return bdao.replycntDown_update(bnum);
	}

}
