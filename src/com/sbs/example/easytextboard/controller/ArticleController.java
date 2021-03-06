package com.sbs.example.easytextboard.controller;

import java.util.ArrayList;
import java.util.List;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.dto.Article;
import com.sbs.example.easytextboard.dto.Board;
import com.sbs.example.easytextboard.dto.Member;
import com.sbs.example.easytextboard.service.ArticleService;
import com.sbs.example.easytextboard.service.MemberService;

public class ArticleController extends Controller {

	private ArticleService articleService;
	private MemberService memberService;

	public ArticleController() {
		articleService = Container.articleService;
		memberService = Container.memberService;
	}

	public void doCommand(String command) {
		if (command.equals("article add")) {
			add(command);
		} else if (command.startsWith("article list ")) {
			list(command);
		} else if (command.equals("article list")) {
			list(command);
		} else if (command.startsWith("article detail ")) {
			detail(command);
		} else if (command.startsWith("article delete ")) {
			delete(command);
		} else if (command.startsWith("article modify ")) {
			modify(command);
		} else if (command.startsWith("article search ")) {
			search(command);
		} else if (command.equals("article makeBoard")) {
			makeBoard(command);
		} else if (command.startsWith("article selectBoard ")) {
			selectBoard(command);
		} else if (command.equals("article currentBoard")) {
			currentBoard(command);
		}

	}

	private void currentBoard(String command) {
		if (Container.session.selectedBoardId == 0) {
			System.out.println("게시판 선택 후 이용해 주세요.");
			return;
		}

		Board board = articleService.getBoardById(Container.session.selectedBoardId);
		System.out.printf("현재 \"%s\" 게시판이 선택되어있는 상태입니다.\n", board.name);

	}

	private void selectBoard(String command) {
		int inputedId = 0;

		try {
			inputedId = Integer.parseInt(command.split(" ")[2]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("입력된 게시판 번호가 존재하지 않습니다.");
			return;
		} catch (NumberFormatException e) {
			System.out.println("게시판 번호는 양의 정수를 입력해주세요.");
			return;
		}

		Board board = articleService.getBoardById(inputedId);

		if (board == null) {
			System.out.printf("%d번 게시판은 존재하지 않습니다.\n", inputedId);
			return;
		}
		System.out.printf("%s(%d번) 게시판이 선택되었습니다.\n", board.name, board.id);
		Container.session.selectedBoard(board.id);

	}

	private void makeBoard(String command) {
		System.out.println("== 게시판 생성 ==");

		System.out.printf("게시판 이름 : ");
		String boardName = Container.scanner.nextLine();

		int boardId = articleService.makeBoard(boardName);
		System.out.printf("%s (%d번) 게시판이 생성되었습니다.\n", boardName, boardId);

	}

	private void search(String command) {
		String[] commandBits = command.split(" ");
		String searchKeyword = commandBits[2];

		int page = 0;

		if (commandBits.length >= 4) {
			try {
				page = Integer.parseInt(commandBits[3]);
			} catch (NumberFormatException e) {
				System.out.println("페이지 번호는 양의 정수로 입력해주세요.");
				return;
			}
		}

		if (page <= 1) {
			page = 1;
		}

		List<Article> searchResultArticles = new ArrayList<>();
		for (Article article : articleService.getArticles()) {
			if (article.title.contains(searchKeyword)) {
				searchResultArticles.add(article);
			}
		}

		int itemsInAPage = 10;
		int startPos = searchResultArticles.size() - 1;
		startPos -= (page - 1) * itemsInAPage;
		int endPos = startPos - (itemsInAPage - 1);

		if (searchResultArticles.size() == 0) {
			System.out.println("검색결과가 존재하지 않습니다.");
			return;
		}
		if (startPos < 0) {
			System.out.printf("%d페이지는 존재하지 않습니다.\n", page);
			return;
		}
		if (endPos < 0) {
			endPos = 0;
		}
		System.out.println("== 게시물 검색 ==");
		System.out.println("번호 / 제목");
		for (int i = startPos; i >= endPos; i--) {
			Article article = searchResultArticles.get(i);
			System.out.printf("%d / %s\n", article.id, article.title);
		}

	}

	private void modify(String command) {
		if (Container.session.isLogout()) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}

		int inputedId = 0;
		try {
			inputedId = Integer.parseInt(command.split(" ")[2]);
		} catch (NumberFormatException e) {
			System.out.println("게시물 번호는 양의 정수를 입력해 주세요.");
			return;
		}

		System.out.printf("수정할 제목 : ");
		String title = Container.scanner.nextLine();
		System.out.printf("수정할 제목 : ");
		String body = Container.scanner.nextLine();

		Article article = articleService.modify(inputedId, title, body);

		if (article == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
			return;
		}

		System.out.printf("%d번 게시물이 수정되었습니다.\n", inputedId);

	}

	private void delete(String command) {

		if (Container.session.isLogout()) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}

		int inputedId = 0;
		try {
			inputedId = Integer.parseInt(command.split(" ")[2]);
		} catch (NumberFormatException e) {
			System.out.println("게시물 번호는 양의 정수를 입력해 주세요.");
			return;
		}

		Article article = articleService.getArticle(inputedId);

		if (article == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
			return;
		}

		articleService.remove(inputedId);
		System.out.printf("%d번 게시물이 삭제 되었습니다.\n", inputedId);

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
		if (article == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
			return;
		}

		Member member = memberService.getMemberById(article.memberId);

		System.out.println("== 게시물 상세 ==");
		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("작성자 : %s\n", member.name);
		System.out.printf("제목 : %s\n", article.title);
		System.out.printf("내용 : %s\n", article.body);
	}

	private void list(String command) {
		int boardId = Container.session.selectedBoardId;
		Board board = articleService.getBoardById(boardId);

		int page = 0;
		try {
			page = Integer.parseInt(command.split(" ")[2]);
		} catch (ArrayIndexOutOfBoundsException e) {
			page = 1;
		} catch (NumberFormatException e) {
			System.out.println("페이지 번호는 양의 정수를 입력해 주세요.");
			return;
		}

		if (page <= 1) {
			page = 1;
		}

		List<Article> selectedBoardArticles = new ArrayList<>();
		for (Article article : articleService.getArticles()) {
			if (article.boardId == boardId) {
				selectedBoardArticles.add(article);
			}
		}

		int itemsInAPage = 10;
		int startPos = selectedBoardArticles.size() - 1;
		startPos -= (page - 1) * itemsInAPage;
		int endPos = startPos - (itemsInAPage - 1);

		if (selectedBoardArticles.size() == 0) {
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

		System.out.printf("== %s 게시판 게시물 리스트 ==\n", board.name);
		System.out.println("번호 / 작성자 / 제목");

		for (int i = startPos; i >= endPos; i--) {
			Article article = selectedBoardArticles.get(i);
			Member member = memberService.getMemberById(article.memberId);
			System.out.printf("%d / %s / %s\n", article.id, member.name, article.title);
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

		int memberId = Container.session.loginedMemberId;
		int boardId = Container.session.selectedBoardId;

		int id = articleService.add(title, body, memberId, boardId);
		System.out.printf("%d번 게시물이 생성되었습니다.\n", id);
	}

}
