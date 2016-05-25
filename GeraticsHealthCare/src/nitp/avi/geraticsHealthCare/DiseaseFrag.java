package nitp.avi.geraticsHealthCare;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import nitp.avadhesh.corona.R;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class DiseaseFrag extends  Fragment {
	
	 ExpandableListAdapter listAdapter;
		ExpandableListView expListView;
		List<RowItem> listDataHeader;
		HashMap<String, List<String>> listDataChild;
		String[] menutitles;
		TypedArray menuIcons;
		View rootView;
		AutoCompleteTextView text1;
		 Button submit;
		  String[] list;
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			 rootView = inflater.inflate(R.layout.diseasefrag, container, false);
			  submit=(Button) rootView.findViewById(R.id.button1);
			  
			 //auto complete box implementation....
			 text1=(AutoCompleteTextView)rootView.findViewById(R.id.autoCompleteTextView1);
			 list= getResources().getStringArray(R.array.diseaseName);
		      ArrayAdapter <String>  adapter = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_list_item_1,list);
		       text1.setThreshold(1);
		       text1.setAdapter(adapter);
		       
		     //submit button work///////////////////////////////////////
		        submit.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						  String value=text1.getText().toString();
						  Intent tab=new Intent(getActivity(),DiseaseContainer.class);
						  tab.putExtra("value",value );
						  ShareData.data().Diseasename=value;
						  startActivity(tab);	
						
					}
				});
		        
			
	// Expandable list implementation 		 
    menutitles = getResources().getStringArray(R.array.event);
	menuIcons = getResources().obtainTypedArray(R.array.eventicons);
	listDataHeader=new ArrayList<RowItem>();
	
	// get the listview
			expListView = (ExpandableListView)rootView.findViewById(R.id.expandableListView1);

			// preparing list data
			prepareListData();

			listAdapter = new ExpandableListAdapter(rootView.getContext(), listDataHeader, listDataChild);

			// setting list adapter
			expListView.setAdapter(listAdapter);

			// Listview Group click listener
			expListView.setOnGroupClickListener(new OnGroupClickListener() {

				@Override
				public boolean onGroupClick(ExpandableListView parent, View v,
						int groupPosition, long id) {
					// Toast.makeText(getApplicationContext(),
					// "Group Clicked " + listDataHeader.get(groupPosition),
					// Toast.LENGTH_SHORT).show();
					return false;
				}
			});
			
			expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

				@Override
				public void onGroupExpand(int groupPosition) {
					//Toast.makeText(rootView.getContext()," Expanded",Toast.LENGTH_SHORT).show();
				}
			});

			// Listview Group collasped listener
			expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

				@Override
				public void onGroupCollapse(int groupPosition) {
					//Toast.makeText(rootView.getContext(),"Collapsed",Toast.LENGTH_SHORT).show();

				}
			});
			
			// Listview on child click listener
			expListView.setOnChildClickListener(new OnChildClickListener() {

				@Override
				public boolean onChildClick(ExpandableListView parent, View v,
						int groupPosition, int childPosition, long id) {
					// TODO Auto-generated method stub
					Intent tab=new Intent(getActivity(),DiseaseContainer.class);
					tab.putExtra("value", listDataChild.get(
							listDataHeader.get(groupPosition).toString()).get(
							childPosition));
					  ShareData.data().Diseasename = listDataChild.get(
								listDataHeader.get(groupPosition).toString()).get(
										childPosition);
					  ShareData.data().source="xml";
					startActivity(tab);	
				/*	Toast.makeText(
							rootView.getContext(),
							listDataChild.get(
											listDataHeader.get(groupPosition).toString()).get(
											childPosition), Toast.LENGTH_SHORT)
							.show();
					*/
					return false;
				}
			});
			
			return rootView;
		}


private void prepareListData() {
	//listDataHeader = new ArrayList<String>();
	for (int i = 0; i < menutitles.length; i++) {
		RowItem items = new RowItem(menutitles[i], menuIcons.getResourceId(
				i, -1));
		listDataHeader.add(items);
	
	}
	menuIcons.recycle();
	listDataChild = new HashMap<String, List<String>>();

	// Adding child data
	List<String> cardiovascular = new ArrayList<String>();
	cardiovascular.add("   Hypertension");
	cardiovascular.add("   Ischaemic Heart Disease");
	cardiovascular.add("   Acute Myocardial Infarction");
	cardiovascular.add("   Chronic Cardiac Failure");
	cardiovascular.add("   Orthostatic Hypotension");
	cardiovascular.add("   Syncope");

	List<String> respiratory = new ArrayList<String>();
	respiratory.add("   Pneumonia");
	respiratory.add("   Tuberculosis");
	respiratory.add("   Bronchial Asthma");
	respiratory.add("   Chronic Obstructive Pulmonary Disease");
	respiratory.add("   Lung cancer");

	List<String> gastrointestinal = new ArrayList<String>();
	gastrointestinal.add("   Hiatus Hernia & Gastroesophageal Reflux");
	gastrointestinal.add("   NSAID Gastropathy & peptic ulcer");
	gastrointestinal.add("   Non Ulcer Dyspepsia");
	gastrointestinal.add("   Cancer of GI tract");
	gastrointestinal.add("   Constipation");
	
	
	List<String> endocrine = new ArrayList<String>();
	endocrine.add("   Diabetes Mellitus");
	endocrine.add("   HypoThyroidism");
	endocrine.add("   HyperThyroidism");
	

	List<String> urinary = new ArrayList<String>();
	urinary.add("   Urinary Tract Infection");
	urinary.add("   Benign Prostatic Hypertrophy");
	urinary.add("   Malignancy Of Prostate");
	urinary.add("   Urinary Incontinence");

	List<String> brainagingCI = new ArrayList<String>();
	brainagingCI.add("   Dementia");
	brainagingCI.add("   Alzheimer");
	brainagingCI.add("   Vascular Dementia");
	brainagingCI.add("   Confusion/Delirium");
	
	List<String> musculoskeletal = new ArrayList<String>();
	musculoskeletal.add("   Osteoarthritis");
	musculoskeletal.add("   Rheumatoid Arthritis");
	musculoskeletal.add("   Osteoporosis");
	
	List<String> neurological = new ArrayList<String>();
	neurological.add("   Parkinson Disease");
	neurological.add("   Stroke");
	
	List<String> mentalHealth = new ArrayList<String>();
	mentalHealth.add("   Depression");
	
	List<String> sensory = new ArrayList<String>();
	sensory.add("   Cataract");
	sensory.add("   Glaucoma");
	sensory.add("   Skin Infection");
	
	List<String> cancer= new ArrayList<String>();
	cancer.add("   Cancer");
	

	listDataChild.put(listDataHeader.get(0).toString(), cardiovascular); // Header, Child data
	listDataChild.put(listDataHeader.get(1).toString(), respiratory);
	listDataChild.put(listDataHeader.get(2).toString(), gastrointestinal);
	listDataChild.put(listDataHeader.get(3).toString(), endocrine);
	listDataChild.put(listDataHeader.get(4).toString(), urinary);
	listDataChild.put(listDataHeader.get(5).toString(), mentalHealth);
	listDataChild.put(listDataHeader.get(6).toString(), neurological);
	listDataChild.put(listDataHeader.get(7).toString(), sensory);
	listDataChild.put(listDataHeader.get(8).toString(), musculoskeletal);
	listDataChild.put(listDataHeader.get(9).toString(), brainagingCI);
	listDataChild.put(listDataHeader.get(10).toString(), cancer);
	
}

}
