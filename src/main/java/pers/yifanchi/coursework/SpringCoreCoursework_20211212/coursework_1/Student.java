package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_1;

import java.util.Set;

public class Student extends PersonImpl {

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
		
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Set<Clazz> getClazzes() {
		return this.clazzes;
	}

	@Override
	public void setClazzes(Set<Clazz> clazzes) {
		this.clazzes = clazzes;
		
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", clazzes=" + clazzes + "]";
	}



}
