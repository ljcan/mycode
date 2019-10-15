package cn.just.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.just.service.BookService;
import cn.just.vo.Book;

@Controller
public class BookController {
	@Autowired
	// 向前台展示总书列表
	public BookService bookService;
	List<Book> list = new ArrayList<Book>();
	int total; // 图书的总数
	int pages; // 前台视图显示的页数
	// 分页
	int currentpage; // 当前页
	List<Book> currentlist = new ArrayList<Book>(); // 一页的书列表
	int count;
	
	@RequestMapping("/book")
	public String getBook(int indexpages, ModelMap model, HttpServletRequest req) {
		list = bookService.getBook();
		total = list.size();
		pages = total / 4 + 1; // 列表页数
		count = (indexpages - 1) * 4 + 1;
		currentlist.clear();
		if (indexpages != pages)
			for (int i = count - 1, j = 0; j < 4; i++, j++) {
//				System.out.println("============" + list.get(i).getBname());
				currentlist.add(list.get(i));
			}
		if (indexpages == pages)
			for (int i = count - 1, j = 0; j < total % 4; i++, j++) {
				System.out.println("============" + list.get(i).getBname());
				currentlist.add(list.get(i));
			}
		model.addAttribute("currentlist", currentlist);
//		System.out.println("==========" + pages);
		return "/jsp/booklist.jsp?pages=" + pages;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
}
