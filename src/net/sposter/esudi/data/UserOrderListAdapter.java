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
		ttt.OrderCreateDate = "2014年04月04日       16:08";
		ttt.OrderCreateAddress = "成都天赋软件园A区";
		ttt.OrderDistince = "2公里以内";
		
		data.add(ttt);
		ttt.OrderDistince = "2公里以内";
		data.add(ttt);
		ttt.OrderDistince = "2公里以内";
		data.add(ttt);
		ttt.OrderDistince = "2公里以内";
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

	 public final class ListItemView{                //�Զ���ؼ�����     
//         public ImageView imageIcon;     
//         public TextView address;     
         public TextView date;   
//         public TextView distance;  
         public ImageView ivHasNewMessage;          
  }   
	 
	@Override
	public View getView(int index, View convertView, ViewGroup parent) {
		LayoutInflater listContainer;   //��ͼ��������

        listContainer = LayoutInflater.from(context); //������ͼ��������������������
        ListItemView  listItemView = null;   
        if (convertView == null) {
        	listItemView = new ListItemView();
        	convertView = listContainer.inflate(R.layout.activity_user_order_listitem, null);   //����list_item.xml�����ļ�����ͼ
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
