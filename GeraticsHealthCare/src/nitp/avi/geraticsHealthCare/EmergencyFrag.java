package nitp.avi.geraticsHealthCare;

import java.util.ArrayList;
import java.util.List;

import nitp.avadhesh.corona.R;
import nitp.avi.geraticsHealthCare.Home.SlideitemListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class EmergencyFrag extends Fragment {
	String[] menutitles;
	TypedArray menuIcons;
	private List<RowItem> rowItems;
	private CustomAdapter adapter;
	View rootview;
	Button button;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootview=inflater.inflate(R.layout.emergencyfrag, container, false);
		
		ListView mDrawerList = (ListView) rootview.findViewById(R.id.listView1);
		button=(Button) rootview.findViewById(R.id.imageButton1);
		menutitles = getResources().getStringArray(R.array.contact);
		menuIcons = getResources().obtainTypedArray(R.array.pic);
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
		
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Intent.ACTION_CALL_BUTTON);  
	            startActivity(i);

			}
		});
		
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
		String number = rowItems.get(position).getTitle().toString();
		 Intent callIntent = new Intent(Intent.ACTION_CALL);  
         callIntent.setData(Uri.parse("tel:"+number));  
         startActivity(callIntent); 
	}
	
}
