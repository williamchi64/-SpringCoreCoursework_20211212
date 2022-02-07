package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3;

import java.util.Collections;
import java.util.List;

public class Test1 {

	public static void main(String[] args) {
		int num1 = 7;
		int num2 = 23;
		int num3 = 19;
		String s = "| %-"+num1+"s | %-"+num2+"s | %-"+num3+"s | %s\n";
		String s2 = String.join("",Collections.nCopies(num1+2, "-"));
		String s3 = String.join("",Collections.nCopies(num2+2, "-"));
		String s4 = String.join("",Collections.nCopies(num3+2, "-"));
		System.out.printf(s, 1,2,3,4);
		System.out.println("+" +s2 + "+"+s3+"+"+s4+"+");
		String ss1 = "state";    
		String ss2 = "method_name";
		String ss3 = "log_timestamp";
		System.out.println(ss1.length() +" " + ss2.length() +" "+ ss3.length());
	}

}
