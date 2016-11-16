package com.turtlesoftware.thedailyrunners.tools;

/**
 * @author Freddy Ayala
 * @since 2014
 * Contiene todas las URL con las que se traen datos por medio de JSON
 */
public class URLinks {
	
	public final static String  HOST = "http://just-runners.com/";
	
	//Gallery
	public final static String URL_INSERT_USER = HOST+"query/user/saveUser.php";

	
	//Insert a user
	public final static String URL_GALLERY = HOST+"twitterflow/index.html";

	//Obteins races list
	public final static String URL_ALL_RACES = HOST+"query/races/getAllRaces.php";
	
	public final static String URL_COUNTRY_RACES = HOST+"query/races/getCountryRaces.php";
	
	public final static String URL_COUNTRY_RACES_FILTERED = HOST+"query/races/getCountryRacesFiltered.php";

	public final static String URL_INTERNATIONAL_RACES = HOST+"query/races/getInternationalRaces.php";

	public final static String URL_INTERNATIONAL_RACES_FILTERED = HOST+"query/races/getInternationalRacesFiltered.php";

	
	//Get a race assistance
	public final static String URL_GET_ASSISTANCE= HOST+"query/races/getRaceAssistance.php";

	//Set a race assistance
	public final static String URL_SET_ASSISTANCE= HOST+"query/races/setRaceAssistance.php";

	//Get Calendar Races
	public final static String URL_GET_MY_CALENDAR= HOST+"query/races/getMyCalendar.php";
	
	//Obteins a race images cover
	public final static String URL_RACE_COVER_IMAGES = HOST+"query/races/getRacesCoverImg.php";
	
	//Obteins all news
	public final static String URL_ALL_NEWS = HOST+"query/news/getAllNews.php";
	
	//Obtenis all groups
	public final static String URL_ALL_GROUPS = HOST+"query/groups/getAllGroups.php";
	
	//Obteins a group images cover
    public final static String URL_GROUP_COVER_IMAGES = HOST+"query/groups/getGroupsCoverImg.php";
    
    //get Country
    public final static String URL_COUNTRY = HOST+"query/ip/getIP.php";




	
	

}
