package nitp.avi.geraticsHealthCare;

import java.util.ArrayList;
import java.util.List;

import nitp.avadhesh.corona.R;
import android.net.Uri;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TabFindMoreON extends android.support.v4.app.Fragment {
	String[] menutitles;
	TypedArray menuIcons;
	private List<RowItem> rowItems;
	private CustomAdapter adapter;
	View rootview;
	TextView name;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootview=inflater.inflate(R.layout.about_tab, container, false);
		name=(TextView) rootview.findViewById(R.id.textView1);
		name.setText(ShareData.data().Diseasename.toString());
		
		
		//webview loading....
        WebView mWebView = (WebView)rootview.findViewById(R.id.webView1);
        mWebView.loadUrl("file:///android_asset/text.html");
        mWebView.getSettings().setJavaScriptEnabled(true);
        
        
		///list view formation .....
		ListView mDrawerList = (ListView) rootview.findViewById(R.id.listView1);
		menutitles = getResources().getStringArray(R.array.sites);
		menuIcons = getResources().obtainTypedArray(R.array.sitesicon);
rowItems = new ArrayList<RowItem>();
		
		for (int i = 0; i < menutitles.length; i++) {
			RowItem items = new RowItem(menutitles[i], menuIcons.getResourceId(
					i, -1));
			rowItems.add(items);
		}
		menuIcons.recycle();
		
		adapter = new CustomAdapter(getActivity(), rowItems);
		mDrawerList.setAdapter(adapter);
		mDrawerList.setOnItemClickListener(new SlideitemListener());
		
		
		return rootview;
		//return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	class SlideitemListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			updateDisplay(position);
		}

	}
	
	private void updateDisplay(int position) {
		String site = rowItems.get(position).getTitle().toString();
		ShareData.data().site=site;
		 // getActivity().getActionBar().setSelectedNavigationItem(2);
		  ViewPager viewPager = (ViewPager) getActivity().findViewById(
                  R.id.pager);
		  TabsPagerAdapter mAdapter = new TabsPagerAdapter(getActivity().getSupportFragmentManager());
		  viewPager.setAdapter(mAdapter);
		  viewPager.setTag(site);
          viewPager.setCurrentItem(2);
	 
		 
	}
	
	
	
}
