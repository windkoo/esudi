package net.sposter.esudi.ui;

import net.sposter.esudi.R;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class UserSendOrderActivity extends Activity implements OnClickListener {
	public Button btnUserCancelOrder;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_send_order);
		
		btnUserCancelOrder = (Button) findViewById(R.id.idUserCancelOrder);
		
		btnUserCancelOrder.setOnClickListener(this);
		
		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setBackgroundDrawable(this.getBaseContext().getResources().getDrawable(R.drawable.actionbar));
		ab.setTitle("一键寄件");
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
		if(v == btnUserCancelOrder){
			Intent intent = new Intent();
			intent.setClass(UserSendOrderActivity.this, UserReceiveOrderedActivity.class);
			startActivity(intent);
			finish();
		}
	}
	
	
}