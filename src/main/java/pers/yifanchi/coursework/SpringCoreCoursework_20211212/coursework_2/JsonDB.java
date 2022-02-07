package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Component
public class JsonDB {
	private static final String PATHSTR = "./src/main/resources/json/person.json";
	@Autowired
	private Gson gson;
	// Json File 資料庫存放地
	private static final Path PATH = Paths.get(PATHSTR);
	// query all
	public List<Person> queryAll() throws IOException{
		String jsonString = Files.readAllLines(PATH).stream().collect(Collectors.joining());
		Type type = new TypeToken<ArrayList<Person>>() {}.getType();
		List<Person> people = gson.fromJson(jsonString, type);
		
		// two ways operate Date to Year
		// use LocalDate
//		LocalDate todayLocalDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//		people.forEach(person->{
//			LocalDate birthdayLocalDate = person.getBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//			person.setAge(todayLocalDate.getYear()-birthdayLocalDate.getYear());
//		});
		// use Calendar
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int todayYear = calendar.get(Calendar.YEAR);
		people.forEach(person->{
			calendar.setTime(person.getBirth());
			int age = todayYear - calendar.get(Calendar.YEAR);
			person.setAge(age);
		});
		return people;
	}
	
	public boolean add(Person person) throws IOException{
		/**
		 * 	homework 1: 
		 *  check if the object exists in DB
		 */
		boolean result = false;
		List<Person> people = this.queryAll();
		for (Person p: people) {
			if (!(p.getName().equals(person.getName()))
					&&compareDate(p.getBirth(), person.getBirth(), "yyyy/MM/dd")) {
				people.add(person);
				String newJsonString = gson.toJson(people);
				Files.write(PATH, newJsonString.getBytes("UTF-8"));
				result = true;
				break;
			}
		}
		return result;
	}
	
	public void update(List<Person> people) throws UnsupportedEncodingException, IOException {
		String newJsonString = gson.toJson(people);
		Files.write(PATH, newJsonString.getBytes("UTF-8"));
	}
	
	private boolean compareDate(Date date1, Date date2, String format) {
		SimpleDateFormat dateOf = new SimpleDateFormat(format);
		boolean result = false;
		if (dateOf.format(date1).equals(dateOf.format(date2)))
			result = true;
		return result;
	}
}
