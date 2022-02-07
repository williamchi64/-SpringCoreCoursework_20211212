package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.util.List;

public interface PersonService {
	boolean insertPerson(String name, int yyyy, int mm, int dd);
	List<Person> findAllPeople();
	List<Person> findPeopleByName(String name);
	List<Person> findPeopleByTodayDate();
	List<Person> findPeopleByAgeLarger(int age);
	boolean updateDateByName(String name, int yyyy, int mm, int dd);
	boolean deletePersonByDate(String name);
}
