package com.sun00j.dogleg.dto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Person implements Runnable{
	private int id;
	private String name;
	private int score;
	private int[] cards;
	public boolean isDizhu = false;
	public boolean isGoutui = false;
	public boolean isReady = false;
	private String address = "";
	Socket mSocket;
	Table table;
	BufferedReader br;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			br = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
			String clientString = null;
			while ((clientString = readFormClient()) != null) {
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String readFormClient() {
		// TODO Auto-generated method stub
		try {
			return br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			table.socketList.remove(mSocket);
		}
		return null;
	}
}
