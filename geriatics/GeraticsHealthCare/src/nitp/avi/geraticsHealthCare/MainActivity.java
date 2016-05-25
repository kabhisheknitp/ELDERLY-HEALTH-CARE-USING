package nitp.avi.geraticsHealthCare;



import nitp.avadhesh.corona.R;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity {
ImageView anime;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		anime=(ImageView) findViewById(R.id.imageView1);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Animation animation=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.wc);
				anime.setAnimation(animation);
			
			}
		}).start();
	
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent home=new Intent(MainActivity.this,Home.class);
				startActivity(home);
				/// finish this activity..
				finish();
			}
		}, 3100);
	}
//5000
	
}
