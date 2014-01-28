package com.example.recentsmenu;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SearchButton extends Button{

	public SearchButton(final Context context, AttributeSet attrs) {
		super(context, attrs);
		
		this.setOnClickListener(new Button.OnClickListener() {  
            public void onClick(View v)
                     {
        	    		try{         	    			
        	    			v.getContext().startActivity((new Intent("android.search.action.GLOBAL_SEARCH")).setFlags(0x10000000 | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));       	    			
        	    		}
        	    		catch(Exception ex){  
        		            Toast.makeText(context, "Not Installed/Working.",
        		                    Toast.LENGTH_LONG).show(); 	         	    			
        	    		}	
                    }
               });
	}
}
