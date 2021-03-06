package com.example.testthanks;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class MainActivity extends Activity implements OnClickListener {
    private NavigationFragment navigation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		ActionBar actionBar = this.getActionBar();
		actionBar.setCustomView(R.layout.actionbar_layout);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setDisplayShowCustomEnabled(true);

		this.findViewById(R.id.actionbar_logo).setOnClickListener(this);
		this.findViewById(R.id.actionbar_menu_tip).setOnClickListener(this);

		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		boolean isMenuShow = false;
		if (this.navigation!=null)
		{
			isMenuShow = this.navigation.isMenuShow();
		}
		
		if (isMenuShow) 
		{
			this.navigation.toggleMenu(this.findViewById(R.id.actionbar_left));
		}
		else
		{			
			navigation = new NavigationFragment();
	        FragmentManager fragManager = getFragmentManager();
	        FragmentTransaction tran = fragManager.beginTransaction();
	        tran.replace(R.id.thanks_nav, navigation);
	        tran.addToBackStack(null);
	        tran.commit();
			this.navigation.toggleMenu(this.findViewById(R.id.actionbar_left));
		}
		
		return;
	}

}
