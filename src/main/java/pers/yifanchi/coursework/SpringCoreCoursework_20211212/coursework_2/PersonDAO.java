package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.util.List;

public interface PersonDAO {
	public boolean set(Person person);
	public List<Person> getAll();
	public boolean setAll(List<Person> people);
}
