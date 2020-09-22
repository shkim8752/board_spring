package com.myboard.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



//��Ʈ�ѿ��� 
@ControllerAdvice
public class CommonExcptionAdvice {
	
	
	@ExceptionHandler(Exception.class) // ��� ���ܸ� ����� �ֻ��� ���� �� ���ܰ� �߻��ϸ� �ش� �޼ҵ� ����
	public String common(Exception e, Model model) {
		System.out.println("���ܹ߻�");
		model.addAttribute("exception",e);
		System.out.println(e.toString()); //�ܼ�â���� ���� �ֵ��� 
		e.printStackTrace(); 	//�������� 
		return "error_common";

	}
}
