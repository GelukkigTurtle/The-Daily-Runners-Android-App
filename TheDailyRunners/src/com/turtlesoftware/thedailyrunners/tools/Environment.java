package com.turtlesoftware.thedailyrunners.tools;


public class Environment {

	public static final int races = 0;
	public static final int news = 1;
	public static final int groups = 2;
	public static int current_environment = races;
	public static String current_url;
	
	public static void setURLToEnv() {

		switch (current_environment) {
		case races:
			current_url = URLinks.URL_ALL_RACES;
			break;
		case news:
			current_url = URLinks.URL_ALL_NEWS;
			break;
		case groups:
			current_url = URLinks.URL_ALL_GROUPS;
			break;
		}

	}
	
	public static void setEnvironment(int type){
		current_environment = type;
		setURLToEnv();
		
	}
	
}
