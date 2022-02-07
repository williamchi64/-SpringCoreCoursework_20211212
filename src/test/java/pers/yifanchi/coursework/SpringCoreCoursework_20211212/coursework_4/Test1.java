package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.simpleflatmapper.converter.impl.time.ObjectToJavaInstantConverter;

public class Test1 {

	public static void main(String[] args) {
		/*
		List<Integer> i = new ArrayList<Integer>();
		i.add(1);
		i.add(2);
		i.add(3);
		System.out.println(i.stream().map(ia->ia+"\n").collect(Collectors.joining()));
		Integer[] a = new Integer[] {1,2,3,4};
		String s = Arrays.asList(a).stream().map(Object::toString).collect(Collectors.joining(", "));
		System.out.println(s);
		String format = "%s";
		format = String.format(format, s);
		System.out.println(format);
		int [] b = new int[] {1,2,3,4};
		boolean bl = Arrays.stream(b).allMatch(b1->b1==1);
		System.out.println(bl);
		System.out.println(duplicateSymbols(2, 3));
		*/
		Map<String,Object> map1 = new HashMap<>();
		Map<String,Object> map2 = new HashMap<>();
		Map<String,Object> map3 = new HashMap<>();
		List<Map<String,Object>> maps = new ArrayList<>();
		
		map1.put("GENERATED_KEY", 1);
		map2.put("GENERATED_KEY", 2);
		map3.put("GENERATED_KEY", 3);
		maps.add(map1);
		maps.add(map2);
		maps.add(map3);
		
		System.out.println(maps);
		List<?> list1 = maps.stream().map(e->e.get("GENERATED_KE")).collect(Collectors.toList());
		System.out.println(list1);
	}
	
	private static String duplicateSymbols(int size, int batch) {
		String symbols = String.join(", ", Collections.nCopies(batch, "?"));
		return String.join(", ", Collections.nCopies(batch, "("+symbols+")"));
	}

}
