package nitp.avadhesh.corona;

import nitp.avi.geraticsHealthCare.ShareData;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DiagnosisResult extends Activity {
	String[] diseaseArray={"hello","hi","kaise"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diagnosis_results);
		
		
		
	   // ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item,diseaseArray );
		      
		ListView resultlist = (ListView) findViewById(R.id.listView1);
		//resultlist.setAdapter(adapter);
			 
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.diagnosis_result, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
