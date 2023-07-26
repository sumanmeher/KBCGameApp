package kbcGame;

//import kbcGame.User;

public class Launch {

	public static void main(String[] args) {
		User player = new User();
		System.out.println("Please Enter your Name: ");
		player.setName();
		System.out.println("Welcome \033[1m"+ player.getName() +"\033[0m to Crorepati Game");
		
		//Rules
		System.out.println();
		System.out.println("\033[1mGame Rules:\033[0m");
		System.out.println("* You have to answer 10 Questions Correctly to Win this Game.");
		System.out.println("* Every Question will have 4 Options.");
		System.out.println("* You have 3 LifeLine to use.");
		System.out.println("	1. 50-50 : Elemenate 2 options.");
		System.out.println("	2. Audience Poll : Get Audience vote on all options.");
		System.out.println("	3. Skip the Question : Skips the question.");
		System.out.println("* One LifeLine can be used only once.");
		System.out.println("* Wrong answer will lead to Elimation.");
		System.out.println("* Winner will get a Reward of 5 Crore.");
		
		System.out.println();
		System.out.println("Here are Your Questions. \n");
		
		//Rules End
		Question que = new Question(player);
		que.question();
	}

}
