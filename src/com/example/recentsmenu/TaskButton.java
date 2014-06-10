package com.example.recentsmenu;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TaskButton extends Button{

	public TaskButton(final Context context, AttributeSet attrs) {
		super(context, attrs);
		
		this.setOnClickListener(new Button.OnClickListener() {  
            public void onClick(View v)
                     {
        	    		try{  
        	    			v.getContext().startActivity((new Intent("com.sec.android.app.controlpanel.MAIN")).setFlags(0x10000000 | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
							try{ 
								Intent closeDialog = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
								context.sendBroadcast(closeDialog);
							}
	        	    		catch(Exception ex){ 
	        		            Toast.makeText(context, "Action CLose 1 failed.",
	        		                    Toast.LENGTH_LONG).show(); 	        	    			
	        	    		}
							try{ 
								Intent closeDialog = new Intent();
								closeDialog.setAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
								context.sendBroadcast(closeDialog);
							}
	        	    		catch(Exception ex){ 
	        		            Toast.makeText(context, "Action CLose 2 failed.",
	        		                    Toast.LENGTH_LONG).show(); 	        	    			
	        	    		}        	    			
        	    		}        	    
        	    		catch(Exception ex){ 
        		            Toast.makeText(context, "Not Installed/Working.",
        		                    Toast.LENGTH_LONG).show(); 	         	    			
        	    		}	
                    }
               });
		
        ContentResolver resolver = context.getContentResolver();
        int mHideRecentTask = Settings.System.getInt(resolver, "hide_samsung_recents", 0);
        if (mHideRecentTask == 1)
            this.setVisibility(View.GONE);
        else
            this.setVisibility(View.VISIBLE);			
	}	
}
