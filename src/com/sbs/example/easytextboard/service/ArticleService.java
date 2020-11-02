package com.sbs.example.easytextboard.service;

import java.util.ArrayList;
import java.util.List;

import com.sbs.example.easytextboard.dto.Article;

public class ArticleService {

	private List<Article> articles;
	private int lastArticleId;

	public List<Article> getArticles() {
		return articles;
	}

	public ArticleService() {
		lastArticleId = 0;
		articles = new ArrayList<>();

		for (int i = 0; i < 32; i++) {
			add("제목" + (i + 1), "내용" + (i + 1), i % 2 == 0 ? 1 : 2);
		}

	}

	// 게시물에 배열 번호 부여
	public Article getArticle(int id) {
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
	public void remove(int id) {
		int index = getIndexById(id);

		if (index == -1) {
			return;
		}
		articles.remove(index);
	}

	// 게시물 생성 함수
	public int add(String title, String body, int i) {

		Article article = new Article(lastArticleId + 1, title, body, i);

		articles.add(article);
		lastArticleId = article.id;

		return article.id;
	}

	// 게시물 수정 함수
	public void modify(int inputedId, String title, String body) {
		Article article = getArticle(inputedId);

		article.title = title;
		article.body = body;
	}

	public int getArticleSize() {
		return articles.size();
	}

	public Article getArticleByIndex(int i) {
		return articles.get(i);
	}

}
