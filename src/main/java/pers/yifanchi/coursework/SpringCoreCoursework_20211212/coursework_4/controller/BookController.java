package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dto.BookPrintDTO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dto.BookTransactDTO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Book;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.service.BookService;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	
	public List<BookPrintDTO> getBookList(Integer pageNum) {
		List<BookPrintDTO> bookPrintDTOs = null;
		try {
			List<Book> books = bookService.getBookStocksByPage(pageNum);
			bookPrintDTOs = books.stream().map(book->new BookPrintDTO(
				book.getBid(), book.getBname(), book.getPrice(), 
				book.getCreateTime(), book.getStock().getAmount()
			)).collect(Collectors.toList());
		} catch (Exception e) {}
		return bookPrintDTOs;
	}

	public Integer buyBook(Integer wid, Map<Integer, Integer> bookOrder) {
		BookTransactDTO bookTransactDTO = new BookTransactDTO(wid, bookOrder);
		Integer result = 0;
		try {
			result = bookService.booksTransact(bookTransactDTO);
		} catch (Exception e) {}
		return result;
	}
}
