package com.issamux;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;

/**
 * Created by issamux 2016
 */

public class MainActivity extends AppCompatActivity {

	private static final long DELAY = 300;
	private RecyclerView recyclerView;
	private LinearLayoutManager linearLayoutManager;
	private MyRecyclerAdapter adapter;
	private LayoutAnimationController animControler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
		//LinearLayout Manager
		linearLayoutManager = new LinearLayoutManager(this);
		//RecyclerView Adapter
		adapter = new MyRecyclerAdapter(this, recyclerView);
		//Custom RecyclerView Item Animation
		Animation animation = new AlphaAnimation(0, 1);
		animation.setDuration(1000);
		animControler = new LayoutAnimationController(animation);
		recyclerView.setLayoutAnimation(animControler);
		recyclerView.setLayoutManager(linearLayoutManager);
		recyclerView.setHasFixedSize(true);
		recyclerView.setAdapter(adapter);

		//attach header
		adapter.attachHeader(R.layout.header_layout);
		//attach footer
		adapter.attachFooter(R.layout.footer_layout);
	}


	/**
	 * call from UI
	 */
	public void detachHeader(View view) {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				adapter.detachHeader();
			}
		}, DELAY);

	}

	/**
	 * call from UI
	 */
	public void attachHeader(View view) {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				adapter.attachHeader(R.layout.header_layout);
			}
		}, DELAY);
	}

	/**
	 * call from UI
	 */
	public void detachFooter(View view) {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				adapter.detachFooter();
			}
		}, DELAY);

	}

	/**
	 * call from UI
	 */
	public void attachFooter(View view) {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				adapter.attachFooter(R.layout.footer_layout);
			}
		}, DELAY);
	}

}
