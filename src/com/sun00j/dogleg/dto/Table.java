package com.sun00j.dogleg.dto;

import java.util.Arrays;
import java.util.Random;

import org.apache.commons.lang.ArrayUtils;

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
			Arrays.sort(temCards);
			persons[i].setCards(temCards);
		}
		remainCard = Arrays.copyOfRange(cards, 155, 161);
		
	}
	public void addPerson(int index, int id) {
		persons[index].setId(id);
	}
	public void chooseBoss() {
		Random random = new Random();
		int index = random.nextInt(5);
		persons[index].setCards(ArrayUtils.addAll(persons[index].getCards(), remainCard));
		persons[index].isDizhu = true;
	}
	public void setGoutui() {
		int index = -1;
		if(Arrays.asList(persons[0]).contains(65)){
			persons[0].isGoutui=true;
			index = 0;
		} else if(Arrays.asList(persons[1]).contains(65)) {
			persons[1].isGoutui = true;
			index = 1;
		} else if(Arrays.asList(persons[2]).contains(65)) {
			persons[2].isGoutui = true;
			index = 2;
		} else if(Arrays.asList(persons[3]).contains(65)) {
			persons[3].isGoutui = true;
			index = 3;
		} else if(Arrays.asList(persons[4]).contains(65)) {
			persons[4].isGoutui = true;
			index = 4;
		}
		if(persons[index].isDizhu&&persons[index].isGoutui) {
			
		}
	}

}
