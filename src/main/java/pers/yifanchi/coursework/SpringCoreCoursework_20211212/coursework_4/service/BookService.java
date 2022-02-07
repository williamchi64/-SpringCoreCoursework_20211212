package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.service;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dto.BookTransactDTO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception.DAOException;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception.ServiceException;

public interface BookService {
	Integer booksTransact(BookTransactDTO bookTransactDTO) throws DAOException, ServiceException;
}
