package com.sun00j.dogleg.dto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
	OutputStream os ;
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
		if(os!=null) {
			try {
				os.write(cards.toString().getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
			System.out.println("Person run");
			br = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
			os = mSocket.getOutputStream();
			String clientString = null;
			while ((clientString = readFormClient()) != null) {
				//"index#1#id#1"
				System.out.println(clientString);
				if(clientString.contains("index")) {
					int index = Integer.parseInt(clientString.split("#")[1]);
					int id = Integer.parseInt(clientString.split("#")[3]);
					this.id = id;
					table.broadcastMsg(clientString);
				} else if(clientString.contains("ready")) {//id#1#ready
					this.isReady = true;
					table.broadcastMsg(clientString);
					if(table.isAllReady()) {
						table.start();
					}
				}else if(table.isAllReady()) {
					table.broadcastMsg(clientString);
				}
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
			this.isReady = false;
			table.socketList.remove(mSocket);
			table.persons.remove(this);
		}
		return null;
	}
	public void sendMsg(String msg){
		if(os!=null) {
			try {
				os.write((msg+"\n").getBytes());
				os.flush();
				System.out.println("id:"+id+" send msg"+ msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
