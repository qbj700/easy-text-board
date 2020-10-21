package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {

	Article[] articles = new Article[5];
	int lastArticleId = 0;
	int articlesSize = 0;

	int articlesSize() {
		return articlesSize;
	}
	
	Article getArticle(int id) {
		if (id < 1) {
			return null;
		}
		if (id > lastArticleId) {
			return null;
		}
		return articles[id - 1];
	}

	public void run() {
		for (int i = 0; i < articles.length; i++) {
			articles[i] = new Article();
		}

		Scanner sc = new Scanner(System.in);

		int maxArticlesConunt = articles.length;

		while (true) {

			System.out.printf("명령어 입력 :");
			String command = sc.nextLine();

			if (command.equals("article add")) {
				System.out.println("== 게시물 등록 ==");

				if (articlesSize() >= maxArticlesConunt) {
					System.out.println("더 이상 생성할 수 없습니다.");
					continue;
				}

				int id = lastArticleId + 1;
				lastArticleId = id;

				System.out.printf("제목 :");
				String title = sc.nextLine();
				System.out.printf("내용 :");
				String body = sc.nextLine();

				System.out.println("== 게시물 등록 결과 ==");
				System.out.printf("제목 :%s\n", title);
				System.out.printf("내용 :%s\n", body);

				Article article = getArticle(id);

				article.id = id;
				article.title = title;
				article.body = body;

				System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
				
				articlesSize++;
				
			} else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");

				if (articlesSize() == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}
				System.out.println("번호 / 제목");

				for (int i = 1; i <= lastArticleId; i++) {
					Article article = getArticle(i);

					System.out.printf("%d / %s\n", article.id, article.title);
				}
			} else if (command.startsWith("article detail ")) {
				int inputedid = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 상세 ==");

				Article article = getArticle(inputedid);

				if (article == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedid);
					continue;
				}

				System.out.printf("번호 : %d\n", article.id);
				System.out.printf("제목 : %s\n", article.title);
				System.out.printf("내용 : %s\n", article.body);

			} else if (command.equals("system exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			} else {
				System.out.println("== 존재하지 않는 명령어 입니다. ==");
			}
		}
		sc.close();
	}
}
