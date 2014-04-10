package net.sposter.esudi.ui;

import net.sposter.esudi.R;
import net.sposter.esudi.data.UserOrderListAdapter;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class UserOrderListActivity extends Activity implements OnItemClickListener {
	public ListView lvUserOrderList;

	private UserOrderListAdapter uodAdapter;
	
	public static final int MSG_CLICK = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_order_list);
		
		lvUserOrderList = (ListView)findViewById(R.id.idUserOrderList);
		
		uodAdapter = new UserOrderListAdapter(this);
		
		lvUserOrderList.setOnItemClickListener(this);
		lvUserOrderList.setAdapter(uodAdapter);
		
		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setBackgroundDrawable(this.getBaseContext().getResources().getDrawable(R.drawable.actionbar));
		ab.setTitle("ÀúÊ·¶©µ¥");
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home){
			finish();
		}
		return true;
	}
	
}