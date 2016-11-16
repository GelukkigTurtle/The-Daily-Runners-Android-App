package com.turtlesoftware.thedailyrunners.tools;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.turtlesoftware.thedailyrunners.main.R;

public class Tools {
	
	
	static String tmpMonth = "";
	
	@SuppressLint("SimpleDateFormat") public static boolean monthChanged(Date date){
		boolean changed = false;
		DateFormat df = new SimpleDateFormat("MM");
		String month = df.format(date);
		if(!month.equals(tmpMonth)){
			tmpMonth = month;
			changed = true;
		}
		return changed;
	}
	
	@SuppressLint("SimpleDateFormat") public static String getMonthName(Context context, Date date){
		String monthName = "Error";
		DateFormat df = new SimpleDateFormat("MM");
		String monthNumber = df.format(date);
	
		if(monthNumber.equals("01")){
			monthName =  context.getString(R.string.m_january);
		}
		if(monthNumber.equals("02")){
			monthName = context.getString(R.string.m_february);
		}
		if(monthNumber.equals("03")){
			monthName =context.getString(R.string.m_march);
		}
		if(monthNumber.equals("04")){
			monthName = context.getString(R.string.m_april);
		}
		if(monthNumber.equals("05")){
			monthName = context.getString(R.string.m_may);
		}
		if(monthNumber.equals("06")){
			monthName = context.getString(R.string.m_june);
		}
		if(monthNumber.equals("07")){
			monthName = context.getString(R.string.m_july);
		}
		if(monthNumber.equals("08")){
			monthName = context.getString(R.string.m_august);
		}
		if(monthNumber.equals("09")){
			monthName = context.getString(R.string.m_september);
		}
		if(monthNumber.equals("10")){
			monthName = context.getString(R.string.m_october);
		}
		if(monthNumber.equals("11")){
			monthName = context.getString(R.string.m_november);
		}
		if(monthNumber.equals("12")){
			monthName = context.getString(R.string.m_december);
		}
		return monthName;
	}


      public static void applyFont(final Context context, final View root, final String fontName) {
        try {
            if (root instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) root;
                for (int i = 0; i < viewGroup.getChildCount(); i++)
                    applyFont(context, viewGroup.getChildAt(i), fontName);
            } else if (root instanceof TextView){
            	if(root.getTag().equals("title")){
            		((TextView) root).setTypeface(Typeface.createFromAsset(context.getAssets(), Configuration.TITLE_FONT_NAME));
            	}else if(root.getTag().equals("button")){
            		((TextView) root).setTypeface(Typeface.createFromAsset(context.getAssets(), Configuration.BUTTON_FONT_NAME));

            	}else if(root.getTag().equals("general")){
            		((TextView) root).setTypeface(Typeface.createFromAsset(context.getAssets(),  Configuration.GENERAL_FONT_NAME));
            	}
	            }else if(root.getTag().equals("number")){
	        		((TextView) root).setTypeface(Typeface.createFromAsset(context.getAssets(),  Configuration.GENERAL_FONT_NAME));
	        	
	            }
            	
                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
      
      public static Typeface setFontFamily(Context context,String fontPath){
  		Typeface font = Typeface.createFromAsset(context.getAssets(), fontPath);
  		return font;
  	}
      
      public static boolean isOnline(Context context) {
    	    ConnectivityManager cm =
    	        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
    	    if (netInfo != null && netInfo.isConnected()) {
    	        return true;
    	    }
    	    return false;
    	}
      
      public static void changeTabText(ViewGroup tabLayout, int index){
  	    if(tabLayout.getChildCount()-1 >= index){
  	        TextView tv = (TextView)tabLayout.getChildAt(index);
  	        setTypeface(tv, Configuration.BUTTON_FONT_NAME);
  	       // tv.setTextColor(color.black);
  	    }
  	}
  	
  	public static void setTypeface(TextView view, String font) {
  	    Typeface typeface = Typeface.createFromAsset(view.getContext().getAssets(), font);
  	    view.setTypeface(typeface);
  	}
  	
  	public static void changeTitleAppFont(Context context, Activity myActivityReference){
  		 int actionBarTitle = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
         TextView actionBarTitleView = (TextView) myActivityReference.getWindow().findViewById(actionBarTitle);
         Typeface robotoBoldCondensedItalic = Typeface.createFromAsset(context.getAssets(),Configuration.MENU_FONT_NAME);
         if(actionBarTitleView != null){
             actionBarTitleView.setTypeface(robotoBoldCondensedItalic);
         }
  	}
  	public static void refreshList(Context context){
  		Toast toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
		View view = toast.getView();
		view.setBackgroundResource(R.color.transparent);
		toast.setView(view);
		toast.show();
  	}
  	
	public static String dateCompare(String fecha1, String fechaActual) {
		String resultado = "";
		try {
			/** Obtenemos las fechas enviadas en el formato a comparar */
			SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaDate1 = formateador.parse(fecha1);
			Date fechaDate2 = formateador.parse(fechaActual);


			if (fechaDate1.before(fechaDate2)) {
				resultado = "f1<f2";
			} else {
				if (fechaDate2.before(fechaDate1)) {
					resultado = "f1>f2";
				} else {
					resultado = "f1=f2";
				}
			}
		} catch (ParseException e) {

		}
		return resultado;
  	}
	
	public static String dateToCalendar(Date date){
		  String fixedDate = null;
		  Calendar c = Calendar.getInstance();
		  c.setTime(date);
		  
		  //int seconds = c.get(Calendar.SECOND);
          int minutes = c.get(Calendar.MINUTE);
          int hours = c.get(Calendar.HOUR);
        //  int years = c.get(Calendar.YEAR);
        //  int months = 1 + c.get(Calendar.MONTH);
        //  int days = c.get(Calendar.DAY_OF_MONTH);
          int AM_orPM = c.get(Calendar.AM_PM);

          try{
              if (hours < 12)
              {
                  String PM = "";
                  if (AM_orPM == 1)
                  {
                      PM = "PM";
                  }
                  fixedDate =hours + ":" + minutes + " " + PM;
                 
              }
              else if (hours > 12)
              {
                  String AM = "";
                  if (AM_orPM == 0)
                  {
                      AM = "AM";
                  }
                  hours = hours - 12;
                  fixedDate =hours + ":" + minutes + " " +  AM;
             
              }
          }
          catch (Exception e){
        	  
          }
		   return fixedDate; 
		}
	
	public static String getCategoryCode(String option){
		String cat = "99";
		if(option.equals("Calle") || option.equals("Road")){
			cat = "1";
		}
		if(option.equals("Trail")){
			cat = "2";
		}
		if(option.equals("Media Maratón (21.097 k)")){
			cat = "3";
		}
		if(option.equals("Maratón (42.195 k)")){
			cat = "4";
		}
		if(option.equals("Ultra Maratón")){
			cat = "5";
		}
		if(option.equals("Duatlón")){
			cat = "7";
		}
		if(option.equals("Triatlón")){
			cat = "6";
		}
		if(option.equals("Todas") || option.equals("All")){
			cat = "0";
		}
		
		
		return cat;
	}
	//get hashcode
	public static void getHashCode(Context context){
		 PackageInfo info;
	     try {
	         info = context.getPackageManager().getPackageInfo("com.turtlesoftware.thedailyrunners.main", PackageManager.GET_SIGNATURES);
	         for (Signature signature : info.signatures) {
	             MessageDigest md;
	             md = MessageDigest.getInstance("SHA");
	             md.update(signature.toByteArray());
	             String something = new String(Base64.encode(md.digest(), 0));
	             //String something = new String(Base64.encodeBytes(md.digest()));
	             Log.i(Configuration.LOG_TAG, something);
	         }
	     } catch (NameNotFoundException e1) {
	     	Log.i(Configuration.LOG_TAG, e1.toString());
	     } catch (NoSuchAlgorithmException e) {
	     	Log.i(Configuration.LOG_TAG, e.toString());
	     } catch (Exception e) {
	     	Log.i(Configuration.LOG_TAG, e.toString());
	     }
	}
	
	/**
	 * Deletes a directory tree recursively.
	 */
	public static void deleteDirectoryTree(File fileOrDirectory) {
	    if (fileOrDirectory.isDirectory()) {
	        for (File child : fileOrDirectory.listFiles()) {
	            deleteDirectoryTree(child);
	        }
	    }

	    fileOrDirectory.delete();
	}
 
}
