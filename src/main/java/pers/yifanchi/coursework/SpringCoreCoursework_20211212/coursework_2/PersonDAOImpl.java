package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonDAOImpl implements PersonDAO {
	@Autowired
	private JsonDB jsonDB;
	
	@Override
	public boolean set(Person person) {
		boolean check = false;
		try {
			check = jsonDB.add(person);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public List<Person> getAll() {
		List<Person> people = null;
		try {
			people = jsonDB.queryAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return people;
	}

	@Override
	public boolean setAll(List<Person> people) {
		boolean result = false;
		try {
			jsonDB.update(people);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}
