package net.sposter.esudi.data;

import java.util.ArrayList;
import java.util.List;
import net.sposter.esudi.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UserOrderListAdapter extends BaseAdapter {

	public List<OrderData> data = new ArrayList<OrderData>();
	Context context;

	public UserOrderListAdapter(Context context){
		OrderData ttt = new OrderData();
		ttt.OrderID = 123;
		ttt.OrderState = 0;
		ttt.OrderCreateDate = "2014年04月04日        16:08";
		ttt.OrderCreateAddress = "成都市天赋软件园";
		ttt.OrderDistince = "距离2公里";
		
		data.add(ttt);
		ttt.OrderDistince = "距离3公里";
		data.add(ttt);
		ttt.OrderDistince = "距离4公里";
		data.add(ttt);
		ttt.OrderDistince = "距离5公里";
		data.add(ttt);

		this.context = context;
	}
	
		
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int index) {
		return data.get(index);
	}

	@Override
	public long getItemId(int index) {
		
		return data.get(index).OrderID;
	}

	 public final class ListItemView{                //自定义控件集合     
//         public ImageView imageIcon;     
//         public TextView address;     
         public TextView date;   
//         public TextView distance;  
         public ImageView ivHasNewMessage;          
  }   
	 
	@Override
	public View getView(int index, View convertView, ViewGroup parent) {
		LayoutInflater listContainer;   //视图容器工厂

        listContainer = LayoutInflater.from(context); //创建视图容器工厂并设置上下文
        ListItemView  listItemView = null;   
        if (convertView == null) {
        	listItemView = new ListItemView();
        	convertView = listContainer.inflate(R.layout.activity_user_order_listitem, null);   //创建list_item.xml布局文件的视图
        	listItemView.date = (TextView)convertView.findViewById(R.id.idTVUserOrderDateTime);   
        	listItemView.ivHasNewMessage = (ImageView)convertView.findViewById(R.id.idTvUserHasNewMessage);
        	convertView.setTag(listItemView);
        }else{
        	listItemView = (ListItemView)convertView.getTag(); 
        }
        
        OrderData od = data.get(index);
        listItemView.date.setText(od.OrderCreateDate);

		return convertView;
	}

}
