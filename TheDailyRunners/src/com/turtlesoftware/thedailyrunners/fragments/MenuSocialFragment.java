package com.turtlesoftware.thedailyrunners.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.SimpleFacebookConfiguration;
import com.sromku.simple.fb.entities.Profile;
import com.sromku.simple.fb.entities.Profile.Properties;
import com.sromku.simple.fb.listeners.OnLoginListener;
import com.sromku.simple.fb.listeners.OnLogoutListener;
import com.sromku.simple.fb.listeners.OnProfileListener;
import com.sromku.simple.fb.utils.Attributes;
import com.sromku.simple.fb.utils.PictureAttributes;
import com.sromku.simple.fb.utils.PictureAttributes.PictureType;
import com.turtlesoftware.thedailyrunners.beans.Account;
import com.turtlesoftware.thedailyrunners.dao.UserDAO;
import com.turtlesoftware.thedailyrunners.main.MainActivity;
import com.turtlesoftware.thedailyrunners.main.MenuBaseActivity;
import com.turtlesoftware.thedailyrunners.main.R;
import com.turtlesoftware.thedailyrunners.tools.Configuration;
import com.turtlesoftware.thedailyrunners.tools.Tools;

public class MenuSocialFragment extends Fragment {
	
	Permission[] permissions = new Permission[] {
		    Permission.USER_PHOTOS,
		    Permission.EMAIL,
		    Permission.PUBLISH_ACTION,
		    Permission.USER_WORK_HISTORY,
		    Permission.USER_STATUS
		};
	
	
	Profile prof;
    private Button loginButton;
    private ImageView picture;
    private TextView text;
   

	
	SimpleFacebookConfiguration configuration = new SimpleFacebookConfiguration.Builder()
    .setAppId(Configuration.FACEBOOK_ID_APP)
    .setNamespace(Configuration.FACEBOOK_NAMESPACE)
    .setPermissions(permissions)
    .build();
	
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	
		super.onViewCreated(view, savedInstanceState);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		// inflamos la vista que queremos mostrar en pantalla
		View rootView = inflater.inflate(R.layout.menu_social, container, false);
		// inflamos sus componentes
		
		loginButton = (Button) rootView.findViewById(R.id.btnFBLogin);
		
		loginButton.setBackgroundResource(R.drawable.com_facebook_button_blue);
		loginButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.com_facebook_inverse_icon, 0, 0, 0);
		loginButton.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen.com_facebook_loginview_compound_drawable_padding));
		loginButton.setPadding(getResources().getDimensionPixelSize(R.dimen.com_facebook_loginview_padding_left),
                getResources().getDimensionPixelSize(R.dimen.com_facebook_loginview_padding_top),
                getResources().getDimensionPixelSize(R.dimen.com_facebook_loginview_padding_right),
                getResources().getDimensionPixelSize(R.dimen.com_facebook_loginview_padding_bottom));
		loginButton.setVisibility(View.VISIBLE);
		picture  = (ImageView) rootView.findViewById(R.id.profilePicture);
		text = (TextView) rootView.findViewById(R.id.txtMsn);
		SimpleFacebook.setConfiguration(configuration);
		
		MenuBaseActivity.actionBar.setTitle(getString(R.string.title_account));

		Tools.applyFont(getActivity(), rootView, Configuration.GENERAL_FONT_NAME);


	
		return rootView;
	}


	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		MenuBaseActivity.mSimpleFacebook.onActivityResult(getActivity(), requestCode, resultCode, data); 
		super.onActivityResult(requestCode, resultCode, data);
	}


	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}


	@Override
	public void onResume() {
		super.onResume();
		MenuBaseActivity.mSimpleFacebook = SimpleFacebook.getInstance(getActivity());
		  PictureAttributes pictureAttributes = Attributes.createPictureAttributes();
	        pictureAttributes.setHeight(250);
	        pictureAttributes.setWidth(250);
	        pictureAttributes.setType(PictureType.SQUARE);
	        
	        Profile.Properties properties = new Profile.Properties.Builder()
	        .add(Properties.ID)
	        .add(Properties.FIRST_NAME)
	        .add(Properties.LAST_NAME)
	        .add(Properties.EMAIL)
	        .add(Properties.GENDER)
	        .add(Properties.PICTURE, pictureAttributes)
	        .build();   
	        
	        MenuBaseActivity.mSimpleFacebook.getProfile(properties, onProfileListener);
		
	}
	
	OnLoginListener onLoginListener = new OnLoginListener() {
	    @Override
	    public void onLogin() {
	        // change the state of the button or do whatever you want
	        Log.i(Configuration.LOG_TAG, "Logged in");
	        
	        PictureAttributes pictureAttributes = Attributes.createPictureAttributes();
	        pictureAttributes.setHeight(250);
	        pictureAttributes.setWidth(250);
	        pictureAttributes.setType(PictureType.SQUARE);
	        
	        Profile.Properties properties = new Profile.Properties.Builder()
	        .add(Properties.ID)
	        .add(Properties.FIRST_NAME)
	        .add(Properties.LAST_NAME)
	        .add(Properties.EMAIL)
	        .add(Properties.GENDER)
	        .add(Properties.PICTURE, pictureAttributes)
	        .build();   
	        
	        MenuBaseActivity.mSimpleFacebook.getProfile(properties, onProfileListener);
	        
	    }

	    @Override
	    public void onNotAcceptingPermissions(Permission.Type type) {
	        // user didn't accept READ or WRITE permission
	        Log.w(Configuration.LOG_TAG, String.format("You didn't accept %s permissions", type.name()));
	    }

		@Override
		public void onThinking() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onException(Throwable throwable) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onFail(String reason) {
			Log.i(Configuration.LOG_TAG, "Logged in FAIL!");
		}

	    /* 
	     * You can override other methods here: 
	     * onThinking(), onFail(String reason), onException(Throwable throwable)
	     */ 
	};
	
	OnProfileListener onProfileListener = new OnProfileListener() {         
	    @Override
	    public void onComplete(Profile profile) {
	    	loginButton.setText(getResources().getString(R.string.fb_logout_msj));
			loginButton.setOnClickListener(evt_logout);
			
			if(MainActivity.account == null){
				MainActivity.account = new Account();
				MainActivity.account.userID = profile.getId();
				MainActivity.account.userName = profile.getName()+" "+profile.getLastName();
				MainActivity.account.email = profile.getEmail();

			}
			
	        Log.i(Configuration.LOG_TAG, "My profile id = " + profile.getId());
	        Log.i(Configuration.LOG_TAG, "My profile gender = " + profile.getGender());
	        Log.i(Configuration.LOG_TAG, "My profile name = " + profile.getFirstName());
	        Log.i(Configuration.LOG_TAG, "My profile email = " + profile.getEmail());
	        //insert user in db
	        new UserDAO().execute(profile.getId(),profile.getFirstName(),profile.getLastName(),profile.getEmail());
	        
			Picasso.with(getActivity()).load(profile.getPicture()).placeholder(R.drawable.filipi_mariposa).into(picture);
			if(profile.getGender().equals("male")){
				text.setText(getResources().getString(R.string.fb_welcome_male)+" "+profile.getFirstName() + " "+  profile.getLastName()+" !");

			}else if(profile.getGender().equals("female")){
				text.setText(getResources().getString(R.string.fb_welcome_female)+" "+profile.getFirstName() + " "+  profile.getLastName()+" !");

			}else{
				text.setText(getResources().getString(R.string.fb_welcome_male)+" "+profile.getFirstName() + " "+  profile.getLastName()+" !");
			}
	    }

		@Override
		public void onFail(String reason) {
			//we are not logued
			loginButton.setText(getResources().getString(R.string.fb_login_msj));
			loginButton.setOnClickListener(evt_login);
			
	        Log.i(Configuration.LOG_TAG, "Fail = " + reason);
			super.onFail(reason);
		}

		@Override
		public void onThinking() {
			super.onThinking();
	    	loginButton.setText(getResources().getString(R.string.v_loading));

		}
	    
	    

	    /* 
	     * You can override other methods here: 
	     * onThinking(), onFail(String reason), onException(Throwable throwable)
	     */     
	};
	
	// logout listener
	OnLogoutListener onLogoutListener = new OnLogoutListener() {
	    @Override
	    public void onLogout() {
	        Log.i(Configuration.LOG_TAG, "You are logged out");
	        MainActivity.account = null;
	        picture.setImageResource(R.drawable.filipi_squat);
	        text.setText("");
	        loginButton.setText("Iniciar Sesion con Facebook");
			loginButton.setOnClickListener(evt_login);
	        
	    }

		@Override
		public void onThinking() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onException(Throwable throwable) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onFail(String reason) {
			// TODO Auto-generated method stub
			
		}

	    /* 
	     * You can override other methods here: 
	     * onThinking(), onFail(String reason), onException(Throwable throwable)
	     */ 
	};
	OnClickListener evt_login = new  OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			MenuBaseActivity.mSimpleFacebook.login(onLoginListener);
		}
	};
		OnClickListener evt_logout = new  OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			MenuBaseActivity.mSimpleFacebook.logout(onLogoutListener);
		}
	};
	
}
