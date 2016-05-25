package nitp.avi.geraticsHealthCare;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.StringTokenizer;

import nitp.avadhesh.corona.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TabDiseaseDetails extends Fragment {
	View rootview;
	TextView t1,t2,t3,t4,t5;
	static String name;
	String value,source;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootview=inflater.inflate(R.layout.details_tab, container, false);
		
		 t1=(TextView)rootview. findViewById(R.id.textView1);
	        t2=(TextView)rootview. findViewById(R.id.textView2);
	        t3=(TextView)rootview. findViewById(R.id.textView3);
	        t4=(TextView)rootview.findViewById(R.id.textView4);
	        t5=(TextView) rootview.findViewById(R.id.textView5);
	        
	      
	        
	        //webview loading....
	        WebView mWebView = (WebView)rootview.findViewById(R.id.webView1);
	        mWebView.loadUrl("file:///android_asset/text.html");
	        mWebView.getSettings().setJavaScriptEnabled(true);
	        
	        // setting value...	         
	         value =ShareData.data().Diseasename;
	         source =ShareData.data().source;
	        if (source.equals("web")) {
				setDataFromWeb();
			}
	        else if(source.equals("xml")){
	        	setDataFromXML();
	        }
	        else{
	        	setDataFromXML();
	        }      
	       	       
				
		return rootview;
		//return super.onCreateView(inflater, container, savedInstanceState);
	}
	private class MyBrowser extends WebViewClient {
		   @Override
		   public boolean shouldOverrideUrlLoading(WebView view, String url) {
		      view.loadUrl(url);
		      return true;
		   }
		}

	//formatting string .....
	public String tokensplitSymptomprint(String value,String delims){
		StringTokenizer st = new StringTokenizer(value);  
		  String[] v = value.split(delims);
		  String  squery="";
		  for(int i=0;i<v.length;i++){
			  int count=i+1;
             squery += ""+count+") "+v[i].trim()+"\n";}		 	
		return squery;}
	
	public String tokensplitDiagnosisprint(String value){
		StringTokenizer st = new StringTokenizer(value);  
		  String[] v = value.split("\\.\\s+[0-9]+\\.|\\s*[0-9]+\\.");
		  String  squery="";
		  for(int i=1;i<v.length;i++){
             squery += ""+i+") "+v[i].trim()+"."+"\n";}		 	
		return squery;}
	
	
	//setting data details from webservice.
	public void setDataFromWeb() {
		t1.setText(ShareData.data().Diseasename.toString());		  
		t3.setText(tokensplitSymptomprint(ShareData.data().webSelectedDiseaseSymptom.toString().trim(), ","));
		t5.setText(tokensplitDiagnosisprint(ShareData.data().webSelectedDiseaseDiagnosis.toString().trim()));
	}
	
	//setting data from xmlfile....implementing parser
	public void  setDataFromXML() {
		 List<DeseaseBean> employees = null;
	        try {
	            XmlHandler parser = new XmlHandler();
	            InputStream is=getActivity().getAssets().open("diseasedetail.xml");
	            
	            employees = parser.parse(is); 
	           ArrayAdapter<DeseaseBean> adapter =new ArrayAdapter<DeseaseBean>(getActivity(),android.R.layout.simple_list_item_1, employees);
	           // listView.setAdapter(adapter);
	            
	            for(int i=0 ; i<adapter.getCount() ; i++){
	            	DeseaseBean obj = (DeseaseBean)adapter.getItem(i);
	            	String s=obj.getName().toString().trim();
	            	Boolean b=s.equalsIgnoreCase(value.trim());
	            	if(b){
	            	t1.setText(obj.getName().toString());
	            	String symp=tokensplitSymptomprint(obj.getSymptoms().toString().trim(),",");
	            	t3.setText(symp.toString());
	            	//String treat=tokensplitprint(obj.getdiagnosis().toString().trim(),"\\.");
	            	
	            	t5.setText(tokensplitDiagnosisprint(obj.getdiagnosis().toString().trim()));
	            	break;
	            	}
	            	else
	            		{t1.setText("Data not found");
	            	   	}
	            }
	        } catch (IOException e) {e.printStackTrace();}
			
	}
}

