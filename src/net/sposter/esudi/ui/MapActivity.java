package net.sposter.esudi.ui;

import java.util.ArrayList;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;

import net.sposter.esudi.R;
import net.sposter.esudi.R.layout;
import net.sposter.esudi.data.ConstData;
import net.sposter.esudi.data.HttpDataModel.Sudiyuan;
import net.sposter.esudi.data.HttpDataModel.Sudiyuan.SudiyuanInfo;
import net.sposter.esudi.data.HttpRequestParams.MyLocation;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.widget.DrawerLayout;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.BDGeofence;
import com.baidu.location.BDLocationStatusCodes;
import com.baidu.location.GeofenceClient;
import com.baidu.location.GeofenceClient.OnAddBDGeofencesResultListener;
import com.baidu.location.GeofenceClient.OnGeofenceTriggerListener;
import com.baidu.location.GeofenceClient.OnRemoveBDGeofencesResultListener;
import com.baidu.location.LocationClientOption.LocationMode;
import com.litesuits.http.LiteHttpClient;
import com.litesuits.http.async.HttpAsyncExcutor;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.Request;
import com.litesuits.http.request.param.HttpParam;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;

@SuppressLint("NewApi")
@SuppressWarnings("unused")
public class MapActivity extends Activity implements OnClickListener {
	private DrawerLayout mDrawerLayout = null;
	// private Button menuButton ;
//	private ImageView menuButton;
    private LinearLayout llUserLogout;
    private LinearLayout llUserHistoryOrders;
    
	private Button btnOneKeySend;
	private Button btnReceive;
    private View vDivider;
	
	BMapManager mBMapMan = null;
	MapView mMapView = null;
	MapController mMapController;
	public LocationClient mLocationClient = null;
	public BDLocationListener myListener = new MyLocationListener();

	private SharedPreferences userInfo;
	private int userLog;
	private int userType;
	
	private LiteHttpClient client;
	private HttpAsyncExcutor asyncExcutor = new HttpAsyncExcutor();
	
	private double userLat=0.0;
	private double userLon=0.0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		mBMapMan = new BMapManager(getApplication());
		mBMapMan.init(null);

		setContentView(R.layout.activity_map);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		btnOneKeySend = (Button) findViewById(R.id.idBtnMapOneKeySend);
		btnReceive = (Button) findViewById(R.id.idBtnMapReceive);
		vDivider =  (View) findViewById(R.id.idViewDivider);
		llUserLogout = (LinearLayout) findViewById(R.id.idLLUserLogOut);
		llUserHistoryOrders = (LinearLayout) findViewById(R.id.idLLUserHistoryOrders);
		

		btnOneKeySend.setOnClickListener(this);
		btnReceive.setOnClickListener(this);
		llUserLogout.setOnClickListener(this);
		llUserHistoryOrders.setOnClickListener(this);
		
		mLocationClient = new LocationClient(getApplicationContext()); // ����LocationClient��
		mLocationClient.registerLocationListener(myListener); // ע�������

		mMapView = (MapView) findViewById(R.id.bmapsView);
		mMapView.setBuiltInZoomControls(true);

		mMapController = mMapView.getController();
		mMapController.setZoom(15);

		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);
		option.setOpenGps(true);
		option.setCoorType("bd09ll");
		option.setScanSpan(10000);
		option.setIsNeedAddress(true);
		option.setNeedDeviceDirect(true);
		mLocationClient.setLocOption(option);
		
		
		userInfo = getSharedPreferences(ConstData.PREFERENCE_FILE_NAME, 0);

		ActionBar ab = getActionBar();
		
//		ab.setDisplayHomeAsUpEnabled(true);
		ab.setBackgroundDrawable(this.getBaseContext().getResources().getDrawable(R.drawable.actionbar));
//		SpannableString spannableString = new SpannableString("TimeToDo");
//		spannableString.setSpan(new com.masaila.timetodo.font.TypefaceSpan(this, "Roboto-Light.ttf"), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//		spannableString.setSpan(new AbsoluteSizeSpan(24, true), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		ab.setTitle(R.string.title_sudi);
		
		
		client = LiteHttpClient.getInstance(this);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu); 
		
		MenuItem mi = menu.findItem(R.id.itemmenu);
		userLog = userInfo.getInt(ConstData.USER_STATE, 0);
		if(userLog == ConstData.USER_LOGIN){
			mi.setVisible(true);
		}else{
			mi.setVisible(false);
		}

		return true;
	}

//	
//	@Override
//	public boolean onPrepareOptionsMenu(Menu menu) {
//		
//		return true;
//	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.itemmenu){
			mDrawerLayout.openDrawer(Gravity.RIGHT);
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		if (v == btnOneKeySend) {
			Intent intent = new Intent();
			if(userLog == ConstData.USER_LOGOUT){
				intent.setClass(MapActivity.this, UserLoginActivity.class);
			}else{
				intent.setClass(MapActivity.this, UserSendOrderActivity.class);
			}
			startActivity(intent);
		} else if (v == btnReceive) {
			Intent intent = new Intent();
			intent.setClass(MapActivity.this, SudiyuanRegisteActivity.class);
			startActivity(intent);
		}else if (v == llUserLogout) {
			userInfo.edit().putString(ConstData.USER_PHONE, "").commit();
			userInfo.edit().putInt(ConstData.USER_STATE, ConstData.USER_LOGOUT).commit();
			userInfo.edit().putInt(ConstData.USER_TYPE, ConstData.USER_NONE).commit();
			mDrawerLayout.closeDrawers();
			vDivider.setVisibility(View.VISIBLE);
			btnReceive.setVisibility(View.VISIBLE);
			invalidateOptionsMenu();
		}else if (v == llUserHistoryOrders) {
			mDrawerLayout.closeDrawers();
			Intent intent = new Intent();
			intent.setClass(MapActivity.this, UserOrderListActivity.class);
			startActivity(intent);
		}
	}

	@Override
	protected void onDestroy() {
		mMapView.destroy();
		if (mBMapMan != null) {
			mBMapMan.destroy();
			mBMapMan = null;
		}
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		if (mBMapMan != null) {
			mBMapMan.stop();
		}
		super.onPause();
		
		if(mLocationClient!=null){
			mLocationClient.stop();
		}
	}

	@Override
	protected void onResume() {
		userLog = userInfo.getInt(ConstData.USER_STATE, 0);
		userType = userInfo.getInt(ConstData.USER_TYPE, 0);
		if(userLog == ConstData.USER_LOGIN){
			if(userType == ConstData.USER_USER){
				vDivider.setVisibility(View.GONE);
				btnReceive.setVisibility(View.GONE);
			}
		}else{
			vDivider.setVisibility(View.VISIBLE);
			btnReceive.setVisibility(View.VISIBLE);
		}
		invalidateOptionsMenu();
		mMapView.onResume();
		if (mBMapMan != null) {
			mBMapMan.start();
		}
		super.onResume();
		
		if(mLocationClient!=null){
			mLocationClient.start();
			mLocationClient.requestLocation();
		}

	}

	public class MyLocationListener implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null)
				return;
			mLocationClient.stop();
			userLat = location.getLatitude(); 
			userLon = location.getLongitude();  
			MyLocation params = new MyLocation(userLat, userLon);
			getSudiyuanLocation(params);
		}

		public void onReceivePoi(BDLocation poiLocation) {
			if (poiLocation == null) {
				return;
			}
		}
	}
	
	
	class OverlaySudiyuan extends ItemizedOverlay<OverlayItem> {  
	    public OverlaySudiyuan(Drawable mark,MapView mapView){  
	            super(mark,mapView);  
	    } 
	    protected boolean onTap(int index) {  
	        System.out.println("item onTap: "+index);  
	        return true;  
	    }  
	    public boolean onTap(GeoPoint pt, MapView mapView){  
	                super.onTap(pt,mapView);  
	                return false;  
	   }
	}  
	
	void getSudiyuanLocation(HttpParam  para){

		asyncExcutor.execute(client, 
				new Request(ConstData.GET_SUDIYUAN_LOCATION_URL, para), 
				new HttpModelHandler<Sudiyuan>() {
			protected void onSuccess(Sudiyuan data, Response res) {
				Log.i("esudi", "User List: " + data);

//				MyLocationOverlay myLocationOverlay = new MyLocationOverlay(mMapView);  
//				LocationData locData = new LocationData();  
//				locData.latitude =  userLat;
//				locData.longitude = userLon;
//				locData.direction = 2.0f;  
//				myLocationOverlay.setData(locData);  
//				mMapView.getOverlays().add(myLocationOverlay);  
			
				mMapView.getOverlays().clear();  
				
				GeoPoint meGeo = new GeoPoint((int) (userLat * 1E6), (int) (userLon * 1E6));  
				Drawable markMe= getResources().getDrawable(R.drawable.mappinrecipient);  
				OverlayItem itemMe = new OverlayItem(meGeo,"itemMe","itemMe"); 
				itemMe.setMarker(markMe);
				OverlaySudiyuan itemOverlayMe = new OverlaySudiyuan(markMe, mMapView);
				
				itemOverlayMe.addItem(itemMe);
				mMapView.getOverlays().add(itemOverlayMe);  
				
				Drawable mark= getResources().getDrawable(R.drawable.mappinsent);  
				OverlaySudiyuan itemOverlay = new OverlaySudiyuan(mark, mMapView);
				
				double i=0.02;
				int idd=0;
				java.util.Random r=new java.util.Random(20); 
				for(SudiyuanInfo sdy:data.body){

//					double mLat1 = sdy.latitude;  
//					double mLon1 = sdy.longitude; 
					double mLat1;  
					double mLon1;  
					if(idd%2 == 0){
						 mLat1 = userLat - r.nextDouble()*i;  
						 mLon1 = userLon + r.nextDouble()*i;  
					}else{
						 mLat1 = userLat + r.nextDouble()*i;  
						 mLon1 = userLon - r.nextDouble()*i;  
					}
					idd++;
					
					GeoPoint p1 = new GeoPoint((int) (mLat1 * 1E6), (int) (mLon1 * 1E6));  
					OverlayItem item = new OverlayItem(p1,sdy.name,sdy.name); 
					item.setMarker(mark);
					itemOverlay.addItem(item);  
				}
				mMapView.getOverlays().add(itemOverlay);  
				
				mMapController.setCenter(meGeo);
				mMapView.refresh();  
				mMapView.getController().animateTo(new GeoPoint((int)(userLat*1e6),  
				(int)(userLon* 1e6)));  
			}

			@Override
			protected void onFailure(HttpException e, Response res) {
				
			}

		});
	}
	
}
