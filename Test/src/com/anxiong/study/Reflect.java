package com.anxiong.study;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;


class A {
}

/**
 * 反射
 * */
public class Reflect {

	public static void main(String[] args) {
		
		/**
		 * 方法一
		 * */

		A a = new A();

		Class<?> a1 = a.getClass();

		System.out.println("方法一" + a1);
		/**
		 * 方法二
		 * */

		Class<String> class1 = String.class;
		System.out.println("方法二" + class1);
		

		/**
		 * 方法三
		 * */

		Class<?> class2 = null;
		try {
			class2=Class.forName("java.lang.String");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("方法三" + class2);
		
		Constructor<?>[] s11=class2.getConstructors();
	    Method[] s1=class2.getMethods();
		System.out.println(Arrays.toString(s1));
		
		 


	}
}

