package com.anxiong.study;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(empty("«Î—°‘Ò.."));

	}
	 public static boolean empty(Object o) {
		    return (o == null) || ("".equals(o.toString().trim())) || 
		      ("null".equalsIgnoreCase(o.toString().trim())) || 
		      ("undefined".equalsIgnoreCase(o.toString().trim())) || 
		      ("«Î—°‘Ò...".equals(o.toString().trim()));
		  }
}
