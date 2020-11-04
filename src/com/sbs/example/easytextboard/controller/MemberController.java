package com.sbs.example.easytextboard.controller;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.dto.Member;
import com.sbs.example.easytextboard.service.MemberService;

public class MemberController {

	private MemberService memberService;

	public MemberController() {
		memberService = new MemberService();
	}

	public void doCommand(String command) {
		if (command.equals("member join")) {
			join(command);
		} else if (command.equals("member login")) {
			login(command);
		}

	}

	private void login(String command) {

		if (Container.session.isLogined()) {
			System.out.println("이미 로그인 중입니다.");
			return;
		}

		System.out.println("== 로그인 ==");

		String loginId;
		String loginPw;

		System.out.printf("로그인 아이디 : ");
		loginId = Container.scanner.nextLine().trim();

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			System.out.println("존재하지 않는 아이디 입니다.");
			return;
		}

		System.out.printf("로그인 비밀번호 : ");
		loginPw = Container.scanner.nextLine().trim();

		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}

		Container.session.loginedMemberId = member.MemberId;
		System.out.printf("로그인 성공, %s님 환영합니다.\n", member.name);

	}

	private void join(String command) {
		System.out.println("== 회원 가입 ==");

		String loginId;
		String loginPw;
		String name;

		System.out.printf("로그인 아이디 : ");
		loginId = Container.scanner.nextLine();

		System.out.printf("로그인 비밀번호 : ");
		loginPw = Container.scanner.nextLine();

		System.out.printf("이름 : ");
		name = Container.scanner.nextLine();

		int id = memberService.join(loginId, loginPw, name);
		System.out.printf("%d번 회원이 생성되었습니다.\n", id);

	}

}
