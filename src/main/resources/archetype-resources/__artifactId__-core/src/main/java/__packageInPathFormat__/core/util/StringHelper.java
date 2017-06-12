package ${package}.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
/**
 * TODO String 관련 Util 클래스
 *
 * @author mike Ryu, BD Apis
 * @date 2015. 3. 09
 * @version 1.0
 */
public class StringHelper {
	String input;
	String delimiter;
	
	final static int MAX_LOOP = 100000;
	
	
	public StringHelper(String input, String delimiter) {
		super();
		this.input = input;
		this.delimiter = delimiter;
	}
	
	public static Boolean isEmpty(String in){
		Boolean result = true;
		if(in !=null && in.length()>0)
			result = false;
		return result;
	}
	public static Boolean isEmpty(List<String> in){
		Boolean result = true;
		if(in !=null && in.size()>0)
			result = false;
		return result;
	}
	
	public static List<String> parseToken(String in, String delims){
		List<String> result = new ArrayList<String>();
		int cnt =0;
		StringTokenizer st = new StringTokenizer(in, delims);
		while(st.hasMoreTokens()){
			String res = st.nextToken();
			if(res.length()>0){
				result.add(res);
				cnt ++;
			}
			if(cnt>MAX_LOOP)
				break;
		}
		return result;
	}
	public static String checkLastString(String in, String delims){
		if(!in.substring(in.length() - 1).equals(delims)){
			in += delims;
        }
		return in;
	}
	
	public static String getFileName(String fullFileName){
		int cnt =0;
		String result = "";
		String fileName = "";
		StringTokenizer st = new StringTokenizer(fullFileName, "/");
		while(st.hasMoreTokens()){
			String res = st.nextToken();
			if(!st.hasMoreTokens()){
				fileName = res;
			}
			cnt++;
			if(cnt>MAX_LOOP)
				break;
		}
		cnt = 0;
		StringTokenizer st1 = new StringTokenizer(fileName, ".");
		while(st1.hasMoreTokens()){
			String res = st1.nextToken();
			if(st1.hasMoreTokens()){
				if(cnt>0) //최초 문자열을 제외한 경우 .를 붙혀준다.
					result += ".";
				result += res;
			}
			cnt++;
			if(cnt>MAX_LOOP)
				break;
		}
		return result;
	}
	
	public static String getExtension(String fullFileName){
		String delims = ".";
		int cnt =0;
		String result = "";
		StringTokenizer st = new StringTokenizer(fullFileName, delims);
		while(st.hasMoreTokens()){
			String res = st.nextToken();
			if(!st.hasMoreTokens()){
				result = res;
			}
			if(cnt>MAX_LOOP)
				break;
		}
		return result;
	}
	public static String getCompositeString(List<String> list, String delims){
	    String result = "";
	    Iterator<String> itr = list.iterator();
	    while(itr.hasNext()){
	        result += itr.next();
	        if(itr.hasNext())result += delims;
	    }
	    return result;
	}
	
	public static String getString(InputStream inputStream){
        String result = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
 
        try {
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
 
                }
            }
        }
 
        result = stringBuilder.toString();
        return result;
    }
	/**
     * <p>
     * 기준 문자열에 포함된 모든 대상 문자(char)를 제거한다.
     * </p>
     * 
     * <pre>
     * StringUtil.remove(null, *)       = null
     * StringUtil.remove("", *)         = ""
     * StringUtil.remove("queued", 'u') = "qeed"
     * StringUtil.remove("queued", 'z') = "queued"
     * </pre>
     * 
     * @param str
     *            입력받는 기준 문자열
     * @param remove
     *            입력받는 문자열에서 제거할 대상 문자열
     * @return 제거대상 문자열이 제거된 입력문자열. 입력문자열이 null인 경우 출력문자열은 null
     */
    public static String remove(String str, char remove) {
        if (isEmpty(str) || str.indexOf(remove) == -1) {
            return str;
        }
        char[] chars = str.toCharArray();
        int pos = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != remove) {
                chars[pos++] = chars[i];
            }
        }
        return new String(chars, 0, pos);
    }
    
    /**
     * <p>
     * 문자열 내부의 콤마 character(,)를 모두 제거한다.
     * </p>
     * 
     * <pre>
     * StringUtil.removeCommaChar(null)       = null
     * StringUtil.removeCommaChar("")         = ""
     * StringUtil.removeCommaChar("asdfg,qweqe") = "asdfgqweqe"
     * </pre>
     * 
     * @param str
     *            입력받는 기준 문자열
     * @return " , "가 제거된 입력문자열 입력문자열이 null인 경우 출력문자열은 null
     */
    public static String removeCommaChar(String str) {
        return remove(str, ',');
    }

    /**
     * <p>
     * 문자열 내부의 마이너스 character(-)를 모두 제거한다.
     * </p>
     * 
     * <pre>
     * StringUtil.removeMinusChar(null)       = null
     * StringUtil.removeMinusChar("")         = ""
     * StringUtil.removeMinusChar("a-sdfg-qweqe") = "asdfgqweqe"
     * </pre>
     * 
     * @param str
     *            입력받는 기준 문자열
     * @return " - "가 제거된 입력문자열 입력문자열이 null인 경우 출력문자열은 null
     */
    public static String removeMinusChar(String str) {
        return remove(str, '-');
    }
    /**
     * Property값으로 자동메일 발송의 변환정보 String을 반환한다.
     * 예)[$name]Ð홍길동æ[$birthday]Ð2010-01-01æ
     *
     * @param Property
     * @return the string
     */
    public static String convertInfoMail(HashMap<String, Object> map) {
        String convertInfo = "";
        Iterator<String> it = map.keySet().iterator();

        while (it.hasNext()) {
            String key = (String) it.next();
            convertInfo += "[$" + key + "]Ð" + map.get(key) + "æ";
        }

        return convertInfo;
    }
    /**
     * Property값으로 자동메일 발송의 변환정보 String을 반환한다.
     * 예)[$name]Ð홍길동æ[$birthday]Ð2010-01-01æ
     *
     * @param Property
     * @return the string
     */
    public static String replaceDataTempate(HashMap<String, Object> map, String template) {
        String convertInfo = "";
        Iterator<String> it = map.keySet().iterator();

        while (it.hasNext()) {
            String key = (String) it.next();
        }

        return convertInfo;
    }
    
    /**
     * 주어진 길이(iLength)만큼 주어진 문자(cPadder)를 strSource의 왼쪽에 붙혀서 보내준다. ex)
     * lpad("abc", 5, '^') ==> "^^abc" lpad("abcdefghi", 5, '^') ==> "abcde"
     * lpad(null, 5, '^') ==> "^^^^^"
     *
     * @param strSource
     * @param iLength
     * @param cPadder
     */
    public static String lpad(String strSource, int iLength, char cPadder) {
        StringBuffer sbBuffer = null;
        if (!isEmpty(strSource)) {
            int iByteSize = getByteSize(strSource);
            if (iByteSize > iLength) {
                return strSource.substring(0, iLength);
            } else if (iByteSize == iLength) {
                return strSource;
            } else {
                int iPadLength = iLength - iByteSize;
                sbBuffer = new StringBuffer();
                for (int j = 0; j < iPadLength; j++) {
                    sbBuffer.append(cPadder);
                }
                sbBuffer.append(strSource);
                return sbBuffer.toString();
            }
        }
        // int iPadLength = iLength;
        sbBuffer = new StringBuffer();
        for (int j = 0; j < iLength; j++) {
            sbBuffer.append(cPadder);
        }
        return sbBuffer.toString();
    }
    
    /**
     * byte size를 가져온다.
     *
     * @param str
     *            String target
     * @return int bytelength
     */
    public static int getByteSize(String str) {
        if (str == null || str.length() == 0)
            return 0;
        byte[] byteArray = null;
        try {
            byteArray = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException ex) {
        }
        if (byteArray == null)
            return 0;
        return byteArray.length;
    }
    
}
