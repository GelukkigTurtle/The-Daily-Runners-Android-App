package com.turtlesoftware.thedailyrunners.fragments;

import android.app.ProgressDialog;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.sromku.simple.fb.entities.Feed;
import com.sromku.simple.fb.listeners.OnPublishListener;
import com.turtlesoftware.thedailyrunners.beans.Race;
import com.turtlesoftware.thedailyrunners.dao.SetRaceAssistanceDAO;
import com.turtlesoftware.thedailyrunners.main.MainActivity;
import com.turtlesoftware.thedailyrunners.main.R;
import com.turtlesoftware.thedailyrunners.main.RaceInfoMain;
import com.turtlesoftware.thedailyrunners.tools.Configuration;
import com.turtlesoftware.thedailyrunners.tools.Tools;

public class RaceActionsFragment extends DialogFragment {

	private TextView txtMessage;
	private Button btnFBShare;
	private ProgressDialog mProgressDialog;
	private CheckBox chkYes;
	private CheckBox chkNo;

	Race race;

	public static RaceActionsFragment newInstance() {
		RaceActionsFragment f = new RaceActionsFragment();
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		if (getDialog() != null) {
			getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
			// getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		}

		race = RaceInfoMain.raceInfoBean;

		View root = inflater.inflate(R.layout.race_actions_panel, container,
				false);
		txtMessage = (TextView) root.findViewById(R.id.txtMessage);
		btnFBShare = (Button) root.findViewById(R.id.btnFBShare);
		chkNo = (CheckBox) root.findViewById(R.id.chkNo);
		chkYes = (CheckBox) root.findViewById(R.id.chkYes);

		chkNo.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					going(2);

					if (chkYes.isChecked()) {
						chkYes.setChecked(false);
					}
				} else {
					going(1);
					chkYes.setChecked(true);
					Toast.makeText(getActivity(),
							getResources().getString(R.string.cal_goin),
							Toast.LENGTH_SHORT).show();
				}

			}
		});
		chkYes.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					going(1);

					if (chkNo.isChecked()) {
						chkNo.setChecked(false);
					}
				} else {
					going(2);
					chkNo.setChecked(true);
					Toast.makeText(getActivity(),
							getResources().getString(R.string.cal_not_goin),
							Toast.LENGTH_SHORT).show();

				}

			}
		});

		if (MainActivity.account != null) {
			// facebook logued
			txtMessage.setVisibility(View.INVISIBLE);
			btnFBShare.setEnabled(true);
			chkYes.setEnabled(true);
			chkNo.setEnabled(true);
			if (RaceInfoMain.raceAssitance == 1) {
				chkYes.setChecked(true);
			} else {
				chkNo.setChecked(true);
			}
		} else {
			// not logued
			chkYes.setEnabled(false);
			chkNo.setEnabled(false);
			txtMessage.setVisibility(View.VISIBLE);
			btnFBShare.setBackgroundColor(getResources().getColor(
					R.color.disabled_button_color));
			btnFBShare.setEnabled(false);

		}
		// adapter = new RaceOptionsAdapter();

		// pager.setAdapter(adapter);
		btnFBShare.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i(Configuration.LOG_TAG, "Click FB share");
				showDialog();
				String image = null;
				boolean firstImage = true;
				for (String name : RaceInfoTabFragment.url_maps.keySet()) {
					if (firstImage) {
						image = RaceInfoTabFragment.url_maps.get(name);
						firstImage = false;
					}
				}

				Feed feed = new Feed.Builder()
						.setMessage("Clone it out...")
						.setName(
								getResources().getString(R.string.fb_name)+" "
										+ race.name)
						.setCaption(
								getResources().getString(R.string.fb_caption))
						.setDescription(
								getResources().getString(R.string.fb_desc1)+" "
										+ Configuration.dateformat
												.format(race.date)+" "
										+ getResources().getString(
												R.string.fb_desc2)+" "
										+ race.location+" "
										+ getResources().getString(
												R.string.fb_desc3)+" "
										+ Configuration.hourformat
												.format(race.startTime))
						.setPicture(image)
						.setLink(getResources().getString(R.string.fb_link))
						.build();

				RaceInfoMain.mSimpleFacebook.publish(feed, true,
						new OnPublishListener() {
							@Override
							public void onComplete(String postId) {
								Log.i(Configuration.LOG_TAG,
										"Published successfully. The new post id = "
												+ postId);
								hideDialog();
								txtMessage.setVisibility(View.VISIBLE);
								txtMessage.setText(getResources().getString(
										R.string.fb_status_ok));
								super.onComplete(postId);
							}

							@Override
							public void onException(Throwable throwable) {
								Log.i(Configuration.LOG_TAG,
										"Published problema = "
												+ throwable.getMessage());
								hideDialog();
								super.onException(throwable);
							}

							@Override
							public void onFail(String reason) {
								Log.i(Configuration.LOG_TAG,
										"Published problema fail = " + reason);
								hideDialog();
								super.onFail(reason);
							}

							@Override
							public void onThinking() {
								Log.i(Configuration.LOG_TAG, "pensando ");

								showDialog();
								super.onThinking();
							}

						});
			}
		});
		Tools.applyFont(getActivity(), root, Configuration.GENERAL_FONT_NAME);


		return root;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onStart() {
		super.onStart();

		// change dialog width
		if (getDialog() != null) {

			int fullWidth = getDialog().getWindow().getAttributes().width;

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
				Display display = getActivity().getWindowManager()
						.getDefaultDisplay();
				Point size = new Point();
				display.getSize(size);
				fullWidth = size.x;
			} else {
				Display display = getActivity().getWindowManager()
						.getDefaultDisplay();
				fullWidth = display.getWidth();
			}

			final int padding = (int) TypedValue.applyDimension(
					TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
							.getDisplayMetrics());

			int w = fullWidth - padding;
			int h = getDialog().getWindow().getAttributes().height;

			getDialog().getWindow().setLayout(w, h);
		}
	}

	public void going(int type) {
		if (type == 1) { // reset on open checked dialog
			RaceInfoMain.raceAssitance = 1;
		} else {
			RaceInfoMain.raceAssitance = 0;
		}
		new SetRaceAssistanceDAO().execute(MainActivity.account.userID,
				Integer.toString(race.raceID), Integer.toString(type));

	}

	protected void showDialog() {
		if (mProgressDialog == null) {
			setProgressDialog();
		}
		mProgressDialog.show();
	}

	protected void hideDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
	}

	private void setProgressDialog() {
		mProgressDialog = new ProgressDialog(getActivity());
		mProgressDialog.setMessage(getActivity().getString(R.string.v_loading));
	}

}
