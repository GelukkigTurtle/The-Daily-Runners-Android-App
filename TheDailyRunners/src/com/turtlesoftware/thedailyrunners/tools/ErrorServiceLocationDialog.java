package com.turtlesoftware.thedailyrunners.tools;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class ErrorServiceLocationDialog extends android.support.v4.app.DialogFragment {

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
 
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
 
        builder.setMessage("Es necesario que el GPS este activado ")
        .setTitle("Alert!!!")
        .setPositiveButton("GPS Settings", new DialogInterface.OnClickListener()  {
               public void onClick(DialogInterface dialog, int id) {
            	   		startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 999);		
                        dialog.cancel();
                   }
               })
        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                   }
               });
 
        return builder.create();
    }
	
}
