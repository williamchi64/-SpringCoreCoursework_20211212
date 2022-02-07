package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Person {

	private String name;
	private Integer age;
	private Date birth;

	public Person() {
		super();
	}

	public Person(String name, Integer age, Date birth) {
		super();
		this.name = name;
		this.age = age;
		this.birth = birth;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", birth=" + birth + "]";
	}
}
