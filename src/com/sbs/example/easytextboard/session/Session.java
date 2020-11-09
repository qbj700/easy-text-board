package com.sbs.example.easytextboard.session;

public class Session {

	public int loginedMemberId;
	private int selectedBoardId;

	public Session() {
		loginedMemberId = 0;
		selectedBoardId = 0;
	}

	public boolean isLogined() {
		return loginedMemberId != 0;
	}

	public boolean isLogout() {
		return !isLogined();
	}

	public void login(int memberId) {
		loginedMemberId = memberId;
	}

	public void logout() {
		loginedMemberId = 0;
	}

	public void selectedBoard(int id) {
		selectedBoardId = id;
	}
}
