package com.sbs.example.easytextboard.controller;

public class NullController extends Controller {
	public void doCommand(String command) {
		System.out.println("존재하지 않는 명령어 입니다.");
		return;
	}

}
