package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_1;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Teacher extends PersonImpl {
	
	private List<String> experties;
	private Map<String, Integer> salary;
	
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
	
	public List<String> getExperties() {
		return experties;
	}

	public void setExperties(List<String> experties) {
		this.experties = experties;
	}

	public Map<String, Integer> getSalary() {
		return salary;
	}

	public void setSalary(Map<String, Integer> salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Teacher [experties=" + experties + ", salary=" + salary + ", id=" + id + ", name=" + name + ", clazzes="
				+ clazzes + "]";
	}

}
