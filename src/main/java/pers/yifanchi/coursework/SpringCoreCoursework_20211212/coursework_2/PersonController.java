package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PersonController {
	@Autowired
	private PersonService personService;
	// insert one person
	public void insertPerson(String name, int yyyy, int mm, int dd){
		boolean check = false;
		if (existCheck(yyyy, mm, dd)) {
			check = personService.insertPerson(name, yyyy, mm, dd);
		}
		System.out.println("add person:" + check);
	}
	// get all people
	public List<Person> findAllPeople() {
		return personService.findAllPeople();
	}
	// get people by name
	public List<Person> findPeopleByName(String name) {
		return personService.findPeopleByName(name);
	}
	// get people by today date
	public List<Person> findPeopleByTodayDate() {
		return personService.findPeopleByTodayDate();
	}
	// get people who 
	public List<Person> findPeopleByAgeLarger(int age) {
		return personService.findPeopleByAgeLarger(age);
	}
	// update birthday by name
	public void updateDateByName(String name, int yyyy, int mm, int dd) {
		boolean check = false;
		if (existCheck(yyyy, mm, dd)) {
			check = personService.updateDateByName(name, yyyy, mm, dd);
		}
		System.out.println("update birthday:" + check);
		
	}
	// delete person by name
	public void deletePersonByName(String name) {
		boolean check = personService.deletePersonByDate(name);
		System.out.println("delete person:" + check);
	}
	
	private boolean existCheck(Object ... objs) {
		for (Object obj: objs) {
			if (obj.equals(0)) {
				return false;
			}
		}
		return true;
	}
		
}
