package com.mc2e.sample.drawer;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.mc2e.sample.drawer.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

public class MainActivity extends SherlockFragmentActivity implements
		OnItemClickListener {

	private FrameLayout mContentFrame;
	private DrawerLayout mDrawerLayout;
	private MyDrawerToggle mDrawerToggle;
	private ListView mMenu;
	private ArrayAdapter<String> mMenuAdapter;

	private String[] mMenuString = { "Red", "Blue", "Green", "Yellow", };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerToggle = new MyDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close);

		mMenu = (ListView) findViewById(R.id.drawer_menu);
		mMenuAdapter = new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mMenuString);

		mMenu.setOnItemClickListener(this);
		mMenu.setAdapter(mMenuAdapter);

		mContentFrame = (FrameLayout) findViewById(R.id.content_frame);
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		selectItem(0);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {

		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getSupportMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == android.R.id.home) {

			if (mDrawerLayout.isDrawerOpen(mMenu)) {
				mDrawerLayout.closeDrawer(mMenu);
			} else {
				mDrawerLayout.openDrawer(mMenu);
			}
		}

		return super.onOptionsItemSelected(item);
	}

	class MyDrawerToggle extends ActionBarDrawerToggle {

		public MyDrawerToggle(Activity activity, DrawerLayout drawerLayout,
				int drawerImageRes, int openDrawerContentDescRes,
				int closeDrawerContentDescRes) {

			super(activity, drawerLayout, drawerImageRes,
					openDrawerContentDescRes, closeDrawerContentDescRes);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onDrawerClosed(View drawerView) {

			// TODO Auto-generated method stub
			super.onDrawerClosed(drawerView);

			invalidateOptionsMenu();
		}

		@Override
		public void onDrawerOpened(View drawerView) {

			// TODO Auto-generated method stub
			super.onDrawerOpened(drawerView);

			invalidateOptionsMenu();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

		selectItem(position);
		mDrawerLayout.closeDrawer(mMenu);
	}

	private void selectItem(int position) {
		mMenu.setItemChecked(position, true);

		int color = Color.RED;

		switch (position) {
		case 0:
			color = Color.RED;
			break;
		case 1:
			color = Color.BLUE;
			break;
		case 2:
			color = Color.GREEN;
			break;
		case 3:
			color = Color.YELLOW;
			break;
		}

		mContentFrame.setBackgroundColor(color);
	}
}
