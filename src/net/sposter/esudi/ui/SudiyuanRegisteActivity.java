package net.sposter.esudi.ui;

import net.sposter.esudi.R;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class SudiyuanRegisteActivity extends Activity implements OnClickListener {
	private TextView ivSudiyuanRegiste;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sudiyuan_registe);
		
		
		ivSudiyuanRegiste = (TextView) findViewById(R.id.idIVSudiyuanRegiste);
		ivSudiyuanRegiste.setOnClickListener(this);
		ivSudiyuanRegiste.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); 
		
		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setBackgroundDrawable(this.getBaseContext().getResources().getDrawable(R.drawable.actionbar));
		ab.setTitle("注册成为快递员");
	}
	@Override
	public void onClick(View v) {
		if(v == ivSudiyuanRegiste){
			Intent intent = new Intent();
			intent.setClass(SudiyuanRegisteActivity.this, SudiyuanLoginActivity.class);
			startActivity(intent);
			this.finish();
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home){
			finish();
		}
		return true;
	}
}