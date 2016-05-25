package nitp.avi.geraticsHealthCare;



import java.util.HashMap;
import java.util.List;

import nitp.avadhesh.corona.R;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
	
	
	private Context _context;
	private List<RowItem> _listDataHeader; // header titles
	// child data in format of header title, child title
	private HashMap<String, List<String>> _listDataChild;

	public ExpandableListAdapter(Context context, List<RowItem> listDataHeader,
			HashMap<String, List<String>> listChildData) {
		this._context = context;
		this._listDataHeader = listDataHeader;
		this._listDataChild = listChildData;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition).toString())
				.get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		final String childText = (String) getChild(groupPosition, childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_item, null);
		}

		TextView txtListChild = (TextView) convertView
				.findViewById(R.id.lblListItem);

		txtListChild.setText(childText);
		return convertView;
	}
	@Override
	public int getChildrenCount(int groupPosition) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition).toString())
				.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this._listDataHeader.get(groupPosition).toString();
	}

	@Override
	public int getGroupCount() {
		return this._listDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}
	private class ViewHolder {
		ImageView icon;
		TextView  title;
			
		}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		convertView=null;
		//String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.eventcatcustomize, null);
		 holder=new ViewHolder();
		 holder.title=(TextView) convertView
					.findViewById(R.id.eventname);
		 
		 holder.icon=(ImageView) convertView.findViewById(R.id.imageicon);
		 RowItem pos=_listDataHeader.get(groupPosition);
			
			holder.icon.setImageResource(pos.getIcon());
			holder.title.setText(pos.getTitle());
			
		/*TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.lblListHeader);
		ImageView imageView=(ImageView) convertView.findViewById(R.id.icon);
		lblListHeader.setTypeface(null, Typeface.BOLD);
		
		lblListHeader.setText(headerTitle);
*/
		 convertView.setTag(holder);
		}
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}