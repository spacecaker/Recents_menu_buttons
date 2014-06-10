package com.example.recentsmenu;

import java.io.RandomAccessFile;
import java.util.List;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class KillButton extends Button{

    ActivityManager am;
    public Context cContext;
    double totalMemory;
    double availableMemory;
    public Handler mHandler;
    
	public KillButton(final Context context, AttributeSet attrs) {
		super(context, attrs);
        cContext = context;
        am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        mHandler = new Handler();

        totalMemory = 0;
        availableMemory = 0; 		
		this.setOnClickListener(new Button.OnClickListener() {  
            public void onClick(View v)
                     {
            	Intent startMain = new Intent(Intent.ACTION_MAIN);
        		startMain.addCategory(Intent.CATEGORY_HOME);
        		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        		cContext.startActivity(startMain);
        		ActivityManager am = (ActivityManager) cContext.getSystemService(Context.ACTIVITY_SERVICE);
        		List<ActivityManager.RunningAppProcessInfo> runningapptasks = am.getRunningAppProcesses();
        		List<ActivityManager.RecentTaskInfo> recent_tasks = am.getRecentTasks(Integer.MAX_VALUE, ActivityManager.RECENT_WITH_EXCLUDED);
        		List<ActivityManager.RunningTaskInfo> runningtaskss =am.getRunningTasks(Integer.MAX_VALUE);
        		for (ActivityManager.RunningTaskInfo runningtasks : runningtaskss) {
        			String packageName = runningtasks.baseActivity.getPackageName();
        			am.killBackgroundProcesses(packageName);
        			am.restartPackage(packageName);
        		}
        		
        		for (ActivityManager.RunningAppProcessInfo runningapptask : runningapptasks) {
        			am.restartPackage(runningapptask.processName);
        		}	
        		am.restartPackage("com.android.music");
        		for (ActivityManager.RecentTaskInfo recent_task : recent_tasks) {
        			String LocalApp = recent_task.baseIntent + "";
        			int indexPackageNameBegin = LocalApp.indexOf("cmp=") + 4;
        			int indexPackageNameEnd = LocalApp.indexOf("/", indexPackageNameBegin);
        			String PackageName = LocalApp.substring(indexPackageNameBegin, indexPackageNameEnd);
        			am.killBackgroundProcesses(PackageName);
        			am.restartPackage(PackageName);
        		}
        		
        		MemoryInfo();    
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
               });
		      	    			
		
        ContentResolver resolver = context.getContentResolver();
        int mHideRecentSearch = Settings.System.getInt(resolver, "hide_samsung_recents", 0);
        if (mHideRecentSearch == 1)
            this.setVisibility(View.GONE);
        else
            this.setVisibility(View.VISIBLE);  		
	}	
	
    private void MemoryInfo() {
        if(totalMemory == 0) {
            try {
                RandomAccessFile reader = new RandomAccessFile("/proc/meminfo", "r");
                String load = reader.readLine();
                String[] memInfo = load.split(" ");
                totalMemory = Double.parseDouble(memInfo[9])/1024;

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        availableMemory = mi.availMem / 1048576L;
        int usedmemory = (int) (totalMemory - availableMemory);
        int totmem = (int) totalMemory;
        Toast.makeText(cContext, "RAM Cleared! " +usedmemory +" MB/" +totmem +" MB", Toast.LENGTH_SHORT).show();
    }	
}
