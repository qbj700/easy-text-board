package com.sbs.example.easytextboard.session;

public class Session {
	public int loginedMemberId;

	public boolean isLogined() {
		return loginedMemberId != 0;
	}

	public boolean isLogout() {
		return !isLogined();
	}
}
