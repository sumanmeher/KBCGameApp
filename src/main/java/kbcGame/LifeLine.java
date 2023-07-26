package kbcGame;

import java.util.Random;
import java.util.Scanner;


class LifeLine implements LifeLineInterface{
	Scanner sc = new Scanner(System.in);
	User player;
	Question que;
	
	LifeLine(User player, Question que){
		this.player = player;
		this.que=que;
	}
	
	void showLifeLine() {
		System.out.println("Lifeline Left:"+player.totalLifeLine);
		if(!player.fiftyFiftyStatus)
			System.out.print("\033[9m");
		System.out.println("A) 50-50");
		System.out.print("\033[0m");
		
		if(!player.audiencePollStatus)
			System.out.print("\033[9m");
		System.out.println("B) Audience Poll");
		System.out.print("\033[0m");
		
		if(!player.skipQuestionStatus)
			System.out.print("\033[9m");
		System.out.println("C) Skip the Question");
		System.out.print("\033[0m");
		
		
	}
	
	void useLifeLine(String optnArr[], char queAns) {
		
		if(player.totalLifeLine<=0) {
			System.out.println("\033[31mNo Lifeline is Left to use.\033[0m \nPlease select a option to Register the answer.");
			que.queAnswer();
			return;
		}
		
		showLifeLine();
		System.out.println("Enter LifeLine Option:");
		char lifeOpt = sc.next().charAt(0);
		lifeOpt = Character.toUpperCase(lifeOpt);
		
		//selct type of lifeline
		if(lifeOpt=='A') {
			fiftyFifty(optnArr,queAns);
			
		}else if(lifeOpt=='B') {
			audiencePoll(optnArr ,queAns);
			
		}else if(lifeOpt == 'C'){
			skipQuestion(optnArr, queAns);
			que.question();
		}else {
			System.out.println("Wrong Option Selected. Please Try again..");
			useLifeLine(optnArr, queAns);
			
		}
	}
	
	@Override
	public void fiftyFifty(String optnArr[] ,char answ) {
		if(!player.fiftyFiftyStatus) {
			System.out.println("\033[31mYou have Already used this Life Line\033[0m");
			System.out.println("Please select other Lifeline:");
			useLifeLine(optnArr, answ);
			return;
		}
		
		player.fiftyFiftyStatus = false;
		player.totalLifeLine--;
		char availArr[] = new char[3];
		int count = 0;
		for(int i=65; i<=68;i++) {
			if((char)i != answ) {
				availArr[count++] = (char)(i);
			}
		}
		
		Random random = new Random();
		int randomNum = random.nextInt(3);
		
		//print The Question
		que.printQue();
		
		for(int i=65; i<=68; i++) {
			if((char)i != answ && (char)i!=availArr[randomNum]) {
				System.out.print((char)i+" ______ ");
			}else {
				System.out.print((char)i+") "+optnArr[i-65]+" ");
			}
		}
		
		System.out.println("\n	E) *Get a Life Line");
		que.queAnswer();
		
	}
	
	@Override
	public void audiencePoll(String optnArr[], char answ) { 
		
		if(!player.audiencePollStatus) {
			System.out.println("\033[31mYou have Already used this Life Line\033[0m");
			System.out.println("Please select other Lifeline:");
			useLifeLine(optnArr, answ);
			return;
		}
		
		player.audiencePollStatus = false;
		player.totalLifeLine--;
		Random random = new Random();
		
		int arr[] = new int[3];
		int total=0;
		for(int i = 0; i<3; i++) {
			arr[i] = random.nextInt(20);
			total+=arr[i];
		}
		int opt = 0;
		for(int i=0;i<4;i++) {
			char ch = (char)(65+i);
			if(ch==answ)
				System.out.println(ch+")"+(100-total)+"%");
			else
				System.out.println(ch+")"+arr[opt++]+"%");
		}
		que.queAnswer();
	}

	

	@Override
	public void skipQuestion(String optnArr[], char answ) {
		if(!player.skipQuestionStatus) {
			System.out.println("\033[31mYou have Already used this Life Line\033[0m");
			System.out.println("Please select other Lifeline:");
			useLifeLine(optnArr, answ);
			return;
		}
		
		player.skipQuestionStatus = false;
		player.totalLifeLine--;
		player.nextQue();
		
	}

}
