package com.sun00j.dogleg.utils;

import java.util.Random;

public class GameUtil {

	private int cards[] = new int[162];
	public int[] washCard() {
		initCards();
		Random rand = new Random();
		int a=0;
		for(int i=0;i<162;i++) {
			a=rand.nextInt(162);
			cards[i]=a+1;
			cards[a]=i+1;
		}
		return cards;
	}
	private void initCards() {
		for(int i=0;i<162;i++) {
			cards[i]=i+1;
		}
	}
}
