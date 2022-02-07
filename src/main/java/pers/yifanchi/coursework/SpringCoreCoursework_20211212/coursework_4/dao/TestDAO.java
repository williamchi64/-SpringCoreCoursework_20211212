package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class TestDAO {
	public List<Integer> test(Integer i, Integer...ts) {
		List<Integer> tsl = new ArrayList<Integer>();
		for (Integer t: ts) {
			tsl.add(t);
		}
		tsl.add(i);
		return tsl;
	}
	public List<Integer> test2(Integer i, Integer i2) {
		List<Integer> tsl = new ArrayList<Integer>();
		tsl.add(i);
		tsl.add(i2);
		return tsl;
	}
}
