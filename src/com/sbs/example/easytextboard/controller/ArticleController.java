package com.sbs.example.easytextboard.controller;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.dto.Article;
import com.sbs.example.easytextboard.service.ArticleService;

public class ArticleController {

	private ArticleService articleService;

	public ArticleController() {
		articleService = new ArticleService();
	}

	public void doCommand(String command) {
		if (command.equals("article add")) {
			add(command);
		} else if (command.startsWith("article list ")) {
			list(command);
		} else if (command.startsWith("article detail ")) {
			detail(command);
		}

	}

	private void detail(String command) {
		int inputedId = 0;
		try {
			inputedId = Integer.parseInt(command.split(" ")[2]);
		} catch (NumberFormatException e) {
			System.out.println("게시물 번호는 양의 정수를 입력해 주세요.");
			return;
		}

		Article article = articleService.getArticle(inputedId);

		System.out.println("== 게시물 상세 ==");
		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("제목 : %s\n", article.title);
		System.out.printf("내용 : %s\n", article.body);
	}

	private void list(String command) {
		int page = 0;
		try {
			page = Integer.parseInt(command.split(" ")[2]);
		} catch (NumberFormatException e) {
			System.out.println("페이지 번호는 양의 정수를 입력해 주세요.");
			return;
		}

		if (page <= 1) {
			page = 1;
		}

		int itemsInAPage = 10;
		int startPos = articleService.getArticles().size() - 1;
		startPos -= (page - 1) * itemsInAPage;
		int endPos = startPos - (itemsInAPage - 1);

		if (articleService.getArticles().size() == 0) {
			System.out.println("게시물이 존재하지 않습니다.");
			return;
		}
		if (startPos < 0) {
			System.out.printf("%d페이지는 존재하지 않습니다.\n", page);
			return;
		}
		if (endPos < 0) {
			endPos = 0;
		}

		System.out.println("== 게시물 리스트 ==");
		System.out.println("번호 / 제목");

		for (int i = startPos; i >= endPos; i--) {
			Article article = articleService.getArticleByIndex(i);
			System.out.printf("%d / %s\n", article.id, article.title);
		}

	}

	private void add(String command) {

		if (Container.session.isLogout()) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}

		System.out.println("== 게시물 등록 ==");

		System.out.printf(" 제목 : ");
		String title = Container.scanner.nextLine();
		System.out.printf(" 내용 : ");
		String body = Container.scanner.nextLine();

		int id = articleService.add(title, body);
		System.out.printf("%d번 게시물이 생성되었습니다.\n", id);
	}

}
