package nitp.avi.geraticsHealthCare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nitp.avadhesh.corona.R;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class HomeFrag extends Fragment {
	
	TextView textView;
@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.homefrag, container, false);
		 textView=(TextView) rootView.findViewById(R.id.textView1);
		
		    WebView mWebView = (WebView) rootView.findViewById(R.id.webView1);
	        mWebView.loadUrl("file:///android_asset/text.html");
	        mWebView.getSettings().setJavaScriptEnabled(true);
	        
	        rootView.findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				Fragment fragment=null;	
				fragment=new Diagnosis();
				
				if(fragment!=null){
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager.beginTransaction()
							.replace(R.id.frame_container, fragment).commit();
					}
					
				}
			});
           rootView.findViewById(R.id.button2).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Fragment fragment=null;
					fragment=new DiseaseFrag();
					
					if(fragment!=null){
						FragmentManager fragmentManager = getFragmentManager();
						fragmentManager.beginTransaction()
								.replace(R.id.frame_container, fragment).commit();
						}
					
				}
			});
	        
	    

	    return rootView;
		//return super.onCreateView(inflater, container, savedInstanceState);
	   
	}


}
