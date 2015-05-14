package com.anxiong.study;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;


class A {
}

/**
 * ����
 * */
public class Reflect {

	public static void main(String[] args) {
		
		/**
		 * ����һ
		 * */

		A a = new A();

		Class<?> a1 = a.getClass();

		System.out.println("����һ" + a1);
		/**
		 * ������
		 * */

		Class<String> class1 = String.class;
		System.out.println("������" + class1);
		

		/**
		 * ������
		 * */

		Class<?> class2 = null;
		try {
			class2=Class.forName("java.lang.String");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("������" + class2);
		
		Constructor<?>[] s11=class2.getConstructors();
	    Method[] s1=class2.getMethods();
		System.out.println(Arrays.toString(s1));
		
		 


	}
}
