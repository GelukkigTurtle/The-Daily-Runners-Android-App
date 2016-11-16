package com.turtlesoftware.thedailyrunners.tools;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class LoadingTask extends AsyncTask<String, Void, Boolean> {

	private ProgressDialog dialog;
    List<Message> titles;
    
   
    public LoadingTask(Activity activity) {
        context = activity;
        dialog = new ProgressDialog(context);
    }

    /** progress dialog to show user that the backup is processing. */

    /** application context. */
    private Context context;

    protected void onPreExecute() {
        this.dialog.setMessage("Cargando ...");
        this.dialog.show();
    }
    
    @Override
    protected Boolean doInBackground(final String... args) {
        try{    
           
            return true;
         } catch (Exception e){
            Log.e("tag", "error", e);
            return false;
         }
      }

    @Override
    protected void onPostExecute(final Boolean success) {
          
            if (dialog.isShowing()) {
            dialog.dismiss();
        }

        if (success) {
            Toast.makeText(context, "OK", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
        }
    }

   


}
