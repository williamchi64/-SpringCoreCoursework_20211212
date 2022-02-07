package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_1;

import java.util.Set;

public interface Person {
	Integer getId();
	void setId(Integer id);
	String getName();
	void setName(String name);
	Set<Clazz> getClazzes();
	void setClazzes(Set<Clazz> clazzes);
}
