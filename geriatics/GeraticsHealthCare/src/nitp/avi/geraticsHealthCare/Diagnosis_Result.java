package nitp.avi.geraticsHealthCare;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import nitp.avadhesh.corona.R;
import nitp.avi.geraticsHealthCare.EnterSymptoms.ExecuteTask;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
 
public class Diagnosis_Result extends Activity   {
 
	 ListView resultlist;
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis_results);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
           String[] diseaseArray=ShareData.data().webResultDisease;
    		 ArrayAdapter adapter = new ArrayAdapter<String>(this,
    		          android.R.layout.simple_list_item_1, android.R.id.text1, diseaseArray);
    	      
    	     resultlist = (ListView) findViewById(R.id.listView1);
    	      resultlist.setAdapter(adapter);
    	      getListViewSize(resultlist);
    	      resultlist.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
				String item=resultlist.getItemAtPosition(position).toString();
				StringTokenizer st = new StringTokenizer(item);
				String itemdise=st.nextToken("*");
				ShareData.data().Diseasename=itemdise.trim();
				ShareData.data().source="web";
				  new ExecuteTask().execute(getFormattedName(itemdise.trim(), " "));
				
				}
			});
    	      
    	    //webview loading....
  	        WebView mWebView = (WebView)findViewById(R.id.webView1);
  	        mWebView.loadUrl("file:///android_asset/text.html");
  	        mWebView.getSettings().setJavaScriptEnabled(true);
        
    }
    
   
    
    @Override
   	public boolean onOptionsItemSelected(MenuItem item) {
   		// TODO Auto-generated method stub
   	super.onBackPressed();
   	return true;
   	}
    
 // webservice rest api code
    class ExecuteTask extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {			
			String res=PostData(params);			
			return res;
		}
		
		@Override
		protected void onPostExecute(String result) {
				
		//Toast.makeText(getApplicationContext(), result, 3000).show();
		 StringTokenizer st = new StringTokenizer(result.trim());  
		  String[] d_details = result.split("AviDiagnosis");
		  ShareData.data().webSelectedDiseaseSymptom=d_details[0];
		  ShareData.data().webSelectedDiseaseDiagnosis=d_details[1];
		  Intent intent=new Intent(Diagnosis_Result.this,DiseaseContainer.class);
			startActivity(intent);
		  
		}
    	
    }

public String PostData(String[] valuse) {
	String s="";
	try	{
	HttpClient httpClient=new DefaultHttpClient();
	HttpPost httpPost=new HttpPost(ShareData.data().hostAddress+"/GeriatricsWebApp/AndiDetails");
	
	List<NameValuePair> list=new ArrayList<NameValuePair>();
	list.add(new BasicNameValuePair("name", valuse[0]));

	httpPost.setEntity(new UrlEncodedFormEntity(list));
    HttpResponse httpResponse=	httpClient.execute(httpPost);

    HttpEntity httpEntity=httpResponse.getEntity();
    s= readResponse(httpResponse);

	}
	catch(Exception exception) 	{}
	return s;

	
}
public String readResponse(HttpResponse res) {
	InputStream is=null; 
	String return_text="";
	try {
		is=res.getEntity().getContent();
		BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is));
		String line="";
		StringBuffer sb=new StringBuffer();
		while ((line=bufferedReader.readLine())!=null)
		{
		sb.append(line);
		}
		return_text=sb.toString();
	} catch (Exception e)
	{
		
	}
	return return_text;
	
}

public String getFormattedName(String value,String delim){              
    StringTokenizer st = new StringTokenizer(value);  
String[] v = value.split(delim);
String  name="";
int i;
for(i=0;i<v.length-1;i++){
        name += v[i].trim()+"_";       
}
name=name+v[i];
  return name;
    }

//websearch imp
private class MyBrowser extends WebViewClient {
	   @Override
	   public boolean shouldOverrideUrlLoading(WebView view, String url) {
	      view.loadUrl(url);
	      return true;
	   }
}


public static void getListViewSize(ListView myListView) {
    ListAdapter myListAdapter = myListView.getAdapter();
    if (myListAdapter == null) {
        //do nothing return null
        return;
    }
    //set listAdapter in loop for getting final size
    int totalHeight = 0;
    for (int size = 0; size < myListAdapter.getCount(); size++) {
        View listItem = myListAdapter.getView(size, null, myListView);
        listItem.measure(0, 0);
        totalHeight += listItem.getMeasuredHeight();
    }
  //setting listview item in adapter
    ViewGroup.LayoutParams params = myListView.getLayoutParams();
    params.height = totalHeight + (myListView.getDividerHeight() * (myListAdapter.getCount() - 1));
    myListView.setLayoutParams(params);
    // print height of adapter on log
    Log.i("height of listItem:", String.valueOf(totalHeight));
}

}