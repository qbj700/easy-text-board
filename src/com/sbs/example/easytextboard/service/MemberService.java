package com.sbs.example.easytextboard.service;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.dao.MemberDao;
import com.sbs.example.easytextboard.dto.Member;

public class MemberService {

	private MemberDao memberDao;

	public MemberService() {
		memberDao = Container.memberDao;
	}

	public int join(String loginId, String loginPw, String name) {
		return memberDao.join(loginId, loginPw, name);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public boolean isJoinableLoginId(String loginId) {
		Member member = memberDao.getMemberByLoginId(loginId);
		if (member != null) {
			return false;
		}

		return true;
	}

	public Member getMemberById(int loginedMemberId) {
		return memberDao.getMemberById(loginedMemberId);
	}

}
