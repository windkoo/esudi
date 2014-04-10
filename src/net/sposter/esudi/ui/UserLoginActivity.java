package net.sposter.esudi.ui;

import com.litesuits.http.LiteHttpClient;
import com.litesuits.http.async.HttpAsyncExcutor;
import com.litesuits.http.data.HttpStatus;
import com.litesuits.http.data.NameValuePair;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.Request;
import com.litesuits.http.request.param.HttpMethod;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpResponseHandler;

import net.sposter.esudi.R;
import net.sposter.esudi.data.ConstData;
import net.sposter.esudi.data.HttpRequestParams;
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
	private LiteHttpClient client;
	HttpAsyncExcutor asyncExcutor = new HttpAsyncExcutor();
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
		ab.setTitle("登录");
		
		client = LiteHttpClient.getInstance(this);
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
			btnUserLogin.setEnabled(false);
			
			
			Request req = new Request(ConstData.GET_LOGIN_BY_SHORTMSG_URL, 
					new HttpRequestParams.LogInMsg(etPhone.getText().toString(),
					etCheckPwd.getText().toString()));
			btnReqiureCheckPwd.setEnabled(false);

			asyncExcutor.execute(client, req, new HttpResponseHandler() {
				@Override
				protected void onSuccess(Response res, HttpStatus status, NameValuePair[] headers) {
					btnUserLogin.setEnabled(true);
					SharedPreferences userInfo = getSharedPreferences(ConstData.PREFERENCE_FILE_NAME, 0);
					userInfo.edit().putString(ConstData.USER_PHONE, etPhone.getText().toString()).commit();  
					userInfo.edit().putInt(ConstData.USER_TYPE, ConstData.USER_USER).commit();
					userInfo.edit().putInt(ConstData.USER_STATE, ConstData.USER_LOGIN).commit();
					
					Intent intent = new Intent();
					intent.setClass(UserLoginActivity.this, UserSendOrderActivity.class);
					startActivity(intent);
					UserLoginActivity.this.finish();
				}

				@Override
				protected void onFailure(Response res, HttpException e) {
					btnUserLogin.setEnabled(true);
				}
			});
		}else if(v == btnReqiureCheckPwd){
			Request req = new Request(ConstData.GET_SHOT_MESSAGE_URL, new HttpRequestParams.GetShortMsg(etPhone.getText().toString()));
			btnReqiureCheckPwd.setEnabled(false);

			asyncExcutor.execute(client, req, new HttpResponseHandler() {
				@Override
				protected void onSuccess(Response res, HttpStatus status, NameValuePair[] headers) {
					btnReqiureCheckPwd.setEnabled(true);
				}

				@Override
				protected void onFailure(Response res, HttpException e) {
					btnReqiureCheckPwd.setEnabled(true);
				}
			});
		}
	}
}