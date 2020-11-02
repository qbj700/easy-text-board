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

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

	// 게시물에 배열 번호 부여
	public Article getArticle(int id) {
		return articleDao.getArticle(id);
	}

	// 게시물 삭제 함수
	public void remove(int id) {
		articleDao.remove(id);
	}

	// 게시물 생성 함수
	public int add(String title, String body, int i) {
		return articleDao.add(title, body, i);
	}

	// 게시물 수정 함수
	public void modify(int id, String title, String body) {
		articleDao.modify(id, title, body);
	}

	public int getArticleSize() {
		return articleDao.getArticleSize();
	}

	public Article getArticleByIndex(int i) {
		return articleDao.getArticleByIndex(i);
	}

}
