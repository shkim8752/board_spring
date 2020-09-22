package com.myboard.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myboard.dto.BoardDTO;
import com.myboard.dto.PageDTO;
import com.myboard.service.BFileService;
import com.myboard.service.BoardService;

@Controller
@RequestMapping("/board")
@SessionAttributes("pdto") 
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Resource 
	private BoardService bservice;

	@Resource 
	private BFileService fservice;
	
	//���� ������ �̵�
	@RequestMapping("/")
	public String listMove(PageDTO pdto, Model model) throws Exception {
		//@SessionAttributes("pdto") ����
		model.addAttribute("pdto", pdto);
		return "board/main";
	}
	
	//��ȸ
	//@ModelAttribute("pdto") : view���� ���� ����
	//@ModelAttribute �� @SessionAttributes�� �� ����
	@RequestMapping("/list")
	public void boardList(@ModelAttribute("pdto") PageDTO pdto, Model model) throws Exception {
		List<BoardDTO> blist = bservice.selectList(pdto);
		model.addAttribute("blist", blist);
	}
	
	//�߰�������
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public void boardAdd() throws Exception {}
	
	//�߰�
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String boardAdd(BoardDTO bdto, List<MultipartFile> bfiles,RedirectAttributes rattr) throws Exception {
		bservice.insert(bdto, bfiles);
		rattr.addFlashAttribute("msg", "�߰��Ϸ�");
		return "redirect:/board/list";		
	}
	
	//�Ѱ���ȸ�� ���������� �̵�
	@RequestMapping("/detail")
	public void boardDetail(int bnum, Model model) throws Exception {
		//��ȸ�� +1
		bservice.readcnt_update(bnum);

		Map<String, Object> resultMap = bservice.selectOne(bnum);
		model.addAttribute("board", resultMap.get("board"));
		model.addAttribute("flist", resultMap.get("flist"));
	}
	//����
	@RequestMapping("/delete")
	public String boardDelete(int bnum, Model model, RedirectAttributes rattr) throws Exception {
		bservice.delete(bnum);
		rattr.addFlashAttribute("msg", "�����Ϸ�");
		return "redirect:/board/list";
	}
	
	//����������
	@RequestMapping(value="/modify", method = RequestMethod.GET)
	public void boardModify(int bnum, Model model) throws Exception {
		Map<String, Object> resultMap = bservice.selectOne(bnum);
		model.addAttribute("board", resultMap.get("board"));
		model.addAttribute("flist", resultMap.get("flist"));
	}
	
	//����
	@RequestMapping(value="/modify", method = RequestMethod.POST)
	public String boardModify(BoardDTO bdto,
			@RequestParam(value="fnum", required = false) List<Integer> fnumList,
			List<MultipartFile> bfiles,
			RedirectAttributes rattr) throws Exception {
		bservice.update(bdto,fnumList,bfiles );
		rattr.addFlashAttribute("msg", "�����Ϸ�");
		rattr.addAttribute("bnum", bdto.getBnum());
		return "redirect:/board/list";
	}
	
	//���� �ٿ�ε�
	@RequestMapping("/filedownload")
	public void fileDownload(String filename, HttpServletResponse response) throws Exception {
		logger.info(filename);
		fservice.fileDownload(filename, response);
	}
	
	//���� ����(@SessionAttributes("pdto"))
	@RequestMapping("/sessionDelete")
	public String sessionDelete(SessionStatus status) {
		//������ �����
		status.setComplete();
		return "redirect:/board/";
	}
}
