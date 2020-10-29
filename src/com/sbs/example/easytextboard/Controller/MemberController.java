package com.sbs.example.easytextboard.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.example.easytextboard.dto.Member;

public class MemberController extends Controller {

	private List<Member> members;
	private int lastMemberId;
	private int loginInformation;

	public MemberController() {
		lastMemberId = 0;
		loginInformation = -1;

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

	// 중복 ID 일경우 함수
	private boolean isJoinableLoginId(String loginId) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).loginId.equals(loginId)) {
				return false;
			}
		}
		return true;
	}

	// 회원 로그인 함수
	private int login(String id, String pw) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).loginId.equals(id)) {
				if (members.get(i).loginPw.equals(pw)) {
					loginInformation = i;
					return i;
				}
			}
		}
		return -1;
	}

	public void run(Scanner sc, String command) {

		if (command.equals("member join")) {
			int loginIdMaxCount = 3;
			int loginIdCount = 0;
			boolean loginIdIsVaild = false;
			String loginId;

			System.out.println("== 회원 가입 ==");

			System.out.printf("이름을 입력하세요 :");
			String name = sc.nextLine();
			System.out.printf("로그인 ID를 입력하세요 :");
			loginId = sc.nextLine();

			while (true) {
				if (loginIdCount >= loginIdMaxCount) {
					System.out.println("다음에 다시 시도해 주세요.");
					break;
				}
				if (loginId.length() == 0) {
					System.out.println("로그인 아이디를 입력해주세요.");
					loginIdCount++;
					System.out.printf("로그인 ID를 입력하세요 :");
					loginId = sc.nextLine();
					continue;

				}
				if (isJoinableLoginId(loginId) == false) {
					System.out.printf("%s는 이미 사용중인 아이디 입니다.\n", loginId);
					loginIdCount++;
					System.out.printf("로그인 ID를 입력하세요 :");
					loginId = sc.nextLine();
					continue;
				}

				loginIdIsVaild = true;
				break;

			}
			if (loginIdIsVaild == false) {
				return;
			}

			System.out.printf("로그인 PW를 입력하세요 :");
			String loginPw = sc.nextLine();

			int id = join(name, loginId, loginPw);

			System.out.printf("%d번 회원으로 회원가입이 완료되었습니다.\n", id);

		} else if (command.equals("member login")) {
			System.out.println("== 로그인 ==");

			System.out.printf("로그인 ID를 입력하세요 :");
			String id = sc.nextLine();
			System.out.printf("로그인 PW를 입력하세요 :");
			String pw = sc.nextLine();

			int count = login(id, pw);

			if (count == -1) {
				System.out.println("로그인 아이디 또는 페스워드가 잘못 입력되었습니다.");
				return;
			}

			System.out.printf("로그인 성공 %s님 반갑습니다.\n", members.get(count).name);

		} else if (command.equals("member whoami")) {
			System.out.println("== 로그인 정보 ==");

			if (loginInformation < 0) {
				System.out.println("현재 로그인 중인 회원이 존재하지 않습니다.");
				return;
			}

			System.out.printf("로그인 아이디 : %s\n", members.get(loginInformation).loginId);
			System.out.printf("등록된 이름 : %s\n", members.get(loginInformation).name);

		} else if (command.equals("member logout")) {

			if (loginInformation < 0) {
				System.out.println("현재 로그인 중인 회원이 존재하지 않습니다.");
				return;
			}

			System.out.println("== 정상적으로 로그아웃 되었습니다. ==");
			loginInformation = -1;

		} else {
			System.out.println("== 존재하지 않는 명령어 입니다  ==");
		}

	}

}
