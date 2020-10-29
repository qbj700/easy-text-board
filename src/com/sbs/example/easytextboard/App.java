package com.sbs.example.easytextboard;

import java.util.Scanner;

import com.sbs.example.easytextboard.Controller.ArticleController;
import com.sbs.example.easytextboard.Controller.MemberController;

public class App {

	public void run() {
		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController();
		ArticleController articleController = new ArticleController();

		while (true) {

			System.out.printf("명령어 입력 :");
			String command = sc.nextLine();

			if (command.startsWith("article ")) {
				articleController.run(sc, command);
			} else if (command.startsWith("member ")) {
				memberController.run(sc, command);

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
