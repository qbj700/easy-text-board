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
		makeTestData();

	}

	private void makeTestData() {
		join("aaa", "aaa", "aaa");
		join("bbb", "bbb", "bbb");
	}

	public int join(String loginId, String loginPw, String name) {
		Member member = new Member();
		member.memberId = lastMemberId + 1;
		member.loginId = loginId;
		member.loginPw = loginPw;
		member.name = name;

		members.add(member);
		lastMemberId = member.memberId;

		return member.memberId;
	}

	public Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	public Member getMemberById(int loginedMemberId) {
		for (Member member : members) {
			if (member.memberId == loginedMemberId) {
				return member;
			}
		}
		return null;
	}

}
