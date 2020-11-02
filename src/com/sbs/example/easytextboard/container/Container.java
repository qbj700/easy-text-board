package com.sbs.example.easytextboard.container;

import com.sbs.example.easytextboard.service.ArticleService;
import com.sbs.example.easytextboard.service.MemberService;
import com.sbs.example.easytextboard.session.Session;

public class Container {
	public static Session session;
	public static ArticleService articleService;
	public static MemberService memberService;

	static {
		session = new Session();
		articleService = new ArticleService();
		memberService = new MemberService();

	}
}
