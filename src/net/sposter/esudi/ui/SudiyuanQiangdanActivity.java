package net.sposter.esudi.ui;

import net.sposter.esudi.R;
import net.sposter.esudi.data.ConstData;
import net.sposter.esudi.data.SudiyuanQiangDanAdapter;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;

public class SudiyuanQiangdanActivity extends Activity implements OnClickListener {
	public ListView lvQiangdan;
	private QiangDanHander qdHandler =  new QiangDanHander();
	private SudiyuanQiangDanAdapter sdyAdapter;
	public static final int MSG_QIANG_DAN = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sudiyuan_qiangdan);
		
		lvQiangdan = (ListView)findViewById(R.id.idSudiyuanQiangdanList);
		
		sdyAdapter = new SudiyuanQiangDanAdapter(this, qdHandler);
		
		lvQiangdan.setAdapter(sdyAdapter);
		
		ActionBar ab = getActionBar();
//		ab.setDisplayHomeAsUpEnabled(true);
		ab.setBackgroundDrawable(this.getBaseContext().getResources().getDrawable(R.drawable.actionbar));
		ab.setTitle("抢单");
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu); 
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home){
			finish();
		}else if(item.getItemId() == R.id.itemmenu){
			Intent intent = new Intent();
			intent.setClass(SudiyuanQiangdanActivity.this, SudiyuanOrderListActivity.class);
			startActivity(intent);
		}
		return true;
	}
	
	
	class QiangDanHander extends Handler{
		@Override
		public void handleMessage(Message msg) {
			if(msg.what == MSG_QIANG_DAN){
				Intent intent = new Intent();
				intent.setClass(SudiyuanQiangdanActivity.this, SudiyuanQiangdanSuccessActivity.class);
				startActivity(intent);
			}
		}
		
	}


	@Override
	public void onClick(View v) {

	}
}