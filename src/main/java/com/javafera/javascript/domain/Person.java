package com.javafera.javascript.domain;


public class Person {
	
	private String name;
	private String age;

	public void apresentation(){
		System.out.println("Hello. My name is "+ name + ". I'm "+ age +" years old.");
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
