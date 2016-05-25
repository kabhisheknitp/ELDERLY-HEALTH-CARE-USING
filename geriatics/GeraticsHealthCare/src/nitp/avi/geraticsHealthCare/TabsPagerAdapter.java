package nitp.avi.geraticsHealthCare;
import nitp.avadhesh.corona.R;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import nitp.avi.geraticsHealthCare.TabFindMoreON;
import nitp.avi.geraticsHealthCare.TabDiseaseDetails;

public class TabsPagerAdapter extends FragmentPagerAdapter {
 
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
        Fragment fragment=null;
        switch (index) {
        case 0:
            // Top Rated fragment activity
        	fragment=(Fragment)new TabDiseaseDetails();
            return fragment;
        case 1:
            // Games fragment activity
        	fragment=new TabFindMoreON();
            return fragment;
        case 2:
            // Movies fragment activity
        	fragment=new TabMyWebViewFragment();
            return fragment;
        case 3:
            // Games fragment activity
        	
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
 
}