package com.sbs.example.easytextboard.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.example.easytextboard.dto.Member;

public class MemberController extends Controller {

	private List<Member> members;
	private int lastMemberId;

	public MemberController() {
		lastMemberId = 0;
		members = new ArrayList<>();

	}

	// 회원 가입 함수
	private int join(String name, String loginId, String loginPw) {

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
	private boolean isJoinableLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return false;
			}
		}
		return true;
	}

	public void run(Scanner sc, String command) {

		if (command.equals("member join")) {
			System.out.println("== 회원 가입 ==");

			String loginId = "";
			String loginPw;
			String name;

			int loginIdMaxCount = 3;
			int loginIdCount = 0;
			boolean loginIdIsVaild = false;

			while (true) {
				if (loginIdCount >= loginIdMaxCount) {
					System.out.println("회원가입을 취소합니다.");
					break;
				}
				System.out.printf("로그인 아이디 :");
				loginId = sc.nextLine().trim();

				if (loginId.length() == 0) {
					System.out.println("입력된 값이 존재하지 않습니다.");
					loginIdCount++;
					continue;

				} else if (isJoinableLoginId(loginId) == false) {
					System.out.printf("%s는 이미 사용중인 아이디 입니다.\n", loginId);
					loginIdCount++;
					continue;
				}

				loginIdIsVaild = true;
				break;

			}
			if (loginIdIsVaild == false) {
				return;
			}

			while (true) {
				System.out.printf("로그인 비밀번호 :");
				loginPw = sc.nextLine().trim();

				if (loginPw.length() == 0) {
					System.out.println("입력된 값이 존재하지 않습니다.");
					continue;
				}
				break;
			}

			while (true) {
				System.out.printf("이름을 입력하세요 :");
				name = sc.nextLine().trim();

				if (name.length() == 0) {
					System.out.println("입력된 값이 존재하지 않습니다.");
					continue;
				}
				break;
			}

			int id = join(name, loginId, loginPw);

			System.out.printf("%d번 회원이 생성되었습니다.\n", id);

		}
	}

}
