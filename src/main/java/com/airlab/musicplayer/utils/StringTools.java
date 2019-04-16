package com.airlab.musicplayer.utils;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Preconditions;

public class StringTools {
    
	private static final String hexString = "0123456789ABCDEF";
    private static Pattern urlPattern = Pattern.compile("^(http|https|ftp|rtsp|igmp|file|rtspt|rtspu)(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)(/)?$");
    private static Pattern atUserPattern=Pattern.compile("@([\\S]+)");
    private static Pattern responseUserPatter = Pattern.compile("【引文】\\s([\\S]+)");
	private static final Charset CS_UTF8 = Charset.forName("UTF-8");
	private static int modNum = 36;
	private static char[] encodeCharList = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
	
	public static String decodeUTF8(byte[] utf8Bytes) {
		ByteBuffer bb = ByteBuffer.wrap(utf8Bytes);
		String str = CS_UTF8.decode(bb).toString();
		return str;
	}
	
	public static String signature(String inputSS){
		Preconditions.checkArgument(StringUtils.isNotEmpty(inputSS), "输入字符串不能为空");
		
		String upperSS =  inputSS.replace("-", "").toUpperCase();
		Preconditions.checkArgument(StringUtils.isNotEmpty(upperSS), "输入字符串不能全为【-】");
		
		String reverseSS= new StringBuffer(upperSS).reverse().toString();
		Preconditions.checkArgument(upperSS.length() == reverseSS.length(), "内部错误，倒序前后长度不一致");

		char[] charArray1 = upperSS.toCharArray();	
		char[] charArray2 = reverseSS.toCharArray();	
		
		
		int charLength = charArray1.length;
		char[] returnArray = new char[charLength];
		
		for(int i = 0; i< charLength; i++){
			int index = ((int)charArray1[i]+(int)charArray2[i]) % modNum;
			returnArray[i] = encodeCharList[index];
		}
	
		return new String(returnArray);
	}

	public static <T> List<T> transferArrayToList(T[] array){
		if(array == null || array.length == 0){
			return null;
		}
		List<T> list = new ArrayList<T>();
		Collections.addAll(list, array);
		return list;
	}
	
	public static byte[] encodeUTF8(String str) {
		CharBuffer cb= CharBuffer.wrap(str);
		byte[] utf8Bytes = CS_UTF8.encode(cb).array();
		int zeroIndex = 0;
		while (utf8Bytes[zeroIndex] != 0) zeroIndex++;
		byte[] res = new byte[zeroIndex];
		System.arraycopy(utf8Bytes, 0, res, 0, zeroIndex);
		return res;
	}
    
    public static Set<String> extractReminderNameFromText(String msg){
		Matcher m = atUserPattern.matcher(msg); 
		Set<String> resultSet=new HashSet<String>();
		while(m.find()){
			resultSet.add(m.group(1));
		}
		return resultSet;
    }
    
    public static Set<String> extractReminderNameFromResponseText(String msg){
		Matcher m = responseUserPatter.matcher(msg); 
		Set<String> resultSet=new HashSet<String>();
		while(m.find()){
			resultSet.add(m.group(1));
		}
		return resultSet;
    }
    
    public static String formatDeviceToken(String orig){
    	
    	String lowerToken=orig.toLowerCase();
    	StringBuilder sb=new StringBuilder();
    	int i=0;
    	for(char c:lowerToken.toCharArray()){
    		i++;
    		sb.append(c);
    		if(i%8==0 && i<64){
    			sb.append(" ");
    		}
    	}
    	return sb.toString();
    }
	/**
     * null,过滤前后空格后，length==0 return true
     * 
     * @param str
     * @return
     */
    public static boolean isNullOrWhiteSpace(String str) {
        if (str == null) {
            return true;
        }
        if (str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static String toHexStr(byte[] bytes) {
        if (bytes == null)
        {
            return null;
        }
    	
        // 根据默认编码获取字节数组
        StringBuilder sb = new StringBuilder(bytes.length * 3);
        // 将字节数组中每个字节拆解成2位16进制整数
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
            sb.append(" ");
        }
        return sb.toString();
    }

    public static String toHexStr(byte[] bytes, int count) {
        if (bytes == null)
        {
            return null;
        }

        // 根据默认编码获取字节数组
        StringBuilder sb = new StringBuilder(bytes.length * 3);
        // 将字节数组中每个字节拆解成2位16进制整数
        for (int i = 0; i < bytes.length && i < count; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
            sb.append(" ");
        }
        if (count < bytes.length)
        {
            sb.append("...");
        }
        return sb.toString();
    }
    
	/**
	 * 根据参数字符串生成用于 like 查询的 base64 字符串数组，每个字符串前后添加了'%'通配符。
	 * 根据 base64 算法考虑三种情况：
	 * 1、前面不填充英文字符，要去除填充的等号，若不是4的整数倍，则去除末尾一个字符
	 * 2、前面填充1个英文字符，结果中去除前2个和末尾一个字符
	 * 3、前面填充2个英文字符，结果中去除前3个和末尾一个字符
	 * @param str
	 * @return
	 */
	public static String[] getBase64LikeStrings(String str) {
		if (isNullOrWhiteSpace(str)) {
			return null;
		}
		
		String[] strArray = new String[3];
		strArray[0] = new String(Base64.encodeBase64(str.getBytes()));
		// remove ending '='s
		for (int i = strArray[0].length(); i > 0; i--) {
			if (strArray[0].charAt(i-1) != '=') {
				strArray[0] = strArray[0].substring(0, i);
				break;
			}
		}
		int len = strArray[0].length();
		if (len % 4 != 0) {
			strArray[0] = strArray[0].substring(0, len - 1);
		}
		
		strArray[1] = new String(Base64.encodeBase64(("0" + str).getBytes())).substring(2, 1 + len);
		strArray[2] = new String(Base64.encodeBase64(("00" + str).getBytes())).substring(3, 2 + len);
		
		for (int i = 0; i < strArray.length; i++) {
			strArray[i] = "%" + strArray[i] + "%";
		}

		return strArray;
	}
	
	/**
	 * 是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str){
		Pattern pattern = Pattern.compile("^[0123456789]*$");
        Matcher matcher = pattern.matcher(str);
        
        if (matcher.matches()) {
            return true;
        } else {
        	return false;
        }
	}
	
	/**
	 * 是否为IMSI
	 * @param str
	 * @return
	 */
	public static boolean isIMSI(String str){
		if ( str == null || str.length() != 15 ) {
			return false;
		}
		
		return isNumber(str);
	}
	
	public static String trim(String text,int length) {
		if(text==null)
			return null;
		if (text.length() > length) {
			text = text.substring(0, length) + "...";
		}
		return text;
	}
	
	/**
	 * 转半角
	 * 
	 * 全角空格为12288，半角空格为32
　　	 * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
	 * 
	 * @param inputStr
	 * @return
	 */
	public static String toDBCcase(String inputStr){
		if ( inputStr == null ) {
			return inputStr;
		}
		char[] c = inputStr.toCharArray();
		for (int i = 0; i< c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i]> 65280&& c[i]< 65375){
				c[i] = (char) (c[i] - 65248);	
			}			
		}
		return new String(c);
	}
	
	/**
	 * 转全角
	 * 
	 * 全角空格为12288，半角空格为32
　　   * 其他字符半角(33-126)与考试.大提示全角(65281-65374)的对应关系是：均相差65248
	 *
	 * @param halfwidthStr
	 * @return
	 */
	public static String toSBCcase(String inputStr){
		if ( inputStr == null ) {
			return inputStr;
		}
		char[] c = inputStr.toCharArray();
		for (int i = 0; i< c.length; i++) {
			if (c[i] == 32) {
				c[i] = (char) 12288;
				continue;
			}
			if (c[i]< 127){
				c[i] = (char) (c[i] + 65248);
			}
		}
		return new String(c);
	}
	
	/**
	 * 获取UUID
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	/**
	 * 检查是否是URL
	 * @param url
	 * @return
	 */
	public static boolean isUrl(String url){
		if (StringUtils.isNotEmpty(url)) {
			Matcher m = urlPattern.matcher(url);
			return m.matches();
		} else {
			return false;
		}
	}
	
    /**
   * 左位补零
   * @param str
   * @param l 返回长度
   * @param p 左位补的字符
   */
  public static String lpr(String str, int l, char p) {
      if (str == null) {
          return null;
      }
      int length = str.length();
      StringBuffer buffer = new StringBuffer();
      for (int j = 0; j < l - length; j++) {
          buffer.append(p);
      }
      buffer.append(str);

      return buffer.toString();
  }
  
  /**
   * 把unicode转化为汉字
   * @param unicode
   * @return
   */
  public static String UnicodeToString(String unicode) {
      Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");    
      Matcher matcher = pattern.matcher(unicode);
      char ch;
      while (matcher.find()) {
          ch = (char) Integer.parseInt(matcher.group(2), 16);
          unicode = unicode.replace(matcher.group(1), ch + "");    
      }
      return unicode;
  }
  
  /**
   * 版本比对
   * @param version1
   * @param version2
   * @return
   * @throws Exception
   */
  public static int compareVersion(String version1, String version2) throws Exception {
      if (version1 == null || version2 == null) {
          throw new Exception("compareVersion error:illegal params.");
      }
      String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用.；
      String[] versionArray2 = version2.split("\\.");
      int idx = 0;
      int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
      int diff = 0;
      while (idx < minLength
              && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
              && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
          ++idx;
      }
      //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
      diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
      return diff;
  }
  
  
	//第一个是行，第二个是列
	public static List<Map<String, Object>> transferList(List<String> inputData, String xCoordination, String yCoordination, String zCoordination){
		Preconditions.checkArgument(!CollectionUtils.isEmpty(inputData), "输入不能为空");
		Preconditions.checkArgument(!StringUtils.isEmpty(xCoordination), "x轴参数不能为空");
		Preconditions.checkArgument(!StringUtils.isEmpty(yCoordination), "y轴参数不能为空");
		Preconditions.checkArgument(!StringUtils.isEmpty(zCoordination), "z轴参数不能为空");

		String row = yCoordination;
		String col = xCoordination;
		
		List<String> rowList = new ArrayList<String>();
		List<String> colList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();
		Map<String, String> originMap = new HashMap<String, String>();
		for(String ss: inputData){

			String rowValue = null;
			String colValue = null;
			String value = null;
			
			Pattern rowPattern=Pattern.compile(row+"\\\\*\"?\\s[:|=]\\s\\\\*\"?([^\"|^\\\\]+)\\\\*\"");
			Matcher m1 = rowPattern.matcher(ss); 
			while(m1.find()){
				rowValue = m1.group(1);
				rowList.add(m1.group(1));
			}
			
			
			Pattern colPattern=Pattern.compile(col+"\\\\*\"?\\s[:|=]\\s\\\\*\"?([^\"|^\\\\]+)\\\\*\"");
			Matcher m2 = colPattern.matcher(ss); 
			while(m2.find()){
				colValue = m2.group(1);
				colList.add(m2.group(1));
			}
			
			Pattern valuePattern=Pattern.compile(zCoordination+"\\\\*\"?\\s*[:|=]\\s*([\\d|.]+)");
			Matcher m3 = valuePattern.matcher(ss); 
			while(m3.find()){
				value = m3.group(1);
				valueList.add(m3.group(1));
			}
			
			originMap.put(rowValue+","+colValue, value);
			
		}

		HashSet<String> rowSet = new HashSet<String>(rowList);
		HashSet<String> colSet = new HashSet<String>(colList);

		int rowSize =  rowSet.size();
		int colSize = colSet.size();
		
		rowList = new ArrayList(rowSet);
		colList = new ArrayList(colSet);
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		for(int i=0; i<colSize; i++){
			String colName = colList.get(i);
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("", colName);
			for(int j=0; j<rowSize; j++){
				String roleName = rowList.get(j);
				String key = roleName + "," + colName;
				String vv = originMap.get(key);
				if(StringUtils.isEmpty(vv)){
					vv = "0";
				}
				rowMap.put(roleName, Long.valueOf(vv));	
			}
			result.add(rowMap);
		}
	
		return result;
	}
	
	public static String getVersion(String version,String newVersion){
		try {
			int diff = compareVersion(version, newVersion);
			if(diff<0){
				version = newVersion;
			}else{
				version = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return version;
	}
}
