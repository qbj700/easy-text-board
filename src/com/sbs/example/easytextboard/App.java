package com.sbs.example.easytextboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

	private List<Article> articles;
	private List<Member> members;
	private int lastArticleId;
	private int lastMemberCount;
	private int loginInformation;

	public App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
		lastArticleId = 0;
		loginInformation = -1;

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

	// 회원 가입 함수
	private int join(String name, String id, String pw) {

		Member member = new Member();
		member.count = lastMemberCount + 1;
		member.name = name;
		member.id = id;
		member.pw = pw;

		members.add(member);
		lastMemberCount = member.count;

		return member.count;
	}

	public void run() {
		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.printf("명령어 입력 :");
			String command = sc.nextLine();

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
					continue;
				}

				if (page <= 1) {
					page = 1;
				}

				System.out.println("== 게시물 리스트 ==");

				if (articles.size() == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
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
					continue;
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
					continue;
				}

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
				int inputedId = 0;
				try {
					inputedId = Integer.parseInt(command.split(" ")[2]);
				} catch (NumberFormatException e) {
					System.out.println("게시물 번호를 양의 정수로 입력해주세요.");
					continue;
				}

				System.out.println("== 게시물 삭제 ==");

				Article article = getArticle(inputedId);

				if (article == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
					continue;
				}
				remove(inputedId);
				System.out.printf("%d번 게시물이 삭제되었습니다.\n", inputedId);

			} else if (command.startsWith("article modify ")) {
				int inputedId = 0;
				try {
					inputedId = Integer.parseInt(command.split(" ")[2]);
				} catch (NumberFormatException e) {
					System.out.println("게시물 번호를 양의 정수로 입력해주세요.");
					continue;
				}

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

				String[] commandBits = command.split(" ");
				String searchKeyword = commandBits[2];

				int page = 1;

				if (commandBits.length >= 4) {
					try {
						page = Integer.parseInt(commandBits[3]);
					} catch (NumberFormatException e) {
						System.out.println("페이지 번호를 양의 정수로 입력해주세요.");
						continue;
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
					continue;
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
					continue;
				}

				for (int i = startPos; i >= endPos; i--) {
					Article article = searchResultArticles.get(i);

					System.out.printf("%d / %s\n", article.id, article.title);
				}

			} else if (command.equals("member join")) {
				System.out.println("== 회원 가입 ==");

				System.out.printf("이름을 입력하세요 :");
				String name = sc.nextLine();
				System.out.printf("로그인 ID를 입력하세요 :");
				String id = sc.nextLine();
				System.out.printf("로그인 PW를 입력하세요 :");
				String pw = sc.nextLine();

				int count = join(name, id, pw);

				System.out.printf("%d번 회원으로 회원가입이 완료되었습니다.\n", count);

			} else if (command.equals("member login")) {
				System.out.println("== 로그인 ==");

				System.out.printf("로그인 ID를 입력하세요 :");
				String id = sc.nextLine();
				System.out.printf("로그인 PW를 입력하세요 :");
				String pw = sc.nextLine();

				int count = login(id, pw);

				if (count == -1) {
					System.out.println("로그인 아이디 또는 페스워드가 잘못 입력되었습니다.");
					continue;
				}

				System.out.printf("로그인 성공 %s님 반갑습니다.\n", members.get(count).name);

			} else if (command.equals("member whoami")) {
				System.out.println("== 로그인 정보 ==");

				if (loginInformation < 0) {
					System.out.println("현재 로그인 중인 회원이 존재하지 않습니다.");
					continue;
				}

				System.out.printf("현재 로그인 아이디 : %s\n", members.get(loginInformation).id);
				System.out.printf("이름 : %s\n", members.get(loginInformation).name);

			} else if (command.equals("member logout")) {

				if (loginInformation < 0) {
					System.out.println("현재 로그인 중인 회원이 존재하지 않습니다.");
					continue;
				}

				System.out.println("== 정상적으로 로그아웃 되었습니다. ==");
				loginInformation = -1;

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
