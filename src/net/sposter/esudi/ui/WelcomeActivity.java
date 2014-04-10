package net.sposter.esudi.ui;

import net.sposter.esudi.R;
import net.sposter.esudi.R.id;
import net.sposter.esudi.R.layout;
import net.sposter.esudi.data.ConstData;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class WelcomeActivity extends Activity {
	private ImageView ivWelcome;
	private Intent intent = new Intent(); 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);  
		setContentView(R.layout.activity_welcome);
		
//		ivWelcome = (ImageView) this.findViewById(R.id.idIVWelcomeImage);  
//		AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);  
//		alphaAnimation.setDuration(1000);  
//		ivWelcome.startAnimation(alphaAnimation);
//		alphaAnimation.setAnimationListener(new WelcomeAnimation());  
		
		
		SharedPreferences userInfo = getSharedPreferences(ConstData.PREFERENCE_FILE_NAME, 0);
		
		int userLog = userInfo.getInt(ConstData.USER_STATE, 0);
		int userType = userInfo.getInt(ConstData.USER_TYPE, 0);
				

		if(userLog == ConstData.USER_LOGOUT){
			userInfo.edit().putInt(ConstData.USER_TYPE, ConstData.USER_NONE).commit();  
			intent.setClass(WelcomeActivity.this, MapActivity.class);  
		}else{
			if(userType == ConstData.USER_USER){
				intent.setClass(WelcomeActivity.this, MapActivity.class);  
			}else{
				intent.setClass(WelcomeActivity.this, SudiyuanQiangdanActivity.class);  
			}
		}
        
        
		new Handler().postDelayed(new Runnable(){
			@Override
			public void run() {
	            startActivity(intent);  
	            finish();  
			}
			
		}, 2000);
        
		
	}

	
//	private class WelcomeAnimation implements AnimationListener{  
//        public void onAnimationStart(Animation animation) {  
//        	  
//        }  
//
//        public void onAnimationRepeat(Animation animation) {  
//
//        }  
//
//        public void onAnimationEnd(Animation animation) {  
//            Intent intent = new Intent();  
//            intent.setClass(WelcomeActivity.this, MapActivity.class);  
//            startActivity(intent);  
//            finish();  
//        }  
//    }
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.welcome, menu);
//		return true;
//	}

}
