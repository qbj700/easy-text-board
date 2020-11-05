package com.sbs.example.easytextboard;

import java.util.Scanner;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.controller.ArticleController;
import com.sbs.example.easytextboard.controller.Controller;
import com.sbs.example.easytextboard.controller.MemberController;
import com.sbs.example.easytextboard.controller.NullController;

public class App {
	private MemberController memberController;
	private ArticleController articleController;
	private NullController nullController;

	public App() {
		memberController = new MemberController();
		articleController = new ArticleController();
		nullController = new NullController();
	}

	public void run() {
		Scanner sc = Container.scanner;

		while (true) {
			System.out.printf("명령어 입력 : ");
			String command = sc.nextLine();

			if (command.equals("system exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			}
			Controller controller = getControllerByCommand(command);
			controller.doCommand(command);
		}

		sc.close();

	}

	private Controller getControllerByCommand(String command) {
		if (command.startsWith("member ")) {
			return memberController;
		} else if (command.startsWith("article ")) {
			return articleController;
		} else {
			return nullController;
		}
	}

}
