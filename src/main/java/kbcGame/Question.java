package kbcGame;

import java.util.Scanner;

public class Question{
	
	static QuestionDetail[] questionList = new QuestionDetail[11];
	static {
		//1st question
		questionList[0] = new QuestionDetail();
		questionList[0].setQuestionStatement("Which of the following is the capital of India?");
		questionList[0].setOptions(new String[] {"Mumbai", "New Delhi", "Kolkata", "Hyderabad"});
		questionList[0].setAns('B');
		
		//2nd Question
		questionList[1] = new QuestionDetail();
		questionList[1].setQuestionStatement("Which of the following is the highest mountain peak in India?");
		questionList[1].setOptions(new String[] {"Mount Everest", "Kanchenjunga", "Nanda Devi", "Makalu"});
		questionList[1].setAns('A');
		
		//3rd Question
		questionList[2] = new QuestionDetail();
		questionList[2].setQuestionStatement("Which of the following is the largest desert in India?");
		questionList[2].setOptions(new String[] {"Ladakh Desert", "Rajasthan Desert", "Kutch Desert", "Thar Desert"});
		questionList[2].setAns('D');
		
		//4th Question
		questionList[3] = new QuestionDetail();
		questionList[3].setQuestionStatement("What is the longest river in the world?");
		questionList[3].setOptions(new String[] {"The Yangtze River", "The Amazon River", "The Nile River", "The Yellow River"});
		questionList[3].setAns('C');
		
		//5th Question
		questionList[4] = new QuestionDetail();
		questionList[4].setQuestionStatement("Who ordered Kattapa to kill Bahubali?");
		questionList[4].setOptions(new String[] {"Kalakeya", "Shivgamini", "Avantika", "Dev Sena"});
		questionList[4].setAns('B');
		
		//6th Question
		questionList[5] = new QuestionDetail();
		questionList[5].setQuestionStatement("The Indian state with the largest coastline is");
		questionList[5].setOptions(new String[] {"Gujarat", "Maharastra", "Tamil Nadu", "Andhra pradesh"});
		questionList[5].setAns('A');
		
		//7th Question
		questionList[6] = new QuestionDetail();
		questionList[6].setQuestionStatement("Who is the only Indian to have won the Wimbledon singles title?");
		questionList[6].setOptions(new String[] {"Sania Mirza", "Leander Paes", "Vijay Amritraj", "Mahesh Bhupathi"});
		questionList[6].setAns('C');
		
		//8th Question
		questionList[7] = new QuestionDetail();
		questionList[7].setQuestionStatement("Which Indian cricketer holds the record for taking the most wickets in Test cricket?");
		questionList[7].setOptions(new String[] {"Kapil Dev", "Anil Kumble", "Javagal Srinath", "Ishant Sharma"});
		questionList[7].setAns('B');
		
		//9th Question
		questionList[8] = new QuestionDetail();
		questionList[8].setQuestionStatement("What is the name of the world's highest waterfall?");
		questionList[8].setOptions(new String[] {"Yosemite Falls", "Niagara Falls", "Victoria Falls", "Angel Falls"});
		questionList[8].setAns('D');
		
		//10th Question
		questionList[9] = new QuestionDetail();
		questionList[9].setQuestionStatement("Who is the Greek divine force of the ocean?");
		questionList[9].setOptions(new String[] {"Hermus", "Apollo", "poseidon", "zeus"});
		questionList[9].setAns('C');
		
		//11th Question
		questionList[10] = new QuestionDetail();
		questionList[10].setQuestionStatement("Who is the Railway Minister of England?");
		questionList[10].setOptions(new String[] {"Huw Merriman", "Paul Maynard", "Ashwini Karl", "Hayashi Yoshima"});
		questionList[10].setAns('A');
	}
	
	Scanner sc = new Scanner(System.in);
	
	User player;
	LifeLine life;
	int queNo;
	//Constructor
	public Question(User player) {
		this.player = player;
		life = new LifeLine(this.player,this);
		queNo = player.getQueNo();
		if(player.skipQuestionStatus) {
			queNo--;
		}
	}
	
	final static int amountperQue[] = {5000, 10000, 20000, 50000, 100000, 500000, 1000000, 5000000, 10000000, 50000000};
	
//General Functions
	
	//questions
	void question() {
		//check of 10 questions are completed
		if(queNo>=10) {
			System.out.println("\033[32m\033[1mYou won the Game..");
			System.out.println("# 5 Crore..\033[0m\033[0m");
			System.out.println("Thanks for Playing...");
			printAmitabh();
			exitGameSeperator();
			return;
		}
		
		//print Question
		printQue();
		
		//print Options
		printOptions();
		
		//Take input
		queAnswer();
		
			
	}
	
	//Print Questions
	void printQue() {
		System.out.println(queNo+1 +") "+questionList[player.getQueNo() - 1].getQuestionStatement());
		System.out.print("	");
	}
	
	
	//Print Options
	void printOptions() {
		String arr[] = questionList[player.getQueNo() - 1].getOptions();
		for(int i=0; i<arr.length; i++) {
			System.out.print((char)(i+65)+") "+arr[i]+" ");
		}
		System.out.println();
		System.out.println("	E) *Get a Life Line");
	}

	//take answer Input
	void queAnswer() {
		//get User input of answer
		System.out.println("Type your answer:");
		char userAns = sc.next().charAt(0);
		userAns = Character.toUpperCase(userAns);
		
		//lifeline
		if(userAns=='A' || userAns=='B' || userAns=='C' || userAns=='D') {
			boolean nextQue = checkAns(userAns, questionList[player.getQueNo() - 1].getAns());
			if (nextQue) {
				System.out.println("\033[32m\033[1mCorrect Answer...\033[0m\033[0m");
				player.addAmount(amountperQue[queNo++]);
				System.out.println("Amount Won: \033[1m₹"+player.getAmount()+"\033[0m");
				if(queNo<10) {
					queSeperator();
				}
				player.nextQue();
				question();
			}else {
				System.out.println("\033[1m\033[31mWrong Answer..\033[0m\033[0m");
				System.out.println(player.getName()+" is Exited From the Game.");
				System.out.println("\033[32m\033[1mFinal Amount Won: ₹"+player.getAmount()+"\033[0m\033[0m");
				exitGameSeperator();
			}	
		}else if(userAns=='E') {
			life.useLifeLine(questionList[player.getQueNo() - 1].getOptions(), questionList[player.getQueNo() - 1].getAns());
			
		}else {
			System.out.println("Wrong Option Selected. Try again!!");
			queAnswer();
		}
	}
	
	
	//Check Answers
	boolean checkAns(char userAns, char finalAns){
		if(Character.toUpperCase(userAns)==finalAns)
			return true;
		return false;
	}
	
	
	void queSeperator() {
		System.out.println("\n------------------- Next Question -------------------\n");
	}
	
	void exitGameSeperator() {
		System.out.println("\n------------------- Game Over -------------------\n");
	}
	
	void printAmitabh() {
		System.out.println(".........:0WMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMW0dddl:d\r\n"
				+ "..........:kNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNK0OOkkkkkk0NWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWOdol:lK\r\n"
				+ "c'.........'ckNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNOoc:;,,',;;,;cOWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWKxddcl0W\r\n"
				+ "NOc'.........,lONMMMMMMMMMMMMMMMMMMMMMMMMMWKo:cc::;;;;::;,.;OWMMMMMMMMMMMMMMMMMMMMMMMMMMMMW0kxdlcOWM\r\n"
				+ "MMNk:.........',ckXMMMMMMMMMMMMMMMMMMMMMMMXo:ldddoolc::;;,..cXMMMMMMMMMMMMMMMMMMMMMMMMMMMXdcodo:oXMM\r\n"
				+ "MMMMXx;..........'c0WMMMMMMMMMMMMMMMMMMMMMXocdxxxxxxol:;,'..,kMMMMMMMMMMMMMMMMMMMMMMMMMMKl..;lc,oNMM\r\n"
				+ "MMMMMMXd,..........:dOXWMMMMMMMMMMMMMMMMMM0c;::::clc:;,'.....dWMMMMMMMMMMMMMMMMMMMMMMMW0c....,,,kWMM\r\n"
				+ "MMMMMMMWKl'.........',;lxKWMMMMMMMMMMMMMMWkccolclllc;;;'.....xWMMMMMMMMMMMMMMMMMMMMMMW0:.......lXMMM\r\n"
				+ "MMMMMMMMMNo..............;oxO0KNWMMMMMMMMWOoddoloddooll:'.',:OMMMMMMMMMMMMMMMMMMMMMMNk;.......;0MMMM\r\n"
				+ "MMMMMMMMMM0;................'',cOWMMMMMMMW0ddooxxxxxdllc:coldXMMMMMMMMMMMMMMMMMMMMN0l'...... .kWMMMM\r\n"
				+ "MMMMMMMMMMWO:...................;dkOO00000Odlclodxkxolllcc;;kWMMMMMMMMMMMMMMMMWXOdc'....... .oNMMMMM\r\n"
				+ "MMMMMMMMMMMMXxc'............'......',;;,,,;ldoodxxxdoool:';OWMMMMMMMMMMMMMWN0dc;..........  ,KMMMMMM\r\n"
				+ "MMMMMMMMMMMMMMWKkl,..................''....;dxkkkxxdc:,'..;ONK00000OOOOOkkd:'...........   .oWMMMMMM\r\n"
				+ "MMMMMMMMMMMMMMMMMWN0d:......................,::cc;;,.......;c:;;;,'''''...............    .lKMMMMMMM\r\n"
				+ "MMMMMMMMMMMMMMMMMMMMMN0o,.......................'........',,,'''.......................;lxKWMMMMMMMM\r\n"
				+ "MMMMMMMMMMMMMMMMMMMMMMMWNOc....................''.....,;;:;,'......................'cxKNMMMMMMMMMMMM\r\n"
				+ "MMMMMMMMMMMMMMMMMMMMMMMMMMNd'................''......',,,,,'.........        .....:0WMMMMMMMMMMMMMMM\r\n"
				+ "MMMMMMMMMMMMMMMMMMMMMMMMMMMW0l'...............''..''................         ...'oXMMMMMMMMMMMMMMMMM\r\n"
				+ "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMNO:..............'''''''''.............         .,oKWMMMMMMMMMMMMMMMMMM\r\n"
				+ "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMKl............'''..'''''.............       .:xXWMMMMMMMMMMMMMMMMMMMM\r\n"
				+ "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWx'...........''.....................     'dKWMMMMMMMMMMMMMMMMMMMMMMM\r\n"
				+ "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWx'...........''......................   ;KMMMMMMMMMMMMMMMMMMMMMMMMMM\r\n"
				+ "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWk'....................................  cXMMMMMMMMMMMMMMMMMMMMMMMMMM\r\n"
				+ "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMk,....................................  ,0MMMMMMMMMMMMMMMMMMMMMMMMMM\r\n"
				+ "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMO,...........,'......................   'OMMMMMMMMMMMMMMMMMMMMMMMMMM\r\n"
				+ "");
	}
	


}
