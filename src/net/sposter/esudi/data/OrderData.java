package net.sposter.esudi.data;

import java.util.Date;

public class OrderData {
	public static final int ORDER_NEW = 0;
	public static final int ORDER_QIANDAN = 1;
	public static final int ORDER_CANCEL = 8;
	
	
	public int OrderID;
	public int OrderState;
	public String OrderCreateDate;
	public String OrderCreateAddress;
	public String OrderDistince;
}
