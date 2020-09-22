package com.myboard.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



//컨트롤에서 
@ControllerAdvice
public class CommonExcptionAdvice {
	
	
	@ExceptionHandler(Exception.class) // 모든 예외를 잡아줌 최상의 예외 즉 예외가 발상하면 해당 메소드 실행
	public String common(Exception e, Model model) {
		System.out.println("예외발생");
		model.addAttribute("exception",e);
		System.out.println(e.toString()); //콘솔창으로 볼수 있도록 
		e.printStackTrace(); 	//에러내용 
		return "error_common";

	}
}
