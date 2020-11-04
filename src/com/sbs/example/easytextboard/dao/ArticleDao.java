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
	}

	public int add(String title, String body) {
		Article article = new Article();
		article.id = lastArticleId + 1;
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

}
