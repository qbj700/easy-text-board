package com.sbs.example.easytextboard;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int LastArticleid = 0;
		while (true) {
			System.out.printf("명령어 입력 :");
			String command = scanner.nextLine();

			if (command.equals("article add")) {
				System.out.println("== 게시물 작성 ==");

				System.out.printf("제목 :");
				String title = scanner.nextLine();
				System.out.printf("내용 :");
				String body = scanner.nextLine();
				int id = LastArticleid + 1;

				System.out.println("== 생성된 게시물 정보 ==");
				System.out.printf("번호 : %d\n", id);
				System.out.printf("제목 : %s\n", title);
				System.out.printf("내용 : %s\n", body);
				
				LastArticleid = id;

			} else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");
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