package com.example.vocabulary;

public class MyWord {
	
	private int id;
	private String word;
	private String mean;
	private String example;
	private String category;
	public MyWord(int id, String word, String mean, String example,
			String category) {
		this.id = id;
		this.word = word;
		this.mean = mean;
		this.example = example;
		this.category = category;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getMean() {
		return mean;
	}
	public void setMean(String mean) {
		this.mean = mean;
	}
	public String getExample() {
		return example;
	}
	public void setExample(String example) {
		this.example = example;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	

}
