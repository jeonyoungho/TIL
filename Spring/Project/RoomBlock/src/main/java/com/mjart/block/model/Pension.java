package com.mjart.block.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class Pension {
	private String ddnayoId;
	private String ddnayoPW;
	private int ddnayoListIndex = -1; //떠나요 로그인하면 목록 순서 , 0부터 시작

	private String hoteltimeId;
	private String hoteltimePW;
	
	private String naverId;
	private String naverPw;
	private int naverProductNumber;
	
	private String ondaId;
	private String ondaPw;
	private int ondaListIndex = -1;//온다 로그인하면 목록 순서 , 1부터 시작
	private String pensionName;
	
	private String yanoljaId;
	private String yanoljaPw;
	
	private List<String> salesChannel;
	
	public int ConvertDdnayoRoomNum(String roomName) {return -1;}
	public int ConvertOndaRoomNum(String roomName){return -1;}
	public int ConvertHoteltimeRoomNum(String roomName) {return -1;}
	public int ConvertNaverRoomNum(String roomName) {return -1;}
	public int ConvertYanoljaRoomNum(String roomName) {return -1;}
}
