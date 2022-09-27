package commentary.engine;

public class Player {
	
	private int playerId;
	private String playerName;
	private int playerJersyNo;
	private String playerPosition;
	
	
	public Player() {
		super();
	}


	public Player(int playerId, String playerName, int playerJersyNo, String playerPosition) {
		super();
		this.playerId = playerId;
		this.playerName = playerName;
		this.playerJersyNo = playerJersyNo;
		this.playerPosition=playerPosition;
	}


	public int getPlayerId() {
		return playerId;
	}


	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}


	public String getPlayerName() {
		return playerName;
	}


	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


	public int getPlayerJersyNo() {
		return playerJersyNo;
	}


	public void setPlayerJersyNo(int playerJersyNo) {
		this.playerJersyNo = playerJersyNo;
	}


	public String getPlayerPosition() {
		return playerPosition;
	}


	public void setPlayerPosition(String playerPosition) {
		this.playerPosition = playerPosition;
	}		
	
}
