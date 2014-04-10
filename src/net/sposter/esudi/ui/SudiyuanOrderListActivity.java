package net.sposter.esudi.ui;

import net.sposter.esudi.R;
import net.sposter.esudi.data.SudiyuanOrderListAdapter;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SudiyuanOrderListActivity extends Activity  implements OnItemClickListener {
	public ListView idSudiyuanOrderList;
	private SudiyuanOrderListAdapter sdyOrderListAdapter;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sudiyuan_order_list);
		
		idSudiyuanOrderList = (ListView)findViewById(R.id.idSudiyuanOrderList);
		idSudiyuanOrderList.setOnItemClickListener(this);
		
		sdyOrderListAdapter = new SudiyuanOrderListAdapter(this);
		
		idSudiyuanOrderList.setAdapter(sdyOrderListAdapter);
		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setBackgroundDrawable(this.getBaseContext().getResources().getDrawable(R.drawable.actionbar));
		ab.setTitle("历史订单");
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home){
			finish();
		}
		return true;
	}
	
	
	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		
		
	}
}