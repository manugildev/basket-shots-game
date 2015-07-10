package com.madtriangle.basketball.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import MainGame.ActionResolver;
import MainGame.BasketballGame;

public class AndroidLauncher extends AndroidApplication implements ActionResolver {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useImmersiveMode = true;
		initialize(new BasketballGame(this), config);
	}

	@Override
	public void showOrLoadInterstital() {

	}

	@Override
	public void signIn() {

	}

	@Override
	public void signOut() {

	}

	@Override
	public void rateGame() {

	}

	@Override
	public void submitScore(long score) {

	}

	@Override
	public void showScores() {

	}

	@Override
	public boolean isSignedIn() {
		return false;
	}

	@Override
	public boolean shareGame(String msg) {
		return false;
	}

	@Override
	public void unlockAchievementGPGS(String string) {

	}

	@Override
	public void showAchievement() {

	}

	@Override
	public void submitGamesPlayed(long score) {

	}

	@Override
	public void viewAd(boolean view) {

	}

	@Override
	public void iapClick() {

	}

	@Override
	public void toast(String string) {

	}

	@Override
	public void viewVideoAd() {

	}

	@Override
	public void checkVideoAd() {

	}
}
