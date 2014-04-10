package net.sposter.esudi.ui;

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
//�����õ�λ�����ѹ��ܣ���Ҫimport����
//���ʹ�õ���Χ�����ܣ���Ҫimport������
import com.baidu.location.BDGeofence;
import com.baidu.location.BDLocationStatusCodes;
import com.baidu.location.GeofenceClient;
import com.baidu.location.GeofenceClient.OnAddBDGeofencesResultListener;
import com.baidu.location.GeofenceClient.OnGeofenceTriggerListener;
import com.baidu.location.GeofenceClient.OnRemoveBDGeofencesResultListener;
import com.baidu.location.LocationClientOption.LocationMode;

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
		mLocationClient.registerLocationListener(myListener); // ע���������

		mMapView = (MapView) findViewById(R.id.bmapsView);
		mMapView.setBuiltInZoomControls(true);
		// �����������õ����ſؼ�
		mMapController = mMapView.getController();
		mMapController.setZoom(15);// ���õ�ͼzoom����

		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);//���ö�λģʽ
		option.setOpenGps(true);
		option.setCoorType("bd09ll");//���صĶ�λ����ǰٶȾ�γ�ȣ�Ĭ��ֵgcj02
		option.setScanSpan(10000);//���÷���λ����ļ��ʱ��Ϊ5000ms
		option.setIsNeedAddress(true);//���صĶ�λ���������ַ��Ϣ
		option.setNeedDeviceDirect(true);//���صĶ�λ��������ֻ���ͷ�ķ���
		mLocationClient.setLocOption(option);
		mLocationClient.start();
		mLocationClient.requestLocation();
		
		userInfo = getSharedPreferences(ConstData.PREFERENCE_FILE_NAME, 0);

		ActionBar ab = getActionBar();
		
//		ab.setDisplayHomeAsUpEnabled(true);
		ab.setBackgroundDrawable(this.getBaseContext().getResources().getDrawable(R.drawable.actionbar));
//		SpannableString spannableString = new SpannableString("TimeToDo");
//		spannableString.setSpan(new com.masaila.timetodo.font.TypefaceSpan(this, "Roboto-Light.ttf"), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//		spannableString.setSpan(new AbsoluteSizeSpan(24, true), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		ab.setTitle("�ٵ�");
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
	}

	public class MyLocationListener implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null)
				return;

			
			MyLocationOverlay myLocationOverlay = new MyLocationOverlay(mMapView);  
			LocationData locData = new LocationData();  
			//�ֶ���λ��Դ��Ϊ�찲�ţ���ʵ��Ӧ���У���ʹ�ðٶȶ�λSDK��ȡλ����Ϣ��Ҫ��SDK����ʾһ��λ�ã���Ҫʹ�ðٶȾ�γ�����꣨bd09ll��  
			locData.latitude = location.getLatitude();  
			locData.longitude = location.getLongitude();  
			locData.direction = 2.0f;  
			myLocationOverlay.setData(locData);  
			mMapView.getOverlays().add(myLocationOverlay);  
		
			
			
			double mLat1 = locData.latitude + 0.01;  
			double mLon1 = locData.longitude + 0.01;  
			
			GeoPoint p1 = new GeoPoint((int) (mLat1 * 1E6), (int) (mLon1 * 1E6));  
			//׼��overlayͼ�����ݣ�����ʵ������޸�  
			Drawable mark= getResources().getDrawable(R.drawable.mappinsent);  
			
			OverlayItem item1 = new OverlayItem(p1,"item1","item1"); 
			item1.setMarker(mark);
			OverlaySudiyuan itemOverlay = new OverlaySudiyuan(mark, mMapView);
			
			
			GeoPoint meGeo = new GeoPoint((int) (locData.latitude * 1E6), (int) (locData.longitude * 1E6));  
			Drawable markMe= getResources().getDrawable(R.drawable.mappinrecipient);  
			OverlayItem itemMe = new OverlayItem(meGeo,"itemMe","itemMe"); 
			itemMe.setMarker(markMe);
			OverlaySudiyuan itemOverlayMe = new OverlaySudiyuan(markMe, mMapView);
			
			
			mMapView.getOverlays().clear();  
			mMapView.getOverlays().add(itemOverlay);  
			mMapView.getOverlays().add(itemOverlayMe);  
			itemOverlay.addItem(item1);  
			itemOverlayMe.addItem(itemMe);
			mMapController.setCenter(meGeo);
			mMapView.refresh();  
			mMapView.getController().animateTo(new GeoPoint((int)(locData.latitude*1e6),  
			(int)(locData.longitude* 1e6)));  
			
			
//			StringBuffer sb = new StringBuffer(256);
//			sb.append("time : ");
//			sb.append(location.getTime());
//			sb.append("\nerror code : ");
//			sb.append(location.getLocType());
//			sb.append("\nlatitude : ");
//			sb.append(location.getLatitude());
//			sb.append("\nlontitude : ");
//			sb.append(location.getLongitude());
//			sb.append("\nradius : ");
//			sb.append(location.getRadius());
//			if (location.getLocType() == BDLocation.TypeGpsLocation) {
//				sb.append("\nspeed : ");
//				sb.append(location.getSpeed());
//				sb.append("\nsatellite : ");
//				sb.append(location.getSatelliteNumber());
//			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
//				sb.append("\naddr : ");
//				sb.append(location.getAddrStr());
//			}
//Log.d("tangbiao", sb.toString());
		}

		public void onReceivePoi(BDLocation poiLocation) {
			// �����¸��汾��ȥ��poi����
			if (poiLocation == null) {
				return;
			}
//			StringBuffer sb = new StringBuffer(256);
//			sb.append("Poi time : ");
//			sb.append(poiLocation.getTime());
//			sb.append("\nerror code : ");
//			sb.append(poiLocation.getLocType());
//			sb.append("\nlatitude : ");
//			sb.append(poiLocation.getLatitude());
//			sb.append("\nlontitude : ");
//			sb.append(poiLocation.getLongitude());
//			sb.append("\nradius : ");
//			sb.append(poiLocation.getRadius());
//			if (poiLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
//				sb.append("\naddr : ");
//				sb.append(poiLocation.getAddrStr());
//			}
//			if (poiLocation.hasPoi()) {
//				sb.append("\nPoi:");
//				sb.append(poiLocation.getPoi());
//			} else {
//				sb.append("noPoi information");
//			}
//			Log.d("tangbiao2", sb.toString());
		}
	}
	
	
	class OverlaySudiyuan extends ItemizedOverlay<OverlayItem> {  
	    //��MapView����ItemizedOverlay  
	    public OverlaySudiyuan(Drawable mark,MapView mapView){  
	            super(mark,mapView);  
	    }  
	    protected boolean onTap(int index) {  
	        //�ڴ˴���item����¼�  
	        System.out.println("item onTap: "+index);  
	        return true;  
	    }  
	    public boolean onTap(GeoPoint pt, MapView mapView){  
	                //�ڴ˴���MapView�ĵ���¼��������� trueʱ  
	                super.onTap(pt,mapView);  
	                return false;  
	   }  

	}          
}
