package kr.ac.hansung.cse;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Logger {
	
	@Pointcut("execution( void kr.ac.hansung.cse.*.sound() )")
	private void selectSound() {}//signature - 안에 body부분은 채우지 않아도됨, 함수이름이 pointcut의 id이다.
	
	@After("selectSound()")
	public void aboutToSound() {
		System.out.println("advice: about to sound");
	}

}
