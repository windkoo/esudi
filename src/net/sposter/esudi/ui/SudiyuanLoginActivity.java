package net.sposter.esudi.ui;

import net.sposter.esudi.R;
import net.sposter.esudi.data.ConstData;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SudiyuanLoginActivity extends Activity implements OnClickListener {
	private Button idBtnSudiyuanLogIn;
	private EditText idSudiyuanLoginPhone;
	private EditText idSudiyuanCheckPWD;
	private Button idBtnSudiyuanRequireCheckPwd;
	private TextView idTVSudiyuanLoginToRegiste;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sudiyuan_login);
		
		idBtnSudiyuanLogIn = (Button)findViewById(R.id.idBtnSudiyuanLogIn);
		idSudiyuanLoginPhone = (EditText)findViewById(R.id.idSudiyuanLoginPhone);
		idSudiyuanCheckPWD = (EditText)findViewById(R.id.idSudiyuanCheckPWD);
		idBtnSudiyuanRequireCheckPwd = (Button)findViewById(R.id.idBtnSudiyuanRequireCheckPwd);
		idTVSudiyuanLoginToRegiste = (TextView)findViewById(R.id.idTVSudiyuanLoginToRegiste);
		
		idBtnSudiyuanLogIn.setOnClickListener(this);
		idBtnSudiyuanRequireCheckPwd.setOnClickListener(this);
		idTVSudiyuanLoginToRegiste.setOnClickListener(this);
		
		idTVSudiyuanLoginToRegiste.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		
		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setBackgroundDrawable(this.getBaseContext().getResources().getDrawable(R.drawable.actionbar));
		ab.setTitle("µÇÂ¼");
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home){
			finish();
		}
		return true;
	}
	
	@Override
	public void onClick(View v) {
		if(v == idBtnSudiyuanLogIn){
			idSudiyuanLoginPhone.setText("18682759881");
			SharedPreferences userInfo = getSharedPreferences(ConstData.PREFERENCE_FILE_NAME, 0);
			userInfo.edit().putString(ConstData.USER_PHONE, idSudiyuanLoginPhone.getText().toString()).commit();  
			userInfo.edit().putInt(ConstData.USER_TYPE, ConstData.USER_SUDIYUAN).commit();
			userInfo.edit().putInt(ConstData.USER_STATE, ConstData.USER_LOGIN).commit();
			
			Intent intent = new Intent();
			intent.setClass(this, SudiyuanQiangdanActivity.class);
			startActivity(intent);
			this.finish();
		}else if(v == idBtnSudiyuanRequireCheckPwd){
			
		}else if(v == idTVSudiyuanLoginToRegiste){
			Intent intent = new Intent();
			intent.setClass(this, SudiyuanRegisteActivity.class);
			startActivity(intent);
			this.finish();
		}
	}
}