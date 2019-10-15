package cn.just.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.just.service.BookService;
import cn.just.vo.Book;

@Controller
public class PageableController {
	
	@Autowired
	// 向前台展示总书列表
	public BookService bookService;
	List<Book> list = new ArrayList<Book>();
	List<Book> currentlist = new ArrayList<Book>(); // 一页的书列表
	@RequestMapping("/pageable")
	public String getBook(ModelMap model) {
//		@PageableDefault(size=4,page=1,sort="id,asc")	//默认每页大小为4，从第一页开始，按id升序排列
		Pageable pageable=new PageRequest(1, 4);
		list = bookService.getBook();
		int size=pageable.getPageSize();
		int page=pageable.getPageNumber();
		int index=(page-1)*size;
		for(int i=index;i<index+size;i++) {
			currentlist.add(list.get(i));
		}
		model.addAttribute("currentlist", currentlist);
		return "/jsp/list.jsp?pages=" + page;
	}
	
}
