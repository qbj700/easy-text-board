package com.sbs.example.easytextboard.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Article {
	public int id;
	public String regDate;
	public String title;
	public String body;
	public int loginMemberId;

	public Article(int id, String title, String body, int loginMemberId) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.regDate = format1.format(System.currentTimeMillis());

		this.id = id;
		this.title = title;
		this.body = body;
		this.loginMemberId = loginMemberId;
	}
}
