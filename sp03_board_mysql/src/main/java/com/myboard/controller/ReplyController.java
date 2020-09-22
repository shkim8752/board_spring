package com.myboard.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myboard.dto.ReplyDTO;
import com.myboard.service.ReplyService;

//Controller + ResponseBody 의 기능
@RestController
@RequestMapping("/reply")
public class ReplyController {
	
	@Resource
	private ReplyService service;
	
	//댓글 추가
	//@RequestBody : json형태로 값 감싸서  받음
	//produces="application/text; charset=utf-8" : 반환값 문자 한글처리
	@RequestMapping(value="/", method = RequestMethod.POST, produces="application/text; charset=utf-8")
	public ResponseEntity<String> insert(@RequestBody ReplyDTO rdto) throws Exception {
		service.insert(rdto);
		//응답객체를 생성해서 상태값 같이 전송
		return new ResponseEntity<>("추가완료", HttpStatus.OK);
		//return new ResponseEntity<>("실패", HttpStatus.BAD_REQUEST);
	}
	
	//댓글 전체조회
	@RequestMapping(value="/{bnum}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyDTO>> list(@PathVariable("bnum") int bnum) throws Exception {
		List<ReplyDTO> list = service.selectList(bnum);
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	
	//댓글 삭제
	@RequestMapping(value="/{rnum}", method = RequestMethod.DELETE, produces="application/text; charset=utf-8")
	public ResponseEntity<String> delete(@PathVariable("rnum") int rnum,int bnum) throws Exception {
		service.delete(rnum,bnum);
		//응답객체를 생성해서 상태값 같이 전송
		return new ResponseEntity<>("삭제완료", HttpStatus.OK);
	}
	
	//댓글 수정 
	@RequestMapping(value="/{rnum}", method = {RequestMethod.PUT,RequestMethod.PATCH},
			produces="application/text; charset=utf-8")
	public ResponseEntity<String> update(@PathVariable("rnum") int rnum,@RequestBody ReplyDTO rdto) throws Exception {
		rdto.setRnum(rnum); //댓글번호 세팅 
		service.update(rdto);
		
		System.out.println(rdto.toString());
		//응답객체를 생성해서 상태값 같이 전송
		return new ResponseEntity<>("저장완료", HttpStatus.OK);
	}
	
	
	
}
