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

//Controller + ResponseBody �� ���
@RestController
@RequestMapping("/reply")
public class ReplyController {
	
	@Resource
	private ReplyService service;
	
	//��� �߰�
	//@RequestBody : json���·� �� ���μ�  ����
	//produces="application/text; charset=utf-8" : ��ȯ�� ���� �ѱ�ó��
	@RequestMapping(value="/", method = RequestMethod.POST, produces="application/text; charset=utf-8")
	public ResponseEntity<String> insert(@RequestBody ReplyDTO rdto) throws Exception {
		service.insert(rdto);
		//���䰴ü�� �����ؼ� ���°� ���� ����
		return new ResponseEntity<>("�߰��Ϸ�", HttpStatus.OK);
		//return new ResponseEntity<>("����", HttpStatus.BAD_REQUEST);
	}
	
	//��� ��ü��ȸ
	@RequestMapping(value="/{bnum}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyDTO>> list(@PathVariable("bnum") int bnum) throws Exception {
		List<ReplyDTO> list = service.selectList(bnum);
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	
	//��� ����
	@RequestMapping(value="/{rnum}", method = RequestMethod.DELETE, produces="application/text; charset=utf-8")
	public ResponseEntity<String> delete(@PathVariable("rnum") int rnum,int bnum) throws Exception {
		service.delete(rnum,bnum);
		//���䰴ü�� �����ؼ� ���°� ���� ����
		return new ResponseEntity<>("�����Ϸ�", HttpStatus.OK);
	}
	
	//��� ���� 
	@RequestMapping(value="/{rnum}", method = {RequestMethod.PUT,RequestMethod.PATCH},
			produces="application/text; charset=utf-8")
	public ResponseEntity<String> update(@PathVariable("rnum") int rnum,@RequestBody ReplyDTO rdto) throws Exception {
		rdto.setRnum(rnum); //��۹�ȣ ���� 
		service.update(rdto);
		
		System.out.println(rdto.toString());
		//���䰴ü�� �����ؼ� ���°� ���� ����
		return new ResponseEntity<>("����Ϸ�", HttpStatus.OK);
	}
	
	
	
}
