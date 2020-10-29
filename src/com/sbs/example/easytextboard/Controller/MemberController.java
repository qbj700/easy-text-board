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
	private int join(String name, String id, String pw) {

		Member member = new Member();
		member.count = lastMemberId + 1;
		member.name = name;
		member.id = id;
		member.pw = pw;

		members.add(member);
		lastMemberId = member.count;

		return member.count;
	}

	// 회원 로그인 함수
	private int login(String id, String pw) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).id.equals(id)) {
				if (members.get(i).pw.equals(pw)) {
					loginInformation = i;
					return i;
				}
			}
		}
		return -1;
	}

	public void run(Scanner sc, String command) {

		if (command.equals("member join")) {

			System.out.println("== 회원 가입 ==");

			System.out.printf("이름을 입력하세요 :");
			String name = sc.nextLine();
			System.out.printf("로그인 ID를 입력하세요 :");
			String loginId = sc.nextLine();
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

			System.out.printf("로그인 아이디 : %s\n", members.get(loginInformation).id);
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
