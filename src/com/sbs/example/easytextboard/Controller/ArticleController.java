package com.sbs.example.easytextboard.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.example.easytextboard.dto.Article;

public class ArticleController {

	private List<Article> articles;
	private int lastArticleId;

	public ArticleController() {
		lastArticleId = 0;
		articles = new ArrayList<>();

		for (int i = 0; i < 32; i++) {
			add("제목" + (i + 1), "내용" + (i + 1));
		}

	}

	// 게시물에 배열 번호 부여
	private Article getArticle(int id) {
		int index = getIndexById(id);

		if (index == -1) {
			return null;
		}
		return articles.get(index);
	}

	// 입력된 id가 articles.get(i).id 와 일치하는지 여부 확인
	private int getIndexById(int id) {
		for (int i = 0; i < articles.size(); i++) {
			if (articles.get(i).id == id) {
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
		articles.remove(index);
	}

	// 게시물 생성 함수
	private int add(String title, String body) {

		Article article = new Article(lastArticleId + 1, title, body);

		articles.add(article);
		lastArticleId = article.id;

		return article.id;
	}

	// 게시물 수정 함수
	private void modify(int inputedId, String title, String body) {
		Article article = getArticle(inputedId);
		article.title = title;
		article.body = body;
	}

	public void run(Scanner sc, String command) {
		if (command.equals("article add")) {
			System.out.println("== 게시물 등록 ==");

			System.out.printf("제목 :");
			String title = sc.nextLine();
			System.out.printf("내용 :");
			String body = sc.nextLine();

			System.out.println("== 게시물 등록 결과 ==");
			System.out.printf("제목 :%s\n", title);
			System.out.printf("내용 :%s\n", body);

			int id = add(title, body);

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

			if (articles.size() == 0) {
				System.out.println("게시물이 존재하지 않습니다.");
				return;
			}
			System.out.println("번호 / 제목 / 시간");

			int itemsInAPage = 10;
			int startPos = articles.size() - 1;
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
				Article article = articles.get(i);

				System.out.printf("%d / %s / %s\n", article.id, article.title, article.regDate);
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

			Article article = getArticle(inputedId);

			if (article == null) {
				System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
				return;
			}

			System.out.printf("번호 : %d\n", article.id);
			System.out.printf("제목 : %s\n", article.title);
			System.out.printf("내용 : %s\n", article.body);

		} else if (command.startsWith("article delete ")) {
			int inputedId = 0;
			try {
				inputedId = Integer.parseInt(command.split(" ")[2]);
			} catch (NumberFormatException e) {
				System.out.println("게시물 번호를 양의 정수로 입력해주세요.");
				return;
			}

			System.out.println("== 게시물 삭제 ==");

			Article article = getArticle(inputedId);

			if (article == null) {
				System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
				return;
			}
			remove(inputedId);
			System.out.printf("%d번 게시물이 삭제되었습니다.\n", inputedId);

		} else if (command.startsWith("article modify ")) {
			int inputedId = 0;
			try {
				inputedId = Integer.parseInt(command.split(" ")[2]);
			} catch (NumberFormatException e) {
				System.out.println("게시물 번호를 양의 정수로 입력해주세요.");
				return;
			}

			System.out.println("== 게시물 수정 ==");

			Article article = getArticle(inputedId);

			if (article == null) {
				System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
				return;
			} else {
				System.out.printf("수정할 제목 :");
				String title = sc.nextLine();
				System.out.printf("수정할 내용 :");
				String body = sc.nextLine();

				modify(inputedId, title, body);

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

			for (Article article : articles) {
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
