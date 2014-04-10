package net.sposter.esudi.ui;

import net.sposter.esudi.R;
import net.sposter.esudi.data.ConstData;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class UserLoginActivity extends Activity implements OnClickListener {
	private Button btnUserLogin;
	private EditText etPhone;
	private EditText etCheckPwd;
	private Button btnReqiureCheckPwd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_login);
		
		btnUserLogin = (Button)findViewById(R.id.idBtnUserLogIn);
		etPhone = (EditText)findViewById(R.id.idUserLoginPhone);
		etCheckPwd = (EditText)findViewById(R.id.idUserLoginCheckPWD);
		btnReqiureCheckPwd = (Button)findViewById(R.id.idBtnUserRequireCheckPwd);
		
		btnUserLogin.setOnClickListener(this);
		btnReqiureCheckPwd.setOnClickListener(this);
		
		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setBackgroundDrawable(this.getBaseContext().getResources().getDrawable(R.drawable.actionbar));
		ab.setTitle("µÇÂ¼");
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.main, menu); 
//		return true;
//	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home){
			finish();
		}
		return true;
	}
	
	@Override
	public void onClick(View v) {
		if(v == btnUserLogin){
			etPhone.setText("18682759881");
			SharedPreferences userInfo = getSharedPreferences(ConstData.PREFERENCE_FILE_NAME, 0);
			userInfo.edit().putString(ConstData.USER_PHONE, etPhone.getText().toString()).commit();  
			userInfo.edit().putInt(ConstData.USER_TYPE, ConstData.USER_USER).commit();
			userInfo.edit().putInt(ConstData.USER_STATE, ConstData.USER_LOGIN).commit();
			
			Intent intent = new Intent();
			intent.setClass(this, UserSendOrderActivity.class);
			startActivity(intent);
			this.finish();
		}else if(v == btnReqiureCheckPwd){
			
		}
	}
}