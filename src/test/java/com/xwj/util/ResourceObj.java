package com.xwj.util;

import java.io.Serializable;

public class ResourceObj implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String path;
	
	private int age;
	
	private String content;
	
	public ResourceObj(String name, String path, int age) {
		super();
		this.name = name;
		this.path = path;
		this.age = age;
	}

	public ResourceObj(String name, String path, int age, String content) {
		super();
		this.name = name;
		this.path = path;
		this.age = age;
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
