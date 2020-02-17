package com.mjart.block.model;

public class Ecodog extends Pension {
	public int ConvertDdnayoRoomNum(String roomName) {
		switch (roomName) {
		case "301호":
			return 0;
		case "302호":
			return 1;
		case "303호":
			return 2;
		case "601호":
			return 3;
		case "602호":
			return 4;
		case "701호":
			return 5;
		case "702호":
			return 6;
		case "801호":
			return 7;
		case "802호":
			return 8;
		case "803호":
			return 9;
		}
		return -1;
	}

	public int ConvertOndaRoomNum(String roomName) {
		switch (roomName) {
		case "301호":
			return 2;
		case "302호":
			return 3;
		case "303호":
			return 4;
		case "601호":
			return 5;
		case "602호":
			return 6;
		case "701호":
			return 7;
		case "702호":
			return 8;
		case "801호":
			return 9;
		case "802호":
			return 10;
		case "803호":
			return 11;
		}
		return -1;
	}

	public int ConvertHoteltimeRoomNum(String roomName) {
		switch (roomName) {
		case "301호":
			return 1;
		case "302호":
			return 2;
		case "303호":
			return 3;
		case "601호":
			return 4;
		case "602호":
			return 5;
		case "701호":
			return 6;
		case "702호":
			return 7;
		case "801호":
			return 8;
		case "802호":
			return 9;
		case "803호":
			return 10;
		}
		return -1;
	}

	public int ConvertNaverRoomNum(String roomName) {
		switch (roomName) {
		case "301호":
			return 1;
		case "302호":
			return 2;
		case "303호":
			return 3;
		case "601호":
			return 4;
		case "602호":
			return 5;
		case "701호":
			return 6;
		case "702호":
			return 7;
		case "801호":
			return 8;
		case "802호":
			return 9;
		case "803호":
			return 10;
		}
		return -1;
	}

	public int ConvertYanoljaRoomNum(String roomName) {
		switch (roomName) {
		case "301호":
			return 205693;
		case "302호":
			return 205694;
		case "303호":
			return 205696;
		case "601호":
			return 205698;
		case "602호":
			return 205705;
		case "701호":
			return 205706;
		case "702호":
			return 205707;
		case "801호":
			return 205708;
		case "802호":
			return 205709;
		case "803호":
			return 205712;
		}
		return -1;
	}

}
