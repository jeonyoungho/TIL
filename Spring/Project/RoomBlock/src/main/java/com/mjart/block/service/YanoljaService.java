package com.mjart.block.service;

import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjart.block.config.ChromeDriverContext;
import com.mjart.block.model.Pension;
import com.mjart.block.model.ReservationInformation;

@Service
public class YanoljaService {
	@Autowired
	private ChromeDriverContext c;
	private WebDriver driver;
	
	public int yanoljaBlock(Pension pension,ReservationInformation ri) {
		driver = c.getDriver();
		try {
		driver.get("https://ceo.yapen.co.kr/");
		
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[3]/div/label")).click();

		driver.findElement(By.id("ceoID")).sendKeys(pension.getYanoljaId());
		driver.findElement(By.id("ceoPW")).sendKeys(pension.getYanoljaPw());
		driver.findElement(By.className("yapen-loginBtn")).click();
		
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		
		/*
		ArrayList<String> tabList = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabList.get(1));
		driver.close();
		driver.switchTo().window(tabList.get(0)); //팝업 브라우저 닫기
		*/
		
		String monthUrl = String.format("https://ceo.yapen.co.kr/rev/inventory?setDate=%04d-%02d",ri.getYear(),ri.getMonth());
		driver.get(monthUrl);
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
	
		String room = String.format("//*[@id=\"checkbox_%d_%04d-%02d-%02d\"]",pension.ConvertYanoljaRoomNum(ri.getRoomname()),ri.getYear(),ri.getMonth(),ri.getDay());
		driver.findElement(By.xpath(room)).click();

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		
		Calendar cal = Calendar.getInstance();
		cal.set(ri.getYear(),ri.getMonth()-1,ri.getDay());
		
		String blockBtn = String.format("//*[@id=\"roomForm\"]/table/tbody/tr[%d]/td[%d]/table/tbody/tr[12]/td/div[1]",cal.get(Calendar.WEEK_OF_MONTH),cal.get(Calendar.DAY_OF_WEEK));
		driver.findElement(By.xpath(blockBtn)).click();//방막기버튼 클릭
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.switchTo().alert().accept();
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.switchTo().alert().accept();
		
		}
		catch (Exception e) {
			return 0;
		}
		finally {
			driver.manage().deleteAllCookies();
		}
		return 1;		
	}
	public int yanoljaOpen(Pension pension,ReservationInformation ri) {
		driver = c.getDriver();
		try {
		driver.get("https://ceo.yapen.co.kr/");
		
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[3]/div/label")).click(); //아이디 저장 해제

		driver.findElement(By.id("ceoID")).sendKeys(pension.getYanoljaId());
		driver.findElement(By.id("ceoPW")).sendKeys(pension.getYanoljaPw());
		driver.findElement(By.className("yapen-loginBtn")).click();
		
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		
		/*
		ArrayList<String> tabList = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabList.get(1));
		driver.close();
		driver.switchTo().window(tabList.get(0)); //팝업 브라우저 닫기
		*/
		
		String monthUrl = String.format("https://ceo.yapen.co.kr/rev/inventory?setDate=%04d-%02d",ri.getYear(),ri.getMonth());
		driver.get(monthUrl);
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
	
		String room = String.format("//*[@id=\"checkbox_%d_%04d-%02d-%02d\"]",pension.ConvertYanoljaRoomNum(ri.getRoomname()),ri.getYear(),ri.getMonth(),ri.getDay());
		driver.findElement(By.xpath(room)).click();

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		
		Calendar cal = Calendar.getInstance();
		cal.set(ri.getYear(),ri.getMonth()-1,ri.getDay());
		
		String blockBtn = String.format("//*[@id=\"roomForm\"]/table/tbody/tr[%d]/td[%d]/table/tbody/tr[12]/td/div[2]",cal.get(Calendar.WEEK_OF_MONTH),cal.get(Calendar.DAY_OF_WEEK));
		driver.findElement(By.xpath(blockBtn)).click();//방열기버튼 클릭
		
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.switchTo().alert().accept();
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.switchTo().alert().accept();
		
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

