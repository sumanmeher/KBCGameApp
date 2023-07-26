package kbcGame;

import java.util.Scanner;

public class User{
	private String name;
	private int amount = 0;
	private int quesNo = 1;
	
	//LifeLine variables
	int totalLifeLine;
		
	boolean fiftyFiftyStatus;
	boolean audiencePollStatus;
	boolean skipQuestionStatus;
		
	User(){
		totalLifeLine=3;
		fiftyFiftyStatus=true;
		audiencePollStatus=true;
		skipQuestionStatus=true;
	}
	
	Scanner sc = new Scanner(System.in);

	void setName() {
		name = sc.nextLine();
	}
	
	String getName() {
		return this.name;
	}
	
	void addAmount(int val) {
		amount=val;
	}
	
	int getAmount() {
		return amount;
	}
	
	void nextQue() {
		quesNo++;
	}
	
	int getQueNo() {
		return quesNo;
	}
	
	
	
}
