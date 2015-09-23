package com.sun00j.dogleg.dto;

public class Person {
	private int id;
	private String name;
	private int score;
	private int[] cards;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int[] getCards() {
		return cards;
	}
	public void setCards(int[] cards) {
		this.cards = cards;
	}
}
