package commentary.engine;

import java.util.ArrayList;
import java.util.List;

public class Team {

	private String teamName;
	private List<Player> players = new ArrayList<>();
	
	public Team() {
		super();
	}

	public Team(String teamName, List<Player> players) {
		super();
		this.teamName = teamName;
		this.players = players;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}			
}
