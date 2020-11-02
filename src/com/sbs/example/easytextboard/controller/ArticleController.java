package com.sbs.example.easytextboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.dto.Article;
import com.sbs.example.easytextboard.service.ArticleService;

public class ArticleController {
	private ArticleService articleService;

	public ArticleController() {
		articleService = Container.articleService;
	}

	public void run(Scanner sc, String command) {
		if (command.equals("article add")) {

			if (Container.session.isLogout()) {
				System.out.println("로그인 후 이용해 주세요.");
				return;
			}

			System.out.println("== 게시물 등록 ==");

			System.out.printf("제목 :");
			String title = sc.nextLine();
			System.out.printf("내용 :");
			String body = sc.nextLine();

			System.out.println("== 게시물 등록 결과 ==");
			System.out.printf("제목 :%s\n", title);
			System.out.printf("내용 :%s\n", body);

			int id = Container.articleService.add(title, body, Container.session.loginedMemberId);

			System.out.printf("%d번 게시물이 생성되었습니다.\n", id);

		} else if (command.startsWith("article list ")) {
			int page = 0;
			try {
				page = Integer.parseInt(command.split(" ")[2]);
			} catch (NumberFormatException e) {
				System.out.println("페이지 번호를 양의 정수로 입력해주세요.");
				return;
			}

			if (page <= 1) {
				page = 1;
			}

			System.out.println("== 게시물 리스트 ==");

			if (Container.articleService.getArticles().size() == 0) {
				System.out.println("게시물이 존재하지 않습니다.");
				return;
			}
			System.out.println("번호 / 제목 / 작성자");

			int itemsInAPage = 10;
			int startPos = Container.articleService.getArticleSize() - 1;
			startPos -= (page - 1) * itemsInAPage;
			int endPos = startPos - (itemsInAPage - 1);

			if (endPos < 0) {
				endPos = 0;
			}

			if (startPos < 0) {
				System.out.printf("%d페이지는 존재하지 않습니다.\n", page);
				return;
			}

			for (int i = startPos; i >= endPos; i--) {
				Article article = articleService.getArticleByIndex(i);

				System.out.printf("%d / %s / %d번 회원\n", article.id, article.title, article.loginMemberId);
			}

		} else if (command.startsWith("article detail ")) {
			int inputedId = 0;
			try {
				inputedId = Integer.parseInt(command.split(" ")[2]);
			} catch (NumberFormatException e) {
				System.out.println("게시물 번호를 양의 정수로 입력해주세요.");
				return;
			}

			System.out.println("== 게시물 상세 ==");

			Article article = articleService.getArticle(inputedId);

			if (article == null) {
				System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
				return;
			}
			System.out.printf("작성 시간 : %s\n", article.regDate);
			System.out.printf("작성 회원 번호 : %d번 회원\n", article.loginMemberId);
			System.out.printf("번호 : %d\n", article.id);
			System.out.printf("제목 : %s\n", article.title);
			System.out.printf("내용 : %s\n", article.body);

		} else if (command.startsWith("article delete ")) {

			if (Container.session.isLogout()) {
				System.out.println("로그인 후 이용해 주세요.");
				return;
			}

			int inputedId = 0;
			try {
				inputedId = Integer.parseInt(command.split(" ")[2]);
			} catch (NumberFormatException e) {
				System.out.println("게시물 번호를 양의 정수로 입력해주세요.");
				return;
			}

			System.out.println("== 게시물 삭제 ==");

			Article article = articleService.getArticle(inputedId);

			if (article == null) {
				System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
				return;
			}

			if (article.loginMemberId != Container.session.loginedMemberId) {
				System.out.println("작성자만 삭제 가능합니다.");
				return;
			}

			articleService.remove(inputedId);
			System.out.printf("%d번 게시물이 삭제되었습니다.\n", inputedId);

		} else if (command.startsWith("article modify ")) {

			if (Container.session.isLogout()) {
				System.out.println("로그인 후 이용해 주세요.");
				return;
			}

			int inputedId = 0;
			try {
				inputedId = Integer.parseInt(command.split(" ")[2]);
			} catch (NumberFormatException e) {
				System.out.println("게시물 번호를 양의 정수로 입력해주세요.");
				return;
			}
			Article article = articleService.getArticle(inputedId);

			System.out.println("== 게시물 수정 ==");

			if (article == null) {
				System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
				return;
			} else if (article.loginMemberId != Container.session.loginedMemberId) {
				System.out.println("작성자만 수정 가능합니다.");
				return;
			} else {
				System.out.printf("수정할 제목 :");
				String title = sc.nextLine();
				System.out.printf("수정할 내용 :");
				String body = sc.nextLine();

				articleService.modify(inputedId, title, body);

				System.out.printf("%d번 게시물이 수정되었습니다.\n", inputedId);
			}

		} else if (command.startsWith("article search ")) {

			String[] commandBits = command.split(" ");
			String searchKeyword = commandBits[2];

			int page = 1;

			if (commandBits.length >= 4) {
				try {
					page = Integer.parseInt(commandBits[3]);
				} catch (NumberFormatException e) {
					System.out.println("페이지 번호를 양의 정수로 입력해주세요.");
					return;
				}
			}

			if (page <= 1) {
				page = 1;
			}

			System.out.println("== 게시물 검색 ==");

			List<Article> searchResultArticles = new ArrayList<>();

			for (Article article : articleService.getArticles()) {
				if (article.title.contains(searchKeyword)) {
					searchResultArticles.add(article);
				}
			}

			if (searchResultArticles.size() == 0) {
				System.out.println("검색결과가 존재하지 않습니다.");
				return;
			}
			System.out.println("번호 / 제목");

			int itemsInAPage = 10;
			int startPos = searchResultArticles.size() - 1;
			startPos -= (page - 1) * itemsInAPage;
			int endPos = startPos - (itemsInAPage - 1);

			if (endPos < 0) {
				endPos = 0;
			}

			if (startPos < 0) {
				System.out.printf("%d페이지는 존재하지 않습니다.\n", page);
				return;
			}

			for (int i = startPos; i >= endPos; i--) {
				Article article = searchResultArticles.get(i);

				System.out.printf("%d / %s\n", article.id, article.title);
			}

		} else {
			System.out.println("== 존재하지 않는 명령어 입니다  ==");
		}
	}

}
