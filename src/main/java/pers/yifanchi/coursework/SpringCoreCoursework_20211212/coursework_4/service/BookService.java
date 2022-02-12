package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.service;

import java.util.List;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dto.BookTransactDTO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Book;

public interface BookService {
	List<Book> getBookStocksByPage(Integer pageNum) throws Exception;
	Integer booksTransact(BookTransactDTO bookTransactDTO) throws Exception;
}
