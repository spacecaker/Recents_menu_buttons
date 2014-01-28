package com.example.recentsmenu;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class KillButton extends Button{

	public KillButton(final Context context, AttributeSet attrs) {
		super(context, attrs);
		
		this.setOnClickListener(new Button.OnClickListener() {  
            public void onClick(View v)
                     {
        	    		try{         	    			
							Intent i = new Intent();
							i.setClassName("com.sec.android.app.controlpanel", "com.sec.android.app.controlpanel.activity.RamUseageActivity");
							i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
							v.getContext().startActivity(i);							
        	    		}
        	    		catch(Exception ex){ 
        		            Toast.makeText(context, "Not Installed/Working.",
        		                    Toast.LENGTH_LONG).show(); 	        	    			
        	    		}	
                    }
               });
	}	
}
