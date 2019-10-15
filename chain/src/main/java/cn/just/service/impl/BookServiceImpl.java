package cn.just.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.just.dao.BookDao;
import cn.just.service.BookService;
import cn.just.vo.Book;
@Service("bookService")
public class BookServiceImpl implements BookService{
	@Autowired
	public BookDao bookDao;
	public List<Book> getBook() {
		return bookDao.getBook();
	}
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	public BookDao getBookDao() {
		return bookDao;
	}
}
