package nitp.avi.geraticsHealthCare;

import nitp.avadhesh.corona.R;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
 
public class DiseaseContainer extends FragmentActivity implements
        ActionBar.TabListener{
 
    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabs = { "Details", "Find More On", "Web Browser"};
    private int[] tabicon = { R.drawable.details, R.drawable.more, R.drawable.browser};

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_container);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
 
        viewPager.setAdapter(mAdapter);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        
        //int k=0;
        for (int tab_icon : tabicon) {
          Tab tab=  actionBar.newTab();
          //tab.setText(tabs[k]);
          tab.setIcon(tab_icon);
          actionBar.addTab(tab
                  .setTabListener(this));  
          //k++;
          
        }
        // Adding Tabs
     /*   for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }
 
        */
        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
 
            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }
 
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
 
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }
 
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }
 
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }
 
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }

	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
         case android.R.id.home:
            onBackPressed();
        }
        return true;
    }
	
    boolean doubleBackToExitPressedOnce = false;
	@Override
	public void onBackPressed() {
	    if (doubleBackToExitPressedOnce) {
	       super.onBackPressed();	     
	    }
	    this.doubleBackToExitPressedOnce = true;
	    getActionBar().setSelectedNavigationItem(0);
	    Toast.makeText(this, "press again to go back", Toast.LENGTH_SHORT).show();
	    new Handler().postDelayed(new Runnable() {
	        @Override
	        public void run() {
	            doubleBackToExitPressedOnce = false;
	        }
	    }, 2000);
	}
}