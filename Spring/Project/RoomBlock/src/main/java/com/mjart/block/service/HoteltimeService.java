package com.mjart.block.service;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjart.block.config.ChromeDriverContext;
import com.mjart.block.model.Pension;
import com.mjart.block.model.ReservationInformation;

@Service
public class HoteltimeService {
	@Autowired
	private ChromeDriverContext c;
	private WebDriver driver;
	
	public int hoteltimeBlock(Pension pension,ReservationInformation ri) {
		driver = c.getDriver();
		try {
		driver.get("https://cms.hoteltime.co.kr/auth");
		driver.findElement(By.name("id")).sendKeys(pension.getHoteltimeId());
		driver.findElement(By.name("password")).sendKeys(pension.getHoteltimePW());

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}

		String monthUrl = String.format("https://cms.hoteltime.co.kr/sale/goods/%04d-%02d-01", ri.getYear(), ri.getMonth());
		driver.get(monthUrl);

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}

		Calendar cal = Calendar.getInstance();
        cal.set(ri.getYear(), ri.getMonth()-1, ri.getDay());
			
        String selectDate = String.format("//*[@id=\"calendar\"]/tbody/tr[%d]/td[%d]/a",cal.get(Calendar.WEEK_OF_MONTH),cal.get(Calendar.DAY_OF_WEEK));
		driver.findElement(By.xpath(selectDate)).click();//일자선택

		String room = String.format("//*[@id=\"type%d\"]",pension.ConvertHoteltimeRoomNum(ri.getRoomname()));
		driver.findElement(By.xpath(room)).click();// 객실 선택

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}

		driver.findElement(By.xpath("//*[@id=\"aside_area\"]/div[3]/form[2]/div[2]/button[1]")).click();// 선택 중지 클릭

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}

		driver.switchTo().alert().accept();
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}
		catch (Exception e) {
			return 0;
		}
		finally {
			driver.manage().deleteAllCookies();
		}
		return 1;
	}
	
	public int hoteltimeOpen(Pension pension,ReservationInformation ri) {
		driver = c.getDriver();
		try {
		driver.get("https://cms.hoteltime.co.kr/auth");
		driver.findElement(By.name("id")).sendKeys(pension.getHoteltimeId());
		driver.findElement(By.name("password")).sendKeys(pension.getHoteltimePW());

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}

		String monthUrl = String.format("https://cms.hoteltime.co.kr/sale/goods/%04d-%02d-01", ri.getYear(), ri.getMonth());
		driver.get(monthUrl);

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}

		Calendar cal = Calendar.getInstance();
        cal.set(ri.getYear(), ri.getMonth()-1, ri.getDay());
			
        String selectDate = String.format("//*[@id=\"calendar\"]/tbody/tr[%d]/td[%d]/a",cal.get(Calendar.WEEK_OF_MONTH),cal.get(Calendar.DAY_OF_WEEK));
		driver.findElement(By.xpath(selectDate)).click();//일자선택

		String room = String.format("//*[@id=\"type%d\"]",pension.ConvertHoteltimeRoomNum(ri.getRoomname()));
		driver.findElement(By.xpath(room)).click();// 객실 선택

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}

		driver.findElement(By.xpath("//*[@id=\"aside_area\"]/div[3]/form[2]/div[2]/button[2]")).click();// 선택 개시 클릭
		
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}

		driver.switchTo().alert().accept();
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
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
