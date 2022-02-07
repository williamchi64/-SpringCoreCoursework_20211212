package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class Entity {
	protected <T, R> String listToString(List<T> objs, Function<T, R> mapper) {
		return (objs == null || objs.size()==0)? 
				null: objs.stream().map(mapper).collect(Collectors.toList()).toString();
	}
}
