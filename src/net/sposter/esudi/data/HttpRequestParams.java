package net.sposter.esudi.data;

import com.litesuits.http.request.param.HttpParam;

public class HttpRequestParams {
	public static class BaseParam implements HttpParam{
		public int st_auth_version = 1;
		public int st_auth_rand = 1;
		public String st_auth_signature = "8df002b25c8170b88d254d63adbc08c9";
	}
	
	public static class MyLocation extends BaseParam {
		private double longitude=0.0;
		private double latitude=0.0;
		

		public MyLocation(double latitude, double longitude) {
			this.longitude = longitude;
			this.latitude = latitude;
		}
	}
	
	public static class GetShortMsg extends BaseParam {
		private String mobile=null;

		public GetShortMsg(String mobile) {
			this.mobile = mobile;
		}
	}
	
	public static class LogInMsg extends BaseParam {
		private String mobile=null;
		private String short_msg_code=null;
		public LogInMsg(String mobile, String short_msg_code) {
			this.mobile = mobile;
			this.short_msg_code = short_msg_code;
		}
	}
}
