package com.sbs.example.easytextboard.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbs.example.easytextboard.dto.Article;
import com.sbs.example.easytextboard.dto.Board;

public class ArticleDao {

	private List<Article> articles;
	private List<Board> boards;
	private int lastArticleId;
	private int lastBoardId;

	public ArticleDao() {
		articles = new ArrayList<>();
		boards = new ArrayList<>();
		lastArticleId = 0;
		lastBoardId = 0;

	}

	public int add(String title, String body, int loginedMemberId, int boardId) {
		Article article = new Article();
		article.id = lastArticleId + 1;
		article.memberId = loginedMemberId;
		article.boardId = boardId;
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

	public int makeBoard(String boardName) {
		Board board = new Board();
		board.id = lastBoardId + 1;
		board.name = boardName;

		boards.add(board);
		lastBoardId = board.id;

		return board.id;
	}

	public Board getBoardById(int inputedId) {
		for (Board board : boards) {
			if (board.id == inputedId) {
				return board;
			}
		}
		return null;
	}

	public List<Board> getBoards() {
		return boards;
	}

}
