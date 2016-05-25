package nitp.avi.geraticsHealthCare;

import nitp.avadhesh.corona.R;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class TabMyWebViewFragment extends android.support.v4.app.Fragment {
WebView webView;
View rootView;
ProgressBar progressBar;
String site;

private Handler handler = new Handler(){
    @Override
    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:{
                webViewGoBack();
            }break;
            
        }
    }

};

public void webViewGoBack() {
	// TODO Auto-generated method stub
	webView.goBack();
}


@Override
public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle shavedInstance) {
    rootView=inflater.inflate(R.layout.web_fragment, container,false);
	String name=ShareData.data().Diseasename;
	webView = (WebView)rootView.findViewById(R.id.webView1);
	progressBar=(ProgressBar) rootView.findViewById(R.id.progressBar);
	
	webView.setWebViewClient(new MyBrowser());
	webView.getSettings().setJavaScriptEnabled(true);
	webView.getSettings().setLoadsImagesAutomatically(true);
	webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
	String k=getMyUrl().toString();
	webView.loadUrl(k);
    progressBar.setProgress(0);
    
	
   webView.setOnKeyListener(new OnKeyListener() {	
		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			// TODO Auto-generated method stub
			 if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
                 handler.sendEmptyMessage(1);
                 return true;}
			return false;  }
	});

  
   
	return rootView;
}

private class MyBrowser extends WebViewClient {
	   @Override
	   public boolean shouldOverrideUrlLoading(WebView view, String url) {
	      view.loadUrl(url);
	      return true;
	   }
	   
	   @Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		// TODO Auto-generated method stub
		super.onPageStarted(view, url, favicon);
	
	   }
	   @Override
	public void onPageFinished(WebView view, String url) {
		// TODO Auto-generated method stub
		  progressBar.setVisibility(View.GONE);
		super.onPageFinished(view, url);
	}
	   
  }
  
public String  getMyUrl() {
	String name=ShareData.data().Diseasename;
	site=ShareData.data().site;
	String url="http://www.webmd.com/search/search_results/default.aspx?query="+name.trim();
	try{
	if (site.trim().equals("Wikipedia")) {
		url="https://en.wikipedia.org/w/index.php?search="+name.trim();
	} 
	else if (site.trim().equals("WebMD")) {
		url="http://www.webmd.com/search/search_results/default.aspx?query="+name.trim();
	} 
   else if (site.trim().equals("Mayo Clinic")) {
		url="http://www.mayo.edu/research/search/search-results?q="+name.trim();
	} 
   else if (site.trim().equals("Youtube")) {
	   url="https://www.youtube.com/results?search_query="+name.trim();
   } 
   else if (site.trim().equals("Medilineplus")) {
	   url="http://www.medicalnewstoday.com/search?q="+name.trim();
   } 
   else if (site.trim().equals("Google")) {
	   url="https://www.google.co.in/#q="+name.trim();
	}
   else{
	   url="file:///android_asset/text.html";
   }
	}catch(Exception e){
		 
		 url="https://en.wikipedia.org/w/index.php?search="+name.trim();
	}
	return url;
}



}
