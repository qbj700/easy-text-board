package com.sbs.example.easytextboard.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbs.example.easytextboard.dto.Article;

public class ArticleDao {

	private List<Article> articles;
	private int lastArticleId;

	public ArticleDao() {
		articles = new ArrayList<>();
		lastArticleId = 0;

		makeTestData();
	}

	private void makeTestData() {
		for (int i = 1; i <= 5; i++) {
			add("제목" + i, "내용" + i, 1);
		}
		for (int i = 6; i <= 10; i++) {
			add("제목" + i, "내용" + i, 2);
		}

	}

	public int add(String title, String body, int loginedMemberId) {
		Article article = new Article();
		article.id = lastArticleId + 1;
		article.memberId = loginedMemberId;
		article.title = title;
		article.body = body;

		articles.add(article);
		lastArticleId = article.id;

		return article.id;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public Article getArticleByIndex(int i) {
		return articles.get(i);
	}

	public Article getArticle(int inputedId) {
		int index = getIndexById(inputedId);
		if (index == -1) {
			return null;
		}
		return articles.get(index);
	}

	private int getIndexById(int inputedId) {
		for (int i = 0; i < articles.size(); i++) {
			if (articles.get(i).id == inputedId) {
				return i;
			}
		}
		return -1;
	}

	public void remove(int inputedId) {
		int index = getIndexById(inputedId);
		articles.remove(index);
	}

	public Article modify(int inputedId, String title, String body) {
		Article article = getArticle(inputedId);
		article.title = title;
		article.body = body;
		return article;

	}

}
