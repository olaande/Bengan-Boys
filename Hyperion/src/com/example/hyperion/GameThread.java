package com.example.hyperion;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Handler;
import android.os.Looper;

/**
 * To-Write
 * 
 * @author 		Mattias Benng�rd
 * @version		0.1
 * @since		2015-10-05
 */

public class GameThread extends Thread
{
	private final Activity activity;
	private boolean isRunning;
	
	private Playfield playfield = new Playfield ();
	
	/**
	 * Main game thread, initialized from Main Game Panel.
	 * @param activity - current active activity to launch thread in
	 */
	public GameThread (Activity activity) {
       this.activity = activity;
       
       activity.runOnUiThread(this);
	}
	
	/**
	 * Enables the game thread to be run
	 */
	public void startRunning () {
		isRunning = true;
	}
	
	/**
	 * Stops the game thread from running
	 */
	public void stopRunning () {
		isRunning = false;
	}
	
	@Override
	public void run () {
	
		FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
		ft.add(android.R.id.content, playfield);
		ft.commit();
		
		Handler refresh = new Handler(Looper.getMainLooper());
		refresh.post(new Runnable() {
		    public void run()
		    {
		    	while (isRunning) {
			    	playfield.getBus().getPowerComponent().drainPower();
			    	try {
						sleep (1000);
					} catch (InterruptedException e) {
					}
		    	}
		    }
		});
	}
}
