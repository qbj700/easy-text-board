package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {

	private Article[] articles = new Article[5];
	private int lastArticleId = 0;
	private int articlesSize = 0;

	// 현재 게시물 갯수
	private int articlesSize() {
		return articlesSize;
	}

	// 게시물에 배열 번호 부여
	private Article getArticle(int id) {
		int index = getIndexById(id);

		if (index == -1) {
			return null;
		}
		return articles[index];
	}

	// 입력된 id가 articles[].id 와 일치하는지 여부 확인
	private int getIndexById(int id) {
		for (int i = 0; i < articlesSize(); i++) {
			if (articles[i].id == id) {
				return i;
			}
		}
		return -1;
	}

	// 게시물 삭제 함수
	private void remove(int id) {
		int index = getIndexById(id);

		if (index == -1) {
			return;
		}
		for (int i = index + 1; i < articlesSize(); i++) {
			articles[i - 1] = articles[i];
		}
		articlesSize--;
	}

	// 게시물 생성 함수
	private int add(String title, String body) {
		Article article = new Article();

		article.id = lastArticleId + 1;
		article.title = title;
		article.body = body;

		articles[articlesSize] = article;

		articlesSize++;
		lastArticleId = article.id;

		return article.id;
	}

	// 게시물 수정 함수
	private void modify(int inputedId, String title, String body) {
		Article article = getArticle(inputedId);
		article.title = title;
		article.body = body;
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		int maxArticlesCount = articles.length;

		while (true) {

			System.out.printf("명령어 입력 :");
			String command = sc.nextLine();

			if (command.equals("article add")) {
				System.out.println("== 게시물 등록 ==");

				if (articlesSize() >= maxArticlesCount) {
					System.out.println("더 이상 등록할 수 없습니다.");
					continue;
				}

				System.out.printf("제목 :");
				String title = sc.nextLine();
				System.out.printf("내용 :");
				String body = sc.nextLine();

				System.out.println("== 게시물 등록 결과 ==");
				System.out.printf("제목 :%s\n", title);
				System.out.printf("내용 :%s\n", body);

				int id = add(title, body);

				System.out.printf("%d번 게시물이 생성되었습니다.\n", id);
			} else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");

				if (articlesSize() == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}
				System.out.println("번호 / 제목");
				for (int i = articlesSize() -1; i >= 0; i--) {
					Article article = articles[i];
					System.out.printf("%d / %s\n", article.id, article.title);
				}

			} else if (command.startsWith("article detail ")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 상세 ==");

				Article article = getArticle(inputedId);

				if (article == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
					continue;
				}

				System.out.printf("번호 : %d\n", article.id);
				System.out.printf("제목 : %s\n", article.title);
				System.out.printf("내용 : %s\n", article.body);

			} else if (command.startsWith("article delete ")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 삭제 ==");

				Article article = getArticle(inputedId);

				if (article == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
					continue;
				}
				remove(inputedId);
				System.out.printf("%d번 게시물이 삭제되었습니다.\n", inputedId);

			} else if (command.startsWith("article modify ")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 수정 ==");

				Article article = getArticle(inputedId);

				if (article == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
					continue;
				} else {
					System.out.printf("수정할 제목 :");
					String title = sc.nextLine();
					System.out.printf("수정할 내용 :");
					String body = sc.nextLine();

					modify(inputedId, title, body);

					System.out.printf("%d번 게시물이 수정되었습니다.\n", inputedId);
				}

			} else if (command.startsWith("article search ")) {
				String articleSearch = command.split(" ")[2];
				System.out.println("== 게시물 검색 ==");

				System.out.println("번호 / 제목");
				for (int i = 0; i < articlesSize; i++) {
					if (articles[i].title.contains(articleSearch)) {
						System.out.printf("%d / %s\n", i + 1, articles[i].title);
					}
				}

			} else if (command.equals("system exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			} else {
				System.out.println("== 존재하지 않는 명령어 입니다  ==");
			}
		}
		sc.close();
	}
}
