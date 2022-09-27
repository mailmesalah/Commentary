package commentary.play;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import commentary.engine.CommentGenerator;

public class Commentator {
	static int timeDif =1;
	public static void main(String[] arg){
		Boolean isPlaying = true;
		Random rand = new Random();
		CommentGenerator commentG= new CommentGenerator();
		CommentGenerator.initialise();
		//long startTime=System.currentTimeMillis();
		DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR, 7);
		c.set(Calendar.MINUTE, 30);
		while(true){
			System.out.println(dateFormat.format(c.getTime()) +": "+ CommentGenerator.nextComment());
			int  n = rand.nextInt(2000) + 100;
			try {
				Thread.sleep(n);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
			
			if(c.get(Calendar.HOUR)==8 && c.get(Calendar.MINUTE)==30){
				break;
			}
			c.set(Calendar.MINUTE, c.get(Calendar.MINUTE)+timeDif);
		}
		
		System.out.println(dateFormat.format(c.getTime()) +": Long Wistle.. and the game ends");
		System.out.println(dateFormat.format(c.getTime()) +" "+CommentGenerator.getWinMessage());
		System.out.println(dateFormat.format(c.getTime()) +" "+CommentGenerator.getScore());
	}

}
