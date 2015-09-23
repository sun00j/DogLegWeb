package com.sun00j.dogleg.dto;

import java.util.Arrays;


import com.sun00j.dogleg.utils.GameUtil;

public class Table {

	GameUtil mGameUtil = new GameUtil();
	Person []persons;
	int remainCard[];
	public Table() {
		persons = new Person[5];
		// TODO Auto-generated constructor stub
	}
	public void start() {
		int [] cards = mGameUtil.washCard();
		for(int i=0;i<5;i++) {
			int temCards[] = Arrays.copyOfRange(cards, i*31,(i+1)*31-1);
			persons[i].setCards(temCards);
		}
		remainCard = Arrays.copyOfRange(cards, 155, 161);
		
	}
	public void addPerson(int index, int id) {
		persons[index].setId(id);
	}

}
