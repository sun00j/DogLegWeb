package com.sun00j.dogleg.dto;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

import javax.net.ssl.SSLException;

import org.apache.commons.lang.ArrayUtils;

import com.sun00j.dogleg.utils.GameUtil;

public class Table implements Runnable{

	GameUtil mGameUtil = new GameUtil();
	List<Person> persons;
	int remainCard[];
	List<Socket> socketList;
	ServerSocket mServerSocket;
	public Table() {
		//persons = new Person[5];
		socketList = new ArrayList<Socket>();
		try {
			mServerSocket = new ServerSocket(6543);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}
	public void start() {
		int [] cards = mGameUtil.washCard();
		for(int i=0;i<5;i++) {
			int temCards[] = Arrays.copyOfRange(cards, i*31,(i+1)*31-1);
			Arrays.sort(temCards);
			persons.get(i).setCards(temCards);
		}
		remainCard = Arrays.copyOfRange(cards, 155, 161);
		broadcastMsg(remainCard.toString());
		try {
			Thread.sleep(2000);
			chooseBoss();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
//	public void addPerson(int index, int id) {
//		persons[index].setId(id);
//	}
	public void chooseBoss() {
		Random random = new Random();
		int index = random.nextInt(5);
		persons.get(index).setCards(ArrayUtils.addAll(persons.get(index).getCards(), remainCard));
		persons.get(index).isDizhu = true;
		broadcastMsg("chooseboss#"+index);
	}
	public void setGoutui() {
		int index = -1;
		if(Arrays.asList(persons.get(0).getCards()).contains(65)){
			persons.get(0).isGoutui=true;
			index = 0;
		} else if(Arrays.asList(persons.get(1).getCards()).contains(65)) {
			persons.get(1).isGoutui = true;
			index = 1;
		} else if(Arrays.asList(persons.get(2).getCards()).contains(65)) {
			persons.get(2).isGoutui = true;
			index = 2;
		} else if(Arrays.asList(persons.get(3).getCards()).contains(65)) {
			persons.get(3).isGoutui = true;
			index = 3;
		} else if(Arrays.asList(persons.get(4).getCards()).contains(65)) {
			persons.get(4).isGoutui = true;
			index = 4;
		}
		if(persons.get(index).isDizhu&&persons.get(index).isGoutui) {
			
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int socketCount = 0;
		while (socketCount < 5) {
				try {
					System.out.println("Table start");
					Socket mSocket = mServerSocket.accept();
					Person mPerson = new Person();
					mPerson.mSocket = mSocket;
					mPerson.table = this;
					socketList.add(mSocket);
					if(socketList.size()==5){
						start();
					}
					persons.add(mPerson);
					new Thread(mPerson).start();
					socketCount++;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
	}
	public boolean isAllReady() {
		// TODO Auto-generated method stub
		if ((persons.size() == 5)&&persons.get(0).isReady && persons.get(1).isReady && persons.get(2).isReady && persons.get(3).isReady
				&& persons.get(4).isReady) {
			return true;
		}
		return false;
	}
	public void broadcastMsg(String msg) {
		// TODO Auto-generated method stub
		for(int i=0;i<socketList.size();i++){
			persons.get(i).sendMsg(msg);
			System.out.println(msg);
		}
	}

}
