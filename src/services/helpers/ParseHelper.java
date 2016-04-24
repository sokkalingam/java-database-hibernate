package services.helpers;

public class ParseHelper {
	
	public static Double getDouble(String str) {
		try {
			return Double.valueOf(str);
		} catch(Exception e) {
			return null;
		}
	}
	
	public static Integer getInteger(String str) {
		try {
			return Integer.valueOf(str);
		} catch(Exception e) {
			return null;
		}
	}

}
