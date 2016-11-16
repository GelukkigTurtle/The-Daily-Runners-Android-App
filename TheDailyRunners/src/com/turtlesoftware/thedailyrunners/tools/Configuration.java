package com.turtlesoftware.thedailyrunners.tools;

import java.text.SimpleDateFormat;

public class Configuration {
	
	//General
	
	public static final String LOG_TAG = "TheDailyRunners";
	public static final String FACEBOOK_ID_APP = "1496074030638310";
	public static final String GOOGLE_ID_APP = "824014620655";
	public static final String SERVER_GCM_URL = "http://thedailyrunners.com/gcm_server_php/register.php";
	public static final String FACEBOOK_NAMESPACE = "tdrnamespace";
	
	//Fonts
	public static final String GENERAL_FONT_NAME = "fonts/DejaWeb.ttf";
	public static final String MENU_FONT_NAME = "fonts/RaspoutineMedium_TB.otf"; //Cuadrada
	public static final String BUTTON_FONT_NAME = "fonts/acmesab.TTF";
	public static final String TITLE_FONT_NAME = "fonts/adelleregularwebfont.ttf";
	public static final String NUMBER_FONT_NAME = "fonts/DINPro-Medium.otf";

	//Formats
	
	public static final SimpleDateFormat hourformat = new SimpleDateFormat("hh:mm a");
	public static final SimpleDateFormat dateformat = new SimpleDateFormat("dd / MM / yyyy");
	public static final SimpleDateFormat calendardateformat = new SimpleDateFormat("dd");
	public static final SimpleDateFormat raceinfodateformat = new SimpleDateFormat("EEE dd / MM / yyyy");



	

}
