package com.sbs.example.easytextboard.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbs.example.easytextboard.dto.Member;

public class MemberDao {
	private List<Member> members;
	private int lastMemberId;

	public MemberDao() {
		members = new ArrayList<>();
		lastMemberId = 0;

		// 테스트 아이디 3개 생성
		for (int i = 1; i <= 3; i++) {
			join("user" + i, "user" + i, "user" + i);
		}
	}

	public int join(String loginId, String loginPw, String name) {
		Member member = new Member();
		member.MemberId = lastMemberId + 1;
		member.loginId = loginId;
		member.loginPw = loginPw;
		member.name = name;

		members.add(member);
		lastMemberId = member.MemberId;

		return member.MemberId;
	}

	public Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

}
