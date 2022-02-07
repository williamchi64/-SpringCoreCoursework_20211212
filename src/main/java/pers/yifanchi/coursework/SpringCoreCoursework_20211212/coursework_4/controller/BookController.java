package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dto.BookTransactDTO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception.DAOException;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception.ServiceException;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.service.BookService;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;

	public void buyBook(Integer wid, Map<Integer, Integer> bookOrder) {
		BookTransactDTO bookTransactDTO = new BookTransactDTO(wid, bookOrder);
		try {
			bookService.booksTransact(bookTransactDTO);
		} catch (ServiceException|DAOException e) {
			System.err.println(e.getMessage());
		}
	}
}
