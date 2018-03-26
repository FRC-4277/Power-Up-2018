package org.usfirst.frc.team4277.robot.util;

public class GameData {
	String gameData;

	public GameData(String data) {
		gameData = data;
	}

	public Boolean isCloseSwitchLeft() {
		if (!hasProperGameData()) return null;
		Boolean isSwitchLeft = null;
		if (gameData.length() > 0) {
			if (gameData.charAt(0) == 'L') {
				isSwitchLeft = true;
			} else if (gameData.charAt(0) == 'R') {
				isSwitchLeft = false;
			}
		}
		return isSwitchLeft;
	}
	
	public boolean hasProperGameData() {
		return gameData != null;
	}
}
