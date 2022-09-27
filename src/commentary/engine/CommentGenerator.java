package commentary.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommentGenerator {
	
	//Last action for finding next action if required
	static FootballAction nextAction=FootballAction.START;
	//Last player on focus
	static Player lastPlayer = new Player();
	
	//For welcome comments
	static List<String> matchWelcome = new ArrayList<>();
	static List<String> teamIntroduction = new ArrayList<>();
	static List<String> playStart = new ArrayList<>();
	static List<String> passInitiated = new ArrayList<>();
	static List<String> passReceived = new ArrayList<>();
	static List<String> passTackled = new ArrayList<>();
	static List<String> shots = new ArrayList<>();
	static List<String> outs = new ArrayList<>();
	static List<String> throwss = new ArrayList<>();
	static List<String> throwings = new ArrayList<>();
	static List<String> corners = new ArrayList<>();
	static List<String> cornerTakings = new ArrayList<>();
	static List<String> headings = new ArrayList<>();
	static List<String> goalyCatches = new ArrayList<>();
	static List<String> goalyPunches = new ArrayList<>();
	static List<String> goals = new ArrayList<>();
	static List<String> fouls = new ArrayList<>();
	
	static List<FootballAction> afterPassInitiated = new ArrayList<FootballAction>();
	static List<FootballAction> afterPassReceived = new ArrayList<FootballAction>();
	static List<FootballAction> afterPassTackled = new ArrayList<FootballAction>();
	static List<FootballAction> afterShot = new ArrayList<FootballAction>();
	static List<FootballAction> afterOut = new ArrayList<FootballAction>();
	static List<FootballAction> afterThrow = new ArrayList<FootballAction>();
	static List<FootballAction> afterThrowing = new ArrayList<FootballAction>();
	static List<FootballAction> afterCorner = new ArrayList<FootballAction>();
	static List<FootballAction> afterTakingCorner = new ArrayList<FootballAction>();
	static List<FootballAction> afterHeading = new ArrayList<FootballAction>();
	static List<FootballAction> afterGoalyCatch = new ArrayList<FootballAction>();
	static List<FootballAction> afterGoalyPunch = new ArrayList<FootballAction>();
	static List<FootballAction> afterfoul = new ArrayList<FootballAction>();
	
	static Team[] teams = new Team[2];
	static int currenTeam=0;
	static int currentPlayer=0;
	static int[] score =new int[2];	
	
	
	public CommentGenerator(){
		
	}
	
	private static void initDatas(){
		matchWelcome.add("Hi, Good evening and welcome to All for this exciting football match between x1 and x2.");
		matchWelcome.add("Hi, Today we are here for the football match between x1 and x2 that we were waiting for");
		matchWelcome.add("Whether seems to be nice, which welcomes you all for todays match between x1 and x2.");
		
		teamIntroduction.add("Lets introduce the teams, Team x1 has x10 as GoalKeeper x11, x12 and x13 plays as centre backs x14 is at left back x15 at right back, center midfields are covered by x16 and x17, right midfield has x18 and left midfield has x19 and x110 play at center forward."
				+ "and Team x2 has x21 as GoalKeeper x22 and x23 plays as centre backs x24 is at left back x25 at right back, center midfields are covered by x26 and x27, right midfield has x28 and left midfield has x29. x210 and x211 play at center forward.");
		teamIntroduction.add("The teams as you know are x1 and x2 where for team x1 players are x10,x11,x12,x13,x14,x15,x16,x17,x18,x19 and x110 "
				+ "and for team x2 players are x20,x21,x22,x23,x24,x25,x26,x27,x28,x29 and x210.");
		
		playStart.add("It is started.");
		playStart.add("And the game starts by x1.");
		
		//Player 1 to Player two same team
		passInitiated.add("A pass from xx to xy");
		passInitiated.add("xx passes the ball to xy");
		passInitiated.add("xx tries a pass to xy");
		
		//Only current player same team
		passReceived.add("xx takes the ball.");
		passReceived.add("xx nicely taken the ball.");
		passReceived.add("that was a nice effortless take by xx.");
		
		//Other team player
		passTackled.add("It's just tackled by xx.");
		passTackled.add("xx takes the ball nicely.");
		passTackled.add("xx tackles the pass and takes the ball.");
		passTackled.add("Oh that was a nice tackle by xx. Now he moves with it.");
		
		//current player
		shots.add("xx takes a huge shot.");
		shots.add("And xx takes the big shot.");
		shots.add("A fantastic shot from xx.");
		shots.add("That was a powerful shot by xx");
		
		outs.add("And it goes to out.");
		outs.add("That went no were near the post and its out.");
		outs.add("the ball went out.");
		outs.add("And the ball safely touches out.");
		
		throwss.add("Its a throw by xx.");
		throwss.add("And it goes to throw.");
		throwss.add("and thus a throw.");
		
		throwings.add("xx takes the throw.");
		throwings.add("xx takes a big throw.");
		throwings.add("That was a nice throw by xx");
		
		corners.add("And its a corner.");
		corners.add("its corner.");
		corners.add("xx gets a corner.");
		
		cornerTakings.add("xx takes the corner.");
		cornerTakings.add("Thats a nice corner kick by xx.");
		cornerTakings.add("xx kicks the ball wide.");
		cornerTakings.add("Corner kick nicely taken by xx.");
		
		headings.add("Nicely headed by xx.");
		headings.add("xx heads it away.");
		headings.add("xx jumps so high to take the heading.");
		
		goalyCatches.add("That was a nice catch by xx.");
		goalyCatches.add("xx saves for now.");
		goalyCatches.add("Nice save by xx.");
		goalyCatches.add("Andthe ball safely rests in xx's hands.");
		
		goalyPunches.add("The ball is punched away by xx.");
		goalyPunches.add("xx punches the ball away, nicely.");
		goalyPunches.add("xx jumps forward to punch the ball away.");
		
		goals.add("That was a fantastic goal by xx.");
		goals.add("And it touches the net.");
		goals.add("Goooaallll.... That was nice one.");
		
		fouls.add("That is a foul and warned by referee xx");
		fouls.add("Its a foul");
		fouls.add("Thats a nasty foul.");
	}
	
	private static void initAfterFootballAction(){
		
		afterPassInitiated.add(FootballAction.PASS_RECEIVED);
		afterPassInitiated.add(FootballAction.PASS_TACKLED);
		afterPassInitiated.add(FootballAction.OUT);
		afterPassInitiated.add(FootballAction.THROW);
		afterPassInitiated.add(FootballAction.HEADING);
		afterPassInitiated.add(FootballAction.FOUL);
		
		afterPassReceived.add(FootballAction.PASS_INITIATED);
		afterPassReceived.add(FootballAction.SHOT);
		afterPassReceived.add(FootballAction.FOUL);
		
		afterPassTackled.add(FootballAction.PASS_INITIATED);
		afterPassTackled.add(FootballAction.SHOT);
		afterPassTackled.add(FootballAction.FOUL);
		afterPassTackled.add(FootballAction.THROW);
		afterPassTackled.add(FootballAction.OUT);
		afterPassTackled.add(FootballAction.CORNER);
		
		afterShot.add(FootballAction.PASS_RECEIVED);
		afterShot.add(FootballAction.PASS_TACKLED);
		afterShot.add(FootballAction.OUT);
		afterShot.add(FootballAction.THROW);
		afterShot.add(FootballAction.CORNER);
		afterShot.add(FootballAction.HEADING);
		afterShot.add(FootballAction.GOALY_CATCH);
		afterShot.add(FootballAction.GOALY_PUNCHES_AWAY);
		afterShot.add(FootballAction.GOAL);
		afterShot.add(FootballAction.FOUL);
		
		afterOut.add(FootballAction.PASS_INITIATED);
		afterOut.add(FootballAction.SHOT);
		
		afterThrow.add(FootballAction.THROWING);
		
		afterThrowing.add(FootballAction.PASS_RECEIVED);
		afterThrowing.add(FootballAction.PASS_TACKLED);
		afterThrowing.add(FootballAction.OUT);
		afterThrowing.add(FootballAction.CORNER);
		afterThrowing.add(FootballAction.HEADING);
		afterThrowing.add(FootballAction.GOALY_CATCH);
		afterThrowing.add(FootballAction.GOALY_PUNCHES_AWAY);
		afterThrowing.add(FootballAction.GOAL);
		afterThrowing.add(FootballAction.FOUL);
		
		afterCorner.add(FootballAction.CORNER_TAKING);
		
		afterTakingCorner.add(FootballAction.PASS_RECEIVED);
		afterTakingCorner.add(FootballAction.PASS_TACKLED);
		afterTakingCorner.add(FootballAction.OUT);
		afterTakingCorner.add(FootballAction.THROW);
		afterTakingCorner.add(FootballAction.CORNER);
		afterTakingCorner.add(FootballAction.GOALY_CATCH);
		afterTakingCorner.add(FootballAction.GOALY_PUNCHES_AWAY);
		afterTakingCorner.add(FootballAction.GOAL);
		afterTakingCorner.add(FootballAction.FOUL);
		
		afterHeading.add(FootballAction.PASS_RECEIVED);
		afterHeading.add(FootballAction.PASS_TACKLED);
		afterHeading.add(FootballAction.OUT);
		afterHeading.add(FootballAction.THROW);
		afterHeading.add(FootballAction.CORNER);
		afterHeading.add(FootballAction.GOALY_CATCH);
		afterHeading.add(FootballAction.GOALY_PUNCHES_AWAY);
		afterHeading.add(FootballAction.GOAL);
		afterHeading.add(FootballAction.FOUL);
		
		afterGoalyCatch.add(FootballAction.PASS_INITIATED);
		afterGoalyCatch.add(FootballAction.SHOT);
		
		afterGoalyPunch.add(FootballAction.PASS_RECEIVED);
		afterGoalyPunch.add(FootballAction.PASS_TACKLED);
		afterGoalyPunch.add(FootballAction.CORNER);
		
		afterfoul.add(FootballAction.PASS_INITIATED);
		afterfoul.add(FootballAction.SHOT);
		
	}
	
	public static String nextComment(){
		Random rand = new Random();
		int n=0;
		String returnString="";
		switch(nextAction){
		case START:
			nextAction=FootballAction.WELCOME;
			returnString = "Commentator Starts..."; 
			//Setting current team and player
			currenTeam=0;
			currentPlayer=10;
			
			return returnString;
			
		case WELCOME:
			
			n = rand.nextInt(matchWelcome.size());
			nextAction=FootballAction.INTRODUCE_TEAM;
			returnString = matchWelcome.get(n); 
			returnString=returnString.replaceAll("x1", teams[0].getTeamName());
			returnString=returnString.replaceAll("x2", teams[1].getTeamName());
			
			return returnString;
			
		case INTRODUCE_TEAM:

			n = rand.nextInt(teamIntroduction.size());
			nextAction=FootballAction.START_PLAY;
			
			returnString = teamIntroduction.get(n);
			for (int i = 0; i < teams[0].getPlayers().size(); i++) {
				returnString=returnString.replaceAll("x1"+i, teams[0].getPlayers().get(i).getPlayerName());
				returnString=returnString.replaceAll("x2"+i, teams[1].getPlayers().get(i).getPlayerName());
			}
			
			returnString=returnString.replaceAll("x1", teams[0].getTeamName());
			returnString=returnString.replaceAll("x2", teams[1].getTeamName());
			
			return returnString;
			
		case START_PLAY:
			n = rand.nextInt(playStart.size());
			nextAction=FootballAction.PASS_INITIATED;
			returnString = playStart.get(n); 						
			
			return returnString;
			
		case PASS_INITIATED:
			n = rand.nextInt(passInitiated.size());			
			
			returnString = passInitiated.get(n);
			returnString=returnString.replaceAll("xx", teams[currenTeam].getPlayers().get(currentPlayer).getPlayerName());
			setNextPlayer();
			returnString=returnString.replaceAll("xy", teams[currenTeam].getPlayers().get(currentPlayer).getPlayerName());
			
			//Next action
			n = rand.nextInt(afterPassInitiated.size());
			nextAction=afterPassInitiated.get(n);
			
			return returnString;
			
			
		case PASS_RECEIVED:
			n = rand.nextInt(passReceived.size());	
			
			returnString = passReceived.get(n);
			returnString=returnString.replaceAll("xx", teams[currenTeam].getPlayers().get(currentPlayer).getPlayerName());
			//Next action
			n = rand.nextInt(afterPassReceived.size());
			nextAction=afterPassReceived.get(n);
			
			return returnString;
			
		case PASS_TACKLED:
			n = rand.nextInt(passTackled.size());			
			
			returnString = passTackled.get(n);
			changeTeam();
			setNextPlayer();
			returnString=returnString.replaceAll("xx", teams[currenTeam].getPlayers().get(currentPlayer).getPlayerName());
			
			//Next action
			n = rand.nextInt(afterPassTackled.size());
			nextAction=afterPassTackled.get(n);
			
			return returnString;
			
		case SHOT:
			n = rand.nextInt(shots.size());			
			
			returnString = shots.get(n);
			returnString=returnString.replaceAll("xx", teams[currenTeam].getPlayers().get(currentPlayer).getPlayerName());	
			
			//Next action
			n = rand.nextInt(afterShot.size());
			nextAction=afterShot.get(n);
			
			return returnString;
			
		case OUT:
			n = rand.nextInt(outs.size());			
			returnString = outs.get(n);
			changeTeam();
			
			//Next action
			n = rand.nextInt(afterOut.size());
			nextAction=afterOut.get(n);
			
			return returnString;
			
		case THROW:
			n = rand.nextInt(throwss.size());			
			returnString = throwss.get(n);
			returnString=returnString.replaceAll("xx", teams[currenTeam].getPlayers().get(currentPlayer).getPlayerName());
			changeTeam();
			
			//Next action
			n = rand.nextInt(afterThrow.size());
			nextAction=afterThrow.get(n);
			
			return returnString;
			
		case THROWING:
			n = rand.nextInt(throwings.size());			
			returnString = throwings.get(n);
			returnString=returnString.replaceAll("xx", teams[currenTeam].getPlayers().get(currentPlayer).getPlayerName());
			
			//Next action
			n = rand.nextInt(afterThrowing.size());
			nextAction=afterThrowing.get(n);
			
			return returnString;
			
		case CORNER:
			n = rand.nextInt(corners.size());			
			returnString = corners.get(n);
			returnString=returnString.replaceAll("xx", teams[currenTeam].getTeamName());
			
			//Next action
			n = rand.nextInt(afterCorner.size());
			nextAction=afterCorner.get(n);
			
			return returnString;
			
		case CORNER_TAKING:
			n = rand.nextInt(cornerTakings.size());			
			returnString = cornerTakings.get(n);
			setNextPlayer();
			returnString=returnString.replaceAll("xx", teams[currenTeam].getPlayers().get(currentPlayer).getPlayerName());
			
			//Next action
			n = rand.nextInt(afterTakingCorner.size());
			nextAction=afterTakingCorner.get(n);
			
			return returnString;
			
		case HEADING:
			n = rand.nextInt(headings.size());			
			
			returnString = headings.get(n);
			setNextPlayer();
			returnString=returnString.replaceAll("xx", teams[currenTeam].getPlayers().get(currentPlayer).getPlayerName());			
			
			//Next action
			n = rand.nextInt(afterHeading.size());
			nextAction=afterHeading.get(n);
			
			return returnString;
			
		case GOALY_CATCH:
			n = rand.nextInt(goalyCatches.size());			
			
			returnString = goalyCatches.get(n);
			returnString=returnString.replaceAll("xx", teams[currenTeam].getPlayers().get(0).getPlayerName());			
			
			//Next action
			n = rand.nextInt(afterGoalyCatch.size());
			nextAction=afterGoalyCatch.get(n);
			
			return returnString;
			
		case GOALY_PUNCHES_AWAY:
			n = rand.nextInt(goalyPunches.size());			
			
			returnString = goalyPunches.get(n);
			returnString=returnString.replaceAll("xx", teams[currenTeam].getPlayers().get(0).getPlayerName());
			setNextPlayer();
			
			//Next action
			n = rand.nextInt(afterGoalyPunch.size());
			nextAction=afterGoalyPunch.get(n);
			
			return returnString;
			
		case GOAL:
			n = rand.nextInt(goals.size());			
			
			returnString = goals.get(n);
			returnString=returnString.replaceAll("xx", teams[currenTeam].getPlayers().get(currentPlayer).getPlayerName());
			score[currenTeam]++;
			
			changeTeam();
			setNextPlayer();			
			
			//Printing the Score
			System.out.println("Score "+teams[0].getTeamName()+" "+score[0]+" "+teams[1].getTeamName()+" "+score[1]);
			
			//Next action
			nextAction=FootballAction.START_PLAY;
			
			return returnString;
			
		case FOUL:
			n = rand.nextInt(fouls.size());			
			returnString = fouls.get(n);
			returnString=returnString.replaceAll("xx", teams[currenTeam].getPlayers().get(currentPlayer).getPlayerName());
			changeTeam();
			setNextPlayer();
			
			//Next action
			n = rand.nextInt(afterfoul.size());
			nextAction=afterfoul.get(n);
			
			return returnString;
			
		}
		
		
		return "Comment";
	}
	
	public static void initialise(){
		setTeams();
		initDatas();
		initAfterFootballAction();
	}
	
	private static void setTeams(){
		//First team
		teams[0] = new Team();
		teams[0].setTeamName("Real Madrid");
		teams[0].getPlayers().add(new Player(0, "Luca Zidane", 30, "GOALKEEPER"));
		teams[0].getPlayers().add(new Player(1, "Dani Carvajal", 2, "DEFENDER"));
		teams[0].getPlayers().add(new Player(2, "Sergio Ramos", 30, "DEFENDER"));
		teams[0].getPlayers().add(new Player(3, "Alejandro Salto", 44, "DEFENDER"));
		teams[0].getPlayers().add(new Player(4, "Toni Kroos", 8, "MIDFIELDER"));
		teams[0].getPlayers().add(new Player(5, "Casemiro", 14, "MIDFIELDER"));
		teams[0].getPlayers().add(new Player(6, "Lucas Vazquez", 17, "MIDFIELDER"));
		teams[0].getPlayers().add(new Player(7, "Luka Modric", 10, "MIDFIELDER"));
		teams[0].getPlayers().add(new Player(8, "Karim Benzema", 9, "FORWARD"));
		teams[0].getPlayers().add(new Player(9, "Alvaro Morata", 21, "FORWARD"));
		teams[0].getPlayers().add(new Player(10, "Cristiano Ronaldo", 7, "FORWARD"));
		
		//Second team
		teams[1]=new Team();
		teams[1].setTeamName("FC Barcelona");
		teams[1].getPlayers().add(new Player(0, "Marc-Andre Ter Stegen", 1, "GOALKEEPER"));
		teams[1].getPlayers().add(new Player(1, "Gerard Pique", 3, "DEFENDER"));
		teams[1].getPlayers().add(new Player(2, "Javier Marcherano", 14, "DEFENDER"));
		teams[1].getPlayers().add(new Player(3, "Jordi Alba", 18, "DEFENDER"));
		teams[1].getPlayers().add(new Player(4, "Arda Turan", 7, "MIDFIELDER"));
		teams[1].getPlayers().add(new Player(5, "Andres Iniesta", 8, "MIDFIELDER"));
		teams[1].getPlayers().add(new Player(6, "Rafinha", 12, "MIDFIELDER"));
		teams[1].getPlayers().add(new Player(7, "Wilfrid Kaptoum", 27, "MIDFIELDER"));
		teams[1].getPlayers().add(new Player(8, "Luis Suarez", 9, "FORWARD"));
		teams[1].getPlayers().add(new Player(9, "Lionel Messi", 10, "FORWARD"));
		teams[1].getPlayers().add(new Player(10, "Neymar", 11, "FORWARD"));
	}
	
	private static void changeTeam(){
		currenTeam=currenTeam==0?1:0;
	}
	private static void setNextPlayer(){		
		Random rand = new Random();
		int  n=0;
		while(( n= rand.nextInt(teams[currenTeam].getPlayers().size()-1) + 1)==currentPlayer);
		currentPlayer=n;
	}
	
	public static String getScore(){
		return "Score "+teams[0].getTeamName()+" "+score[0]+" "+teams[1].getTeamName()+" "+score[1];
	}
	
	public static String getWinMessage(){
		if(score[0]>score[1]){
			return teams[0].getTeamName() + " wins over "+teams[1].getTeamName();
		}else if(score[0]<score[1]){
			return teams[1].getTeamName() + " wins over "+teams[0].getTeamName();
		}else{
			return teams[0].getTeamName() + " ties with "+teams[1].getTeamName();
		}
	}
}
