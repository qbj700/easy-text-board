package com.sbs.example.easytextboard.container;

import com.sbs.example.easytextboard.session.Session;

public class Container {
	public static Session session;
	static {
		session = new Session();
	}
}
