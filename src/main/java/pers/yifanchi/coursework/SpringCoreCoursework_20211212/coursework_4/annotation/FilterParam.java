package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FilterParam {
	// err massage showed in log
	String value();
	/* 
	 * Returning true uses String.format(*,*) to generate err massage.
	 * Format parameters are signature parameters.
	 * Use %s to represent a parameter.
	 * Variable Arguments are detected as one parameter.
	 */
	boolean isFormat() default false;
	
	boolean disableNullFilter() default false;
}
