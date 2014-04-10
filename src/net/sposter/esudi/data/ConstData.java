package net.sposter.esudi.data;

public class ConstData {
  public static final String PREFERENCE_FILE_NAME = "user_info";
  public static final String USER_PHONE = "user_phone";
  public static final String USER_STATE = "user_state";
  public static final int USER_LOGIN = 1;
  public static final int USER_LOGOUT = 0;
  
  public static final String USER_TYPE = "user_type";
  public static final int USER_NONE = 0;
  public static final int USER_USER = 1;
  public static final int USER_SUDIYUAN = 2;
  
  public static final String GET_SUDIYUAN_LOCATION_URL = "http://115.29.177.58/webapi/get_couriers";
  public static final String GET_SHOT_MESSAGE_URL = "http://115.29.177.58/webapi/get_verification_code";
  public static final String GET_LOGIN_BY_SHORTMSG_URL = "http://115.29.177.58/webapi/login_by_verification_code";
}
