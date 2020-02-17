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
public class OndaService {
	@Autowired
	private ChromeDriverContext c;
	private WebDriver driver;
	
	public int ondaBlock(Pension pension,ReservationInformation ri) {
		driver = c.getDriver();
		try {
		driver.get("https://pension.onda.me/login");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.get("https://pension.onda.me/login");
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(pension.getOndaId());
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(pension.getOndaPw());
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
		
		
		String selectInOndaList = String.format("/html/body/modal-container/div/div/property-selector-modal-component/div[2]/div/div/div[%d]/div",pension.getOndaListIndex());
		Boolean isPresent =	driver.findElements(By.xpath(selectInOndaList)).size()>0;
		if(pension.getOndaListIndex() != -1 && isPresent) {
			driver.findElement(By.xpath(selectInOndaList)).click();//객실 선택
		} //세션이 유지되고 있는 경우 element가 있을시 클릭 , 에코독펜션 선택
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		driver.findElement(By.className("close")).click();
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//*[@id=\"sub-nav\"]/div/div[2]/div[2]")).click();//사람모양클릭
		
		if(pension.getOndaId().equals("jastudy@naver.com")) {
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		driver.findElement(By.xpath("//*[@id=\"sub-nav\"]/div/div[2]/div[2]/div/div[1]/div/input")).sendKeys(pension.getPensionName());
		}
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		driver.findElement(By.linkText(pension.getPensionName())).click();
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.get("https://pension.onda.me/vacancies");//방막기 접속
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
	
		Calendar cal = Calendar.getInstance();
		int cyear = cal.get(Calendar.YEAR);
		int cmonth = cal.get(Calendar.MONTH) + 1;

		if (ri.getYear() > cyear) {
			driver.findElement(By.xpath("/html/body/app-root/app-layout/div[3]/vacancy-component/div[1]/div[2]/div/div/div[2]/button[2]")).click();
			// 페이지 이동
		}
		int diff = ri.getMonth() - cmonth;
		if (diff > 0) {
			for (int i = 0; i < diff; i++) {
				driver.findElement(By.xpath("/html/body/app-root/app-layout/div[3]/vacancy-component/div[1]/div[2]/div/div/div[2]/button[2]")).click();
				//페이지 이동
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
			}
		}
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		cal.set(ri.getYear(),ri.getMonth()-1,ri.getDay());
		String room = String.format("/html/body/app-root/app-layout/div[3]/vacancy-component/div[1]/div[4]/table/tbody/tr[%d]/td[%d]/label[%d]/span/div/div[2]",cal.get(Calendar.WEEK_OF_MONTH),cal.get(Calendar.DAY_OF_WEEK),pension.ConvertOndaRoomNum(ri.getRoomname()));
		driver.findElement(By.xpath(room)).click();//객실 선택
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.findElement(By.xpath("/html/body/app-root/app-layout/div[3]/vacancy-component/div[2]/div/div/button[1]")).click();
		//방막기버튼클릭
		driver.findElement(By.xpath("/html/body/modal-container/div/div/vacancy-select-modal-component/div[2]/div[4]/button")).click();
		//저장버튼클릭
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		
//		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//		
//		driver.findElement(By.xpath("//*[@id=\"sub-nav\"]/div/div[2]/div[2]")).click();//사람모양클릭
//
//		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//		
//		driver.findElement(By.xpath("//*[@id=\"sub-nav\"]/div/div[2]/div[2]/div/a[5]")).click(); //로그아웃클릭
		}
		catch (Exception e) {
			return 0;
		}
		finally {
//			driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/button[2]")).click();
			//확인버튼클릭
//			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			
			driver.manage().deleteAllCookies();
		}
		return 1;
		
	}
	
	public int ondaOpen(Pension pension,ReservationInformation ri) {
		driver = c.getDriver();
		try {
		driver.get("https://pension.onda.me/login");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.get("https://pension.onda.me/login");
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(pension.getOndaId());
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(pension.getOndaPw());
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
		
		
		String selectInOndaList = String.format("/html/body/modal-container/div/div/property-selector-modal-component/div[2]/div/div/div[%d]/div",pension.getOndaListIndex());
		Boolean isPresent =	driver.findElements(By.xpath(selectInOndaList)).size()>0;
		if(pension.getOndaListIndex() != -1 && isPresent) {
			driver.findElement(By.xpath(selectInOndaList)).click();//객실 선택
		} //세션이 유지되고 있는 경우 element가 있을시 클릭 , 에코독펜션 선택
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		driver.findElement(By.className("close")).click();
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//*[@id=\"sub-nav\"]/div/div[2]/div[2]")).click();//사람모양클릭
		
		if(pension.getOndaId().equals("jastudy@naver.com")) {
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		driver.findElement(By.xpath("//*[@id=\"sub-nav\"]/div/div[2]/div[2]/div/div[1]/div/input")).sendKeys(pension.getPensionName());
		}
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		driver.findElement(By.linkText(pension.getPensionName())).click();
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.get("https://pension.onda.me/vacancies");//방막기 접속
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
	
		Calendar cal = Calendar.getInstance();
		int cyear = cal.get(Calendar.YEAR);
		int cmonth = cal.get(Calendar.MONTH) + 1;

		if (ri.getYear() > cyear) {
			driver.findElement(By.xpath("/html/body/app-root/app-layout/div[3]/vacancy-component/div[1]/div[2]/div/div/div[2]/button[2]")).click();
			// 페이지 이동
		}
		int diff = ri.getMonth() - cmonth;
		if (diff > 0) {
			for (int i = 0; i < diff; i++) {
				driver.findElement(By.xpath("/html/body/app-root/app-layout/div[3]/vacancy-component/div[1]/div[2]/div/div/div[2]/button[2]")).click();
				//페이지 이동
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
			}
		}
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		cal.set(ri.getYear(),ri.getMonth()-1,ri.getDay());
		String room = String.format("/html/body/app-root/app-layout/div[3]/vacancy-component/div[1]/div[4]/table/tbody/tr[%d]/td[%d]/label[%d]/span/div/div[2]",cal.get(Calendar.WEEK_OF_MONTH),cal.get(Calendar.DAY_OF_WEEK),pension.ConvertOndaRoomNum(ri.getRoomname()));
		driver.findElement(By.xpath(room)).click();//객실 선택
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.findElement(By.xpath("/html/body/app-root/app-layout/div[3]/vacancy-component/div[2]/div/div/button[2]")).click();
		//방열기버튼클릭
		
//		driver.findElement(By.xpath("/html/body/modal-container/div/div/vacancy-select-modal-component/div[2]/div[4]/button")).click();
		//저장버튼클릭
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		
//		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//		
//		driver.findElement(By.xpath("//*[@id=\"sub-nav\"]/div/div[2]/div[2]")).click();//사람모양클릭
//
//		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//		
//		driver.findElement(By.xpath("//*[@id=\"sub-nav\"]/div/div[2]/div[2]/div/a[5]")).click(); //로그아웃클릭
		}
		catch (Exception e) {
			return 0;
		}
		finally {
//			driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/button[2]")).click();
			//확인버튼클릭
//			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			
			driver.manage().deleteAllCookies();
		}
		return 1;
		
	}


}
