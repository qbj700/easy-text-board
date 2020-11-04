package com.sbs.example.easytextboard.service;

import java.util.List;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.dao.ArticleDao;
import com.sbs.example.easytextboard.dto.Article;

public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public int add(String title, String body, int loginedMemberId) {
		return articleDao.add(title, body, loginedMemberId);

	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

	public Article getArticleByIndex(int i) {
		return articleDao.getArticleByIndex(i);
	}

	public Article getArticle(int inputedId) {

		return articleDao.getArticle(inputedId);
	}

	public void remove(int inputedId) {
		articleDao.remove(inputedId);

	}

	public Article modify(int inputedId, String title, String body) {

		return articleDao.modify(inputedId, title, body);

	}

}
