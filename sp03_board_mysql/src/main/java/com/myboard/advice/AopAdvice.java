package com.myboard.advice;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopAdvice {
	
	//board ��Ʈ�ѷ� �Ű����� ���
	@Before("execution(* com.myboard.controller.*.*(..))")
	public void startLogController(JoinPoint jp) { //�����̽�(������)
		System.out.println(jp.getSignature().toLongString() +":�Ű�����" + Arrays.toString(jp.getArgs()) );
	}
	
	//dao �Ű����� ���
	@Before("execution(* com.myboard.dao.*.*(..))")
	public void startLogDAO(JoinPoint jp) { //�����̽�(������)
		System.out.println("--�Ű�����:" + jp.getSignature().toLongString());
		System.out.println("*** " + Arrays.toString(jp.getArgs()));
	}
	
	//�޼ҵ� ��ȯ�� ���
	//pointcut : ������ ���
	//������ ���������
	@AfterReturning(pointcut="execution(* com.myboard.service.*.*(..))", returning = "rObj")
	public void afterLog(JoinPoint jp, Object rObj) {
		if (rObj != null) {
			System.out.println("--���ϰ�:" + jp.getSignature().toLongString());
			System.out.println("*** " + rObj.toString());
		}
	}
	
	
}
