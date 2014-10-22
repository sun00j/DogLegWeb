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
			swap(i,a);
		}
		return cards;
	}
	private void initCards() {
		for(int i=0;i<162;i++) {
			cards[i]=i+1;
		}
	}
	private void swap(int a,int b) {
		int c=cards[a];
		cards[a]=cards[b];
		cards[b]=c;
	}
}
