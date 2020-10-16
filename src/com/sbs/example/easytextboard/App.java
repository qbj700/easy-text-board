package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {
	// 1번 게시물 저장소
	Article article1 = new Article();
	// 2번 게시물 저장소
	Article article2 = new Article();
	

	public Article getArticle(int id) {
		if (id == 1) {
			return article1;
		} else if (id == 2) {
			return article2;
		}
		return null;
	}

	public void run() {
		Scanner scanner = new Scanner(System.in);

		int maxArticlesCount = 10;
		int lastArticleid = 0;

		while (true) {
			System.out.printf("명령어 입력 :");
			String command = scanner.nextLine();
			// System.out.println(command);

			if (command.equals("article add")) {
				System.out.println("== 게시물 등록 ==");

				if (lastArticleid >= maxArticlesCount) {
					System.out.println("더 이상 생성할 수 없습니다.");
					continue;
				}

				int id = lastArticleid + 1;
				String title;
				String body;

				System.out.printf("제목 :");
				title = scanner.nextLine();
				System.out.printf("내용 :");
				body = scanner.nextLine();

				System.out.println("== 게시물 등록 결과 ==");
				System.out.printf("제목 :%s\n", title);
				System.out.printf("내용 :%s\n", body);
				System.out.printf("%d번 게시물이 등록 되었습니다\n", id);

				if (id == 1) {
					article1.id = id;
					article1.title = title;
					article1.body = body;
				} else if (id == 2) {
					article2.id = id;
					article2.title = title;
					article2.body = body;
				}

				lastArticleid = id;

			} else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");

				if (lastArticleid == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}
				System.out.println("번호 / 제목");

				for (int i = 1; i <= lastArticleid; i++) {
					Article article = null;

					if (i == 1) {
						article = article1;
					}
					if (i == 2) {
						article = article2;
					}

					System.out.printf("%d / %s\n", article.id, article.title);
				}

			} else if (command.startsWith("article detail ")) {
				int inputedid = Integer.parseInt(command.split(" ")[2]);

				System.out.println("== 게시물 상세 ==");

				if (lastArticleid == 0 || inputedid > lastArticleid) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedid);
					continue;
				}

				Article article = getArticle(inputedid);

				System.out.printf("번호 : %d\n", article.id);
				System.out.printf("제목 : %s\n", article.title);
				System.out.printf("내용 : %s\n", article.body);

			} else if (command.equals("system exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			} else {
				System.out.println("== 존재하지 않는 명령어 ==");
			}
		}
		scanner.close();

	}

}
