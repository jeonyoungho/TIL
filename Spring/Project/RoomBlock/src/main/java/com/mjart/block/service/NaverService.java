package com.mjart.block.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjart.block.config.ChromeDriverContext;
import com.mjart.block.model.Pension;
import com.mjart.block.model.ReservationInformation;

@Service
public class NaverService {
	@Autowired
	private ChromeDriverContext c;
	private WebDriver driver;
	
	public int naverBlock(Pension pension,ReservationInformation ri) throws ParseException {
		driver = c.getDriver();
		try {
		Calendar cal = Calendar.getInstance();
		int cyear = cal.get(Calendar.YEAR);
		int cmonth = cal.get(Calendar.MONTH) + 1;
		int cdate = cal.get(Calendar.DATE);
		
	    String a = String.format("%04d",cyear);
	    String b = String.format("%02d",cmonth);
	    String c = String.format("%02d",cdate);
	    String today = a + b + c;
		
	    String a1 = String.format("%04d",ri.getYear());
	    String b1 = String.format("%02d",ri.getMonth());
	    String c1 = String.format("%02d",ri.getDay());
	    String targetday = a1 + b1 + c1;
	    
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	    int pagemove;
	    int offset;
	    long diffDay;
	    
		Date beginDate = formatter.parse(today);
		Date endDate = formatter.parse(targetday);
	    long diff = endDate.getTime() - beginDate.getTime();
	    diffDay = diff / (24 * 60 * 60 * 1000);
	    pagemove = (int) (diffDay / 7);
	    offset = (int) (diffDay % 7);
		
		driver.get("https://easybooking.naver.com/");
		
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div/button")).click(); //팝업창 닫기

		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//*[@id=\"_header_link_con\"]/ul/li/a")).click(); //파트너센터 로그인 버튼 클릭
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementsByName('id')[0].value=\'" + pension.getNaverId() + "\'");
	    js.executeScript("document.getElementsByName('pw')[0].value=\'" + pension.getNaverPw() + "\'");
		
		
		driver.findElement(By.xpath("//*[@id=\"log.login\"]")).click(); //로그인버튼 클릭
		
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}

		String resocStockManageUrl = String.format("https://easybooking.naver.com/service/%d/status/resocStockManage", pension.getNaverProductNumber());
	    driver.get(resocStockManageUrl); //예약관리로 이동
	    
	    try {Thread.sleep(8000);} catch (InterruptedException e) {e.printStackTrace();}
	    
	    for(int i=0;i<pagemove;i++) {
	    	
	    	driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[2]/div[2]/div/div[2]/div[2]/div[2]/a[1]")).click();
	    	//페이지 넘기는 버튼 클릭
	    	try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
	    }
    	driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
  
	    String room = String.format("//*[@id=\"content\"]/div[1]/div/div[2]/div[2]/div/div[2]/div[3]/table/tbody[%d]/tr[1]/td[%d]",pension.ConvertNaverRoomNum(ri.getRoomname()),offset+3); 
	    driver.findElement(By.xpath(room)).click();//객실막기

	    try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
	    
		}
		catch (Exception e) {
			return 0;
		}
		finally {
			driver.manage().deleteAllCookies();
		}
		return 1;
	}
	
	public int naverOpen(Pension pension,ReservationInformation ri) throws ParseException {
		driver = c.getDriver();
		try {
		Calendar cal = Calendar.getInstance();
		int cyear = cal.get(Calendar.YEAR);
		int cmonth = cal.get(Calendar.MONTH) + 1;
		int cdate = cal.get(Calendar.DATE);
		
	    String a = String.format("%04d",cyear);
	    String b = String.format("%02d",cmonth);
	    String c = String.format("%02d",cdate);
	    String today = a + b + c;
		
	    String a1 = String.format("%04d",ri.getYear());
	    String b1 = String.format("%02d",ri.getMonth());
	    String c1 = String.format("%02d",ri.getDay());
	    String targetday = a1 + b1 + c1;
	    
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	    int pagemove;
	    int offset;
	    long diffDay;
	    
		Date beginDate = formatter.parse(today);
		Date endDate = formatter.parse(targetday);
	    long diff = endDate.getTime() - beginDate.getTime();
	    diffDay = diff / (24 * 60 * 60 * 1000);
	    pagemove = (int) (diffDay / 7);
	    offset = (int) (diffDay % 7);
		
		driver.get("https://easybooking.naver.com/");
		
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div/button")).click(); //팝업창 닫기

		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//*[@id=\"_header_link_con\"]/ul/li/a")).click(); //파트너센터 로그인 버튼 클릭
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementsByName('id')[0].value=\'" + pension.getNaverId() + "\'");
	    js.executeScript("document.getElementsByName('pw')[0].value=\'" + pension.getNaverPw() + "\'");
		
		
		driver.findElement(By.xpath("//*[@id=\"log.login\"]")).click(); //로그인버튼 클릭
		
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}

		String resocStockManageUrl = String.format("https://easybooking.naver.com/service/%d/status/resocStockManage", pension.getNaverProductNumber());
	    driver.get(resocStockManageUrl); //예약관리로 이동
	    
	    try {Thread.sleep(8000);} catch (InterruptedException e) {e.printStackTrace();}
	    
	    for(int i=0;i<pagemove;i++) {
	    	
	    	driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[2]/div[2]/div/div[2]/div[2]/div[2]/a[1]")).click();
	    	//페이지 넘기는 버튼 클릭
	    	try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
	    }
	    
    	driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
    	
	    String room = String.format("//*[@id=\"content\"]/div[1]/div/div[2]/div[2]/div/div[2]/div[3]/table/tbody[%d]/tr[1]/td[%d]",pension.ConvertNaverRoomNum(ri.getRoomname()),offset+3); 
	    driver.findElement(By.xpath(room)).click();//객실막기

	    try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
	    
		}
		catch (Exception e) {
			return 0;
		}
		finally {
			driver.manage().deleteAllCookies();
		}
		return 1;
	}
}
