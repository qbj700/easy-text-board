package com.sbs.example.easytextboard;

import java.util.Scanner;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.controller.ArticleController;
import com.sbs.example.easytextboard.controller.Controller;
import com.sbs.example.easytextboard.controller.MemberController;
import com.sbs.example.easytextboard.service.ArticleService;
import com.sbs.example.easytextboard.service.MemberService;

public class App {
	private MemberController memberController;
	private ArticleController articleController;

	public App() {
		memberController = Container.memberController;
		articleController = Container.articleController;

		makeTestData();
	}

	private void makeTestData() {
		// 회원 2명 생성
		MemberService memberService = Container.memberService;
		int firstMemberId = memberService.join("aaa", "aaa", "aaa");
		int secondMemberId = memberService.join("bbb", "bbb", "bbb");

		// 1번 회원이 공지사항 게시물 5개 작성
		ArticleService articleService = Container.articleService;
		for (int i = 1; i <= 5; i++) {
			articleService.add("제목" + i, "내용" + i, firstMemberId);
		}
		// 2번 회원이 공지사항 게시물 5개 작성
		for (int i = 6; i <= 10; i++) {
			articleService.add("제목" + i, "내용" + i, secondMemberId);
		}
	}

	public void run() {
		Scanner sc = Container.scanner;

		while (true) {
			System.out.printf("명령어 입력 : ");
			String command = sc.nextLine();

			if (command.equals("system exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			}
			Controller controller = getControllerByCommand(command);
			if (controller == null) {
				System.out.println("존재하지 않는 명령어 입니다.");
				continue;
			}
			if (controller != null) {
				controller.doCommand(command);
			}
		}

		sc.close();

	}

	private Controller getControllerByCommand(String command) {
		if (command.startsWith("member ")) {
			return memberController;
		} else if (command.startsWith("article ")) {
			return articleController;
		}
		return null;
	}

}
