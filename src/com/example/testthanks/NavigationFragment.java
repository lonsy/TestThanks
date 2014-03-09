package com.example.testthanks;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

public class NavigationFragment extends Fragment implements AnimationListener {

	private Activity myActivity;
	
	private boolean isMenuShow;

	public boolean isMenuShow() {
		return isMenuShow;
	}

	public void setMenuShow(boolean isMenuShow) {
		this.isMenuShow = isMenuShow;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.thanks_nav, container, false);

		this.myActivity = this.getActivity();

		Button b = (Button) v.findViewById(R.id.nav_shadow);
		b.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (myActivity!=null)
				{
					toggleMenu(myActivity.findViewById(R.id.actionbar_left));
				}
			}
		});

		return v;
	}

	@Override
	public void onResume() {
		super.onResume();

		View v = this.getActivity().findViewById(R.id.nav_menu);

		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)v.getLayoutParams();
		//lp.setMargins(0 - v.getWidth(), 0 - v.getWidth(), 0 - v.getWidth(), 0 - v.getWidth());
		//v.setLayoutParams(lp);
		//v.setVisibility(View.VISIBLE);
		Animation animation = new TranslateAnimation(0 - lp.width, 0, 0, 0);
		animation.setFillAfter(true);
		animation.setDuration(500);
		animation.setAnimationListener(this);
		v.startAnimation(animation);
	}

	public void toggleMenu(View v) {
		
		if (v!=null)
		{
			if (this.isMenuShow) 
			{
	
				Animation animation = new TranslateAnimation(
						-13, 0, v.getTop(), v.getTop());
				animation.setFillAfter(true);
				animation.setDuration(500);
				v.startAnimation(animation);
			}
			else
			{
				Animation animation = new TranslateAnimation(
						0, -13, v.getTop(), v.getTop());
				animation.setFillAfter(true);
				animation.setDuration(500);
				v.startAnimation(animation);			 
			}
		}
		
		if (this.isMenuShow)
		{
			View v1 = this.getActivity().findViewById(R.id.nav_menu);
			LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)v1.getLayoutParams();
			Animation animation = new TranslateAnimation(0, 0 - lp.width, 0, 0);
			animation.setFillAfter(true);
			animation.setDuration(500); 
			animation.setAnimationListener(this);
			v1.startAnimation(animation);
		}
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		if (this.isMenuShow)
		{
			FragmentTransaction ft = this.getActivity().getFragmentManager()
					.beginTransaction();
			ft.remove(this).commit();
		}
		else
		{
			//this.getActivity().findViewById(R.id.nav_shadow).setVisibility(View.VISIBLE);	
		}
		this.isMenuShow = this.isMenuShow ? false : true;
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}
}
