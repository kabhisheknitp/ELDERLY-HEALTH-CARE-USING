package nitp.avi.geraticsHealthCare;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import nitp.avadhesh.corona.R;
import android.os.Bundle;
import android.app.Fragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class HelpFrag extends Fragment{

	View rootview;
	static String subject ,query;
	EditText sub,qry;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootview=inflater.inflate(R.layout.helpfrag, container, false);
		sub=(EditText) rootview.findViewById(R.id.editText1);
		qry=(EditText) rootview.findViewById(R.id.editText2);
		
	
		
		 rootview.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	 subject=sub.getText().toString();
	        		 query=qry.getText().toString();
	                sendFeedback(subject,query);
	            }
	        });
	       
		
		return rootview;
		//return super.onCreateView(inflater, container, savedInstanceState);
	}
	private void sendFeedback(String s,String q) {
        final Intent _Intent = new Intent(android.content.Intent.ACTION_SEND);
        _Intent.setType("text/html");
        _Intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ getString(R.string.mail) });
        _Intent.putExtra(android.content.Intent.EXTRA_SUBJECT, s);
        _Intent.putExtra(android.content.Intent.EXTRA_TEXT,q);
        startActivity(Intent.createChooser(_Intent, "Ask Your Query"));
    }
}
