package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonDAO personDAO;

	@Override
	public boolean insertPerson(String name, int yyyy, int mm, int dd) {
		return insertPerson(name, intToDate(yyyy, mm, dd));
	}
	private boolean insertPerson(String name, Date birth) {
		return personDAO.set(new Person(name, 0, birth));
	}

	@Override
	public List<Person> findAllPeople() {
		return personDAO.getAll();
	}

	@Override
	public List<Person> findPeopleByName(String name) {
		return findAllPeople()
				.stream()
				.filter(p->p.getName().equals(name))
				.collect(Collectors.toList());
	}

	@Override
	public List<Person> findPeopleByTodayDate() {
		return findPeopleByDate(new Date());
	}
	private List<Person> findPeopleByDate(Date date) {
		return findAllPeople()
				.stream()
				.filter(p->this.compareDate(p.getBirth(), date, "MM/dd"))
				.collect(Collectors.toList());
	}
	// get people by people's age >= a specific age
	@Override
	public List<Person> findPeopleByAgeLarger(int age) {
		return findAllPeople()
				.stream()
				.filter(p->p.getAge() >= age)
				.collect(Collectors.toList());
	}
	@Override
	public boolean updateDateByName(String name, int yyyy, int mm, int dd) {
		return updateDateByName(name, intToDate(yyyy, mm, dd));
	}
	private boolean updateDateByName(String name, Date date) {
		boolean result = false;
		List<Person> people = findAllPeople();
		for (Person p: people) {
			if (p.getName().equals(name)) {
				p.setBirth(date);
				result = personDAO.setAll(people);
				break;
			}
		}
		return result;
	}

	@Override
	public boolean deletePersonByDate(String name) {
		boolean result = false;
		List<Person> people = findAllPeople();
		for (int i=0; i<people.size(); i++ ) {
			if (people.get(i).getName().equals(name)) {
				people.remove(i);
				result = personDAO.setAll(people);
				result = true;
				break;
			}
		}
		return result;
	}
	private Date intToDate(int yyyy, int mm, int dd) {
		Date birth = null;
		try {
			birth =  new SimpleDateFormat("yyyy/MM/dd").parse(yyyy + "/" + mm + "/" + dd);
		} catch (ParseException e) {
			System.out.println("非法日期");
		}
		return birth;
	}
	private boolean compareDate(Date date1, Date date2, String format) {
		SimpleDateFormat dateOf = new SimpleDateFormat(format);
		boolean result = false;
		if (dateOf.format(date1).equals(dateOf.format(date2)))
			result = true;
		return result;
	}
}
