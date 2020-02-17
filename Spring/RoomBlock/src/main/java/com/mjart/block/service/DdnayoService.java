package com.mjart.block.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjart.block.config.ChromeDriverContext;
import com.mjart.block.model.Pension;
import com.mjart.block.model.ReservationInformation;

@Service
public class DdnayoService {
	@Autowired
	private ChromeDriverContext c;
	private WebDriver driver;
	
	public int ddnayoBlock(Pension pension,ReservationInformation ri) {
		driver = c.getDriver();
		try {
		driver.get("https://www.ddnayo.com/bms/login.aspx?ReturnUrl=%2fAms%2fMtmQa.aspx&id_hotel=");
		driver.findElement(By.id("ctt_idSave")).click();
		driver.findElement(By.id("ctt_id_login")).sendKeys(pension.getDdnayoId());
		driver.findElement(By.id("ctt_pwd_login")).sendKeys(pension.getDdnayoPW());
		driver.findElement(By.xpath("//div[@class=\"input_outer\"]/input")).click();
		
		if(pension.getDdnayoListIndex() != -1) {
		String selectInDdnayoList = String.format("//*[@id=\"ctt_list_LinkButton1_%d\"]",pension.getDdnayoListIndex());
		driver.findElement(By.xpath(selectInDdnayoList)).click();
		}
		
		driver.get("https://www.ddnayo.com/Ams/RoomDayUse.aspx?sslcvt=1/");// 일자별예약관

		Calendar cal = Calendar.getInstance();
		int cyear = cal.get(Calendar.YEAR);
		int cmonth = cal.get(Calendar.MONTH) + 1;

		if (ri.getYear() > cyear) {
			driver.findElement(By.partialLinkText("다음달")).click();// 페이지 이동
		}
		int diff = ri.getMonth() - cmonth;
		if (diff > 0) {
			for (int i = 0; i < diff; i++) {
				driver.findElement(By.partialLinkText("다음달")).click();// 페이지 이동
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		String date = String.format("'Select$%d-%d-%d'",ri.getYear(),ri.getMonth(),ri.getDay());
		List<WebElement> webElementList = driver.findElements(By.cssSelector("th[class='d_th clk']"));
		webElementList.addAll(driver.findElements(By.cssSelector("th[class='d_th_0 clk']")));
		webElementList.addAll(driver.findElements(By.cssSelector("th[class='d_th_6 clk']")));

		for (WebElement element : webElementList) {
			if (element.getAttribute("onClick").contains(date)) {
				element.click();
				break;
			}
		}

		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		EnterBooker(pension.ConvertDdnayoRoomNum(ri.getRoomname()),ri);
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}

		}
		catch (Exception e) {
			return 0;
		}
		finally {
			driver.manage().deleteAllCookies();
		}
		
		return 1;
	}
	
	private void EnterBooker(int roomNum, ReservationInformation ri) {
		String room = String.format("//*[@id=\"ctt_ctt_list_td0010_%d\"]/a[1]", roomNum);
		driver.findElement(By.xpath(room)).click();
		
		List<String> tabList = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabList.get(1));
		
		driver.findElement(By.xpath("//*[@id=\"ctt_ctt_nm_user\"]")).sendKeys(ri.getReservationname());
		driver.findElement(By.xpath("//*[@id=\"ctt_ctt_htel_c1\"]/option[2]")).click();
		driver.findElement(By.xpath("//*[@id=\"ctt_ctt_htel_c2\"]")).sendKeys(ri.getHtel_c2());
		driver.findElement(By.xpath("//*[@id=\"ctt_ctt_htel_c3\"]")).sendKeys(ri.getHtel_c3());
		driver.findElement(By.xpath("//*[@id=\"ctt_ctt_flowinDomainH\"]")).sendKeys(ri.getFlowinDomain());
		driver.findElement(By.xpath("//*[@id=\"ctt_ctt_memo\"]")).sendKeys(ri.getMemo());

		String lengthOfStay = String.format("//*[@id=\"ctt_ctt_list_term_%d\"]/option[%d]", roomNum,ri.getLengthOfStay());
		// argument - 객실명,박수
		driver.findElement(By.xpath(lengthOfStay)).click();

		String adults = String.format("//*[@id=\"ctt_ctt_list_su_adult_%d\"]/option[%d]", roomNum, ri.getNumberOfAdult() + 1);
		// argument - 객실명, 성인수 (인원수 + 1 , ex. 2명 ->3 )
		driver.findElement(By.xpath(adults)).click();

		String childs = String.format("//*[@id=\"ctt_ctt_list_su_child_%d\"]/option[%d]", roomNum, ri.getNumberOfChild() + 1);
		// argument - 객실명, 아수 (인원수 + 1 , ex. 2명 ->3 )
		driver.findElement(By.xpath(childs)).click();

		String baby = String.format("//*[@id=\"ctt_ctt_list_su_baby_%d\"]/option[%d]", roomNum, ri.getNumberOfBaby() + 1);
		// argument - 객실명, 아수 (인원수 + 1 , ex. 2명 ->3 )
		driver.findElement(By.xpath(baby)).click();

		String regState = String.format("//*[@id=\"ctt_ctt_appmRegState_%d\"]",ri.getAppmRegState());
		// 0 입금대기 , 1 입금완료
		driver.findElement(By.xpath(regState)).click();

		String bankNoSmsState = String.format("//*[@id=\"ctt_ctt_is_banknosms_%d\"]",ri.getIs_banknosms());
		// 0 예약자에게 계좌번호문자 발송안함 , 1 예약자에게 계좌번호문자 발송함
		driver.findElement(By.xpath(bankNoSmsState)).click();

		String smsState = String.format("//*[@id=\"ctt_ctt_is_sms_%d\"]",ri.getIs_sms());
		// 0 예약자에게 예약문자 발송안함 , 1 예약자에게 예약문자 발송함
		driver.findElement(By.xpath(smsState)).click();

		String adminState = String.format("//*[@id=\"ctt_ctt_is_admin_%d\"]",ri.getIs_admin());
		// 0 펜션주에게 예약문자 발송안함 , 1 펜션주에게 예약문자 발송함
		driver.findElement(By.xpath(adminState)).click();

		if(ri.getAmt().length() != 0) {
		// 총 결제액 입력
		driver.findElement(By.xpath("//*[@id=\"ctt_ctt_amt\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"ctt_ctt_amt\"]")).sendKeys(ri.getAmt());
		}
		
		driver.findElement(By.xpath("//*[@id=\"ctt_ctt_btnSave\"]")).click(); // 등록하기 버튼 클릭

		driver.switchTo().alert().accept();

		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		driver.switchTo().alert().dismiss();

		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.switchTo().window(tabList.get(0));
	}

	private void CancleBooker(int roomNum, ReservationInformation ri) {
		String openBooker = String.format("//*[@id=\"ctt_ctt_list\"]/table/tbody/tr[%d]/td[3]/a",roomNum+2);
		driver.findElement(By.xpath(openBooker)).click();
		
		List<String> tabList = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabList.get(1));
		
		driver.findElement(By.xpath("//*[@id=\"ctt_ctt_btnCancelView\"]")).click(); //예약 취소버튼클릭
		
		driver.findElement(By.xpath("//*[@id=\"ctt_ctt_is_cc_sms_user\"]")).click(); //고객에게 취소 SMS전송 버튼
		
		String cancleType = String.format("//*[@id=\"ctt_ctt_cd_cc_type_temp_%d\"]",2);
		// 0 전액환불 , 1 현금일부환불, 2 환불없음, 3 환불대기
		
		//form으로 입력받을때 취소일자 입력받기
		//카드 취소시 취소 금액 입력 받아 넣기
		/*
		  if(환불타입 == 1){
		 	String cancleDate = String.format("%04d-%02d-%02d",ri.getYear(),ri.getMonth(),ri.getDay());
		 	driver.findElement(By.xpath("//*[@id=\"ctt_ctt_dt_cancel\"]")).sendKeys(cancleDate); //취소일 입력
		 	driver.findElement(By.xpath("//*[@id=\"ctt_ctt_btnCalCancel\"]")).click(); //취소수수료 계산 버튼 클릭
		  }
		 */
		 
		
		driver.findElement(By.xpath(cancleType)).click();//환불없음 클릭
		
		driver.findElement(By.xpath("//*[@id=\"ctt_ctt_btnCancel\"]")).click(); //예약취소버튼클릭
		
		driver.switchTo().alert().accept();
		
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.switchTo().alert().accept();
		
		driver.close();
		
		driver.switchTo().window(tabList.get(0));
		
	}
	
	private void BlockRoom(int roomNum,ReservationInformation ri) {
		String room = String.format("//*[@id=\"ctt_ctt_list_td0010_%d\"]/a[2] ", roomNum);
		driver.findElement(By.xpath(room)).click();
		driver.switchTo().alert().accept();
	}
	
	private void OpenRoom(Pension pension,ReservationInformation ri) {
		String room = String.format("//*[@id=\"ctt_ctt_list_td0030_%d\"]/span/span/a",pension.ConvertDdnayoRoomNum(ri.getRoomname()));
		driver.findElement(By.xpath(room)).click();
		driver.switchTo().alert().accept();
	}

	public int ddnayoOpen(Pension pension,ReservationInformation ri) {
		driver = c.getDriver();
		try {
		driver.get("https://www.ddnayo.com/bms/login.aspx?ReturnUrl=%2fAms%2fMtmQa.aspx&id_hotel=");
		driver.findElement(By.id("ctt_idSave")).click();
		driver.findElement(By.id("ctt_id_login")).sendKeys(pension.getDdnayoId());
		driver.findElement(By.id("ctt_pwd_login")).sendKeys(pension.getDdnayoPW());
		driver.findElement(By.xpath("//div[@class=\"input_outer\"]/input")).click();
		
		if(pension.getDdnayoListIndex() != -1) {
		String selectInDdnayoList = String.format("//*[@id=\"ctt_list_LinkButton1_%d\"]",pension.getDdnayoListIndex());
		driver.findElement(By.xpath(selectInDdnayoList)).click();
		}
		
		driver.get("https://www.ddnayo.com/Ams/RoomDayUse.aspx?sslcvt=1/");// 일자별예약관

		Calendar cal = Calendar.getInstance();
		int cyear = cal.get(Calendar.YEAR);
		int cmonth = cal.get(Calendar.MONTH) + 1;

		if (ri.getYear() > cyear) {
			driver.findElement(By.partialLinkText("다음달")).click();// 페이지 이동
		}
		int diff = ri.getMonth() - cmonth;
		if (diff > 0) {
			for (int i = 0; i < diff; i++) {
				driver.findElement(By.partialLinkText("다음달")).click();// 페이지 이동
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		String date = String.format("'Select$%d-%d-%d'",ri.getYear(),ri.getMonth(),ri.getDay());
		List<WebElement> webElementList = driver.findElements(By.cssSelector("th[class='d_th clk']"));
		webElementList.addAll(driver.findElements(By.cssSelector("th[class='d_th_0 clk']")));
		webElementList.addAll(driver.findElements(By.cssSelector("th[class='d_th_6 clk']")));

		for (WebElement element : webElementList) {
			if (element.getAttribute("onClick").contains(date)) {
				element.click();
				break;
			}
		}

		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

//		BlockRoom(pension.ConvertDdnayoRoomNum(ri.getRoomname()),ri);
		CancleBooker(pension.ConvertDdnayoRoomNum(ri.getRoomname()), ri);
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}

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
