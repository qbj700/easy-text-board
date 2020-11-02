package com.sbs.example.easytextboard.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbs.example.easytextboard.dto.Member;

public class MemberDao {
	private List<Member> members;
	private int lastMemberId;

	public MemberDao() {
		lastMemberId = 0;
		members = new ArrayList<>();

		for (int i = 1; i <= 3; i++) {
			join("user" + i, "user" + i, "유저" + i);
		}

	}

	public List<Member> getMembers() {
		return members;
	}

	public int getLastMemberId() {
		return lastMemberId;
	}

	// 회원 가입 함수
	public int join(String loginId, String loginPw, String name) {

		Member member = new Member();
		member.memberId = lastMemberId + 1;
		member.name = name;
		member.loginId = loginId;
		member.loginPw = loginPw;

		members.add(member);
		lastMemberId = member.memberId;

		return member.memberId;
	}

	// 로그인아이디 유효성 테스트 함수
	public boolean isJoinableLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return false;
			}
		}
		return true;
	}

	// 로그인 정보 확인 함수
	public Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}
}
