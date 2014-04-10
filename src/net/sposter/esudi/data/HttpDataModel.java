package net.sposter.esudi.data;

import java.util.ArrayList;


public class HttpDataModel {


	public static class Sudiyuan {
		public int couriers_count;
		public ArrayList<SudiyuanInfo> body;

		public static class SudiyuanInfo {
			public int id = -1;
			public String name = null;
			public String mobile = null;
			public double latitude = 0.0;
			public double longitude = 0.0;
		}

		@Override
		public String toString() {
			return "";
		}
	}

}