package nitp.avi.geraticsHealthCare;

import java.util.ArrayList;
import java.util.List;

import nitp.avadhesh.corona.R;
import android.os.Bundle;
import android.os.Handler;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Home extends Activity {
	
	String[] menutitles;
	TypedArray menuIcons;
	// nav drawer title
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private List<RowItem> rowItems;
	private CustomAdapter adapter;
	
	FragmentTransaction fragMentTra;
    RelativeLayout rl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		mTitle=mDrawerTitle=getTitle();
		menutitles = getResources().getStringArray(R.array.title);
		menuIcons = getResources().obtainTypedArray(R.array.icons);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.slider_list);
		rowItems = new ArrayList<RowItem>();
		
		for (int i = 0; i < menutitles.length; i++) {
			RowItem items = new RowItem(menutitles[i], menuIcons.getResourceId(
					i, -1));
			rowItems.add(items);
		}
		menuIcons.recycle();
		
		adapter = new CustomAdapter(getApplicationContext(), rowItems);

		mDrawerList.setAdapter(adapter);
		mDrawerList.setOnItemClickListener(new SlideitemListener());
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
        
        mDrawerToggle=new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.app_name,R.string.app_name)
        {public void onDrawerClosed(View view) {
        	getActionBar().setTitle(mTitle);
			// calling onPrepareOptionsMenu() to show action bar icons
			invalidateOptionsMenu();
		}
        public void onDrawerOpened(View drawerView) {
			getActionBar().setTitle(mDrawerTitle);
			// calling onPrepareOptionsMenu() to hide action bar icons
			invalidateOptionsMenu();
		}
	};
	mDrawerLayout.setDrawerListener(mDrawerToggle);
	if (savedInstanceState == null) {
		// on first time display view for first nav item
		//updateDisplay(0);
     
		
	}
	Fragment fragment=new HomeFrag();
	if(fragment!=null){
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.frame_container, fragment).commit();}
	
	
	}

	class SlideitemListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			updateDisplay(position);
		}

	}
	
	private void updateDisplay(int position) {
		Fragment fragment =null;
		String name = rowItems.get(position).getTitle();
        if(name.equals("Home"))
        {
        	fragment=new HomeFrag();
        
        }
        else  if(name.equals("Diagnosis"))
        {
        	fragment=new Diagnosis();
        
        }
        
        else  if(name.equals("Diseases"))
		{
        	fragment=new DiseaseFrag();	
		}
        
        
        else  if(name.equals("Expert Help"))
        {
        	fragment=new HelpFrag();
        }
		
        else if(name.equals("About Us"))
		{
			fragment=new Developers();	
		}
		
		else if(name.equals("Emergency Call"))
		{
			fragment=new EmergencyFrag();
		}
		else
			fragment=new HomeFrag();
        
		if(fragment!=null){
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.frame_container, fragment).commit();

		setTitle(menutitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
		}
		


	}
	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		Fragment fragment =null;
		switch (item.getItemId()) {
	    case R.id.aboutus:
	        fragment=new Developers();
	        setTitle("About Us");
	    	break;
	    case R.id.help:
	    	fragment=new HelpFrag();
	    	setTitle("Help");
	    	break;
		default:
			return super.onOptionsItemSelected(item);
		}
		if(fragment!=null){
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();
			mDrawerLayout.closeDrawer(mDrawerList);
			}
		return true;
	}
	
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	

	boolean doubleBackToExitPressedOnce = false;
	@Override
	public void onBackPressed() {
	    if (doubleBackToExitPressedOnce) {
	       // super.onBackPressed();
	    	 finish();
	        return;
	    }
	    this.doubleBackToExitPressedOnce = true;
	    Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
	    new Handler().postDelayed(new Runnable() {
	        @Override
	        public void run() {
	            doubleBackToExitPressedOnce = false;
	        }
	    }, 2000);
	}
}
