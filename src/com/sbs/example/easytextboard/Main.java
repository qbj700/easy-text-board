package com.sbs.example.easytextboard;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int lastArticleid = 0;

		// 1번 게시물 저장소
		int article1__id = 0;
		String article1__title = "";
		String article1__body = "";
		// 2번 게시물 저장소
		int article2__id = 0;
		String article2__title = "";
		String article2__body = "";

		while (true) {
			System.out.printf("명령어 입력 :");
			String command = scanner.nextLine();
			// System.out.println(command);

			if (command.equals("article add")) {
				System.out.println("== 게시물 등록 ==");

				int id = lastArticleid + 1;
				String title;
				String body;

				System.out.printf(" 제목 :");
				title = scanner.nextLine();
				System.out.printf(" 내용 :");
				body = scanner.nextLine();

				System.out.println("== 게시물 등록 결과 ==");
				System.out.printf("제목 :%s\n", title);
				System.out.printf("내용 :%s\n", body);
				System.out.printf("%d번 게시물이 등록 되었습니다\n", id);

				if (id == 1) {
					article1__id = id;
					article1__title = title;
					article1__body = body;
				} else if (id == 2) {
					article2__id = id;
					article2__title = title;
					article2__body = body;
				}

				lastArticleid = id;

			} else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");

				if (lastArticleid == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}
				if (lastArticleid >= 1) {
					System.out.println("번호 / 제목");
					System.out.printf("%d / %s\n", article1__id, article1__title);
				}
				if (lastArticleid >= 2) {
					System.out.printf("%d / %s\n", article2__id, article2__title);
				}

			} else if (command.equals("article detail 1")) {
				int inputedid =1;
				System.out.println("== 게시물 상세 ==");
				
				if (article1__id == 0) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedid);
					continue;
				}
				System.out.printf("번호 :%d\n", article1__id);
				System.out.printf("제목 :%s\n", article1__title);
				System.out.printf("내용 :%s\n", article1__body);

			} else if (command.equals("article detail 2")) {
				int inputedid = 2;
				System.out.println("== 게시물 상세 ==");
				if (article2__id == 0) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedid);
					continue;
				}
				System.out.printf("번호 :%d\n", article2__id);
				System.out.printf("제목 :%s\n", article2__title);
				System.out.printf("내용 :%s\n", article2__body);

			} else if (command.equals("article exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			} else {
				System.out.println("== 존재하지 않는 명령어 ==");
			}
		}
		scanner.close();

	}
}