package ${package}.core.util;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.StringUtils;

public class MaskingString {
    private static final Logger LOGGER = LoggerFactory.getLogger(MaskingString.class);
    private static final String PHONE_NUM_PATTERN = "(01[016789])(\\d{3,4})(\\d{4})";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

       /**
        * 이메일이든, 휴대폰번호든 각 포맷에 맞게 마스킹된 결과값 리턴해주는 함수
        * 포맷이 맞지 않을 경우 인풋으로 들어온 값 그대로 리턴
        *
        * @param targetString
        * @return maskedId
        */
       public static String getMaskedString(String targetString) {
          if (isEmail(targetString)) {
             return getMaskedEmail(targetString);
          } else if (isPhoneNum(targetString)) {
             return getMaskedPhoneNum(targetString);
          }
          return getMaskedFull(targetString);
       }

       public static String getMaskedString(String targetString, String format) {
           if(targetString==null || targetString.length()==0){
               return "";
           }else{
               targetString = targetString.replaceAll("\\(", "");
               targetString = targetString.replaceAll("\\)", "");
           }

           if("email".equals(format)){
               return getMaskedEmail(targetString);
           }else if("phone".equals(format)){
               return getMaskedPhoneNum(targetString);
           }else if("name".equals(format)){
               return getMaskedName(targetString);
           }else if("id".equals(format)){
               return getMaskedId(targetString);
           }else if("credit".equals(format)){
               // TODO 수정해야함
               return getMaskedFull(targetString);
           }else if("address".equals(format)){
               return getMaskedAddress(targetString);
           }else if("account".equals(format)){
               return getMaskedBankAccount(targetString);
           }
           return getMaskedFull(targetString);
        }

       /**
        * 이메일 포맷 Validator
        * @param str
        * @return isValidEmailFormat
        */
       private static boolean isEmail(final String str) {
          return isValid(EMAIL_PATTERN, str);
       }

       /**
        * 휴대폰 번호 포맷 Validator
        * @param str
        * @return isValidCellPhoneNumFormat
        */
       private static boolean isPhoneNum(final String str) {
          return isValid(PHONE_NUM_PATTERN, str);
       }

       /**
        * 문자열이 정규식에 맞는 포맷인지 체크
        * @param regex
        * @param target
        * @return isValid
        */
       private static boolean isValid(final String regex, final String target) {
          Matcher matcher = Pattern.compile(regex).matcher(target);
          return matcher.matches();
       }

       /**
        * 이메일 주소 마스킹 처리
        * @param email
        * @return maskedEmailAddress
        */
       private static String getMaskedEmail(String email) {
          /*
          * 요구되는 메일 포맷
          * {userId}@domain.com
          * */
          String regex = "\\b(\\S+)+@(\\S+.\\S+)";
          Matcher matcher = Pattern.compile(regex).matcher(email);
          if (matcher.find()) {
             String id = matcher.group(1); // 마스킹 처리할 부분인 userId
             /*
             * userId의 길이를 기준으로 세글자 초과인 경우 뒤 세자리를 마스킹 처리하고,
             * 세글자인 경우 뒤 두글자만 마스킹,
             * 세글자 미만인 경우 모두 마스킹 처리
             */
             int length = id.length();
             if (length < 3) {
                char[] c = new char[length];
                Arrays.fill(c, '*');
                return email.replace(id, String.valueOf(c));
             } else if (length == 3) {
                return email.replaceAll("\\b(\\S+)[^@][^@]+@(\\S+)", "$1**@$2");
             } else {
                return email.replaceAll("\\b(\\S+)[^@][^@][^@]+@(\\S+)", "$1***@$2");
             }
          }
          return email;
       }

       /**
        * 휴대폰 번호 마스킹 처리
        * @param phoneNum
        * @return maskedCellPhoneNumber
        */
       private static String getMaskedPhoneNum(String phoneNum) {
          /*
          * 02-****-2580 / 010-****-3494
          * */
          String regex = ""; // 9-10 자리
          if(phoneNum.length()==7) regex = "(\\d{3})\\d{4}$";
          else if(phoneNum.length()==8) regex = "(\\d{2})(\\d{3,4})\\d{4}$";
          else if(phoneNum.length()==9 || phoneNum.length()==10) regex = "(\\d{2,3})(\\d{3,4})\\d{4}$";
          else if(phoneNum.length()>10) regex = "(\\d{3})(\\d{4})\\d{4}+";
          Matcher matcher = Pattern.compile(regex).matcher(phoneNum);
          if (!StringUtils.isNullOrEmpty(regex) && matcher.find()) {
              String firstText = matcher.group(1); 
              String replaceTarget = matcher.group(2);
              char[] c = new char[replaceTarget.length()];
              Arrays.fill(c, '*');
              String remakeText = firstText+String.valueOf(c)+(phoneNum.substring(firstText.length()+replaceTarget.length()));
              return remakeText;
          }
          return phoneNum;
       }
       
       /**
        * 이름 마스킹 처리
        * @param name
        * @return 
        */
       private static String getMaskedName(String name) {
          /*
          * 첫글자와 마지막 글자만 표시
          * */
           if(name==null) return "";
           else if(name.length()>2){
               String replaceString = String.format("%0"+((name.length()-2))+"d", 0);
               replaceString = replaceString.replaceAll("0", "*");
               name = name.replaceAll(name.substring(1,(name.length()-1)), replaceString);
           }else{
               name = name.substring(0,1)+"*";
           }
           return name;
       }
       
       /**
        * 아이디 마스킹(앞에 3글자만 보임)
        * @param targetString
        * @return 
        */
       private static String getMaskedId(String targetString) {
          /*
          * 첫글자와 마지막 글자만 표시
          * */
           if(targetString==null) return "";
           else if(targetString.length()>3){
               String replaceString = String.format("%0"+((targetString.length()-3))+"d", 0);
               replaceString = replaceString.replaceAll("0", "*");
               targetString = targetString.replaceAll(targetString.substring(3), replaceString);
           }else{
               targetString = targetString.substring(0,1)+"*";
           }
           return targetString;
       }

       /**
        * 주소 마스킹 처리 : space를 기준으로 시(도),구(군)까지만 두고 나머지는 *처리
        * @param targetString
        * @return 
        */
       private static String getMaskedAddress(String targetString) {
          /*
          * space를 기준으로 시(도),구(군)까지만 두고 나머지는 *처리
          * */
           StringBuffer maskedSb = new StringBuffer();
           if(!StringUtils.isNullOrEmpty(targetString)){
               String[] stringList = targetString.split(" ");
               if(stringList!=null && stringList.length>0){
                   for(int i=0; i<stringList.length; i++){
                       if(i<2){
                           maskedSb.append(stringList[i]);
                       }else{
                           maskedSb.append(getMaskedFull(stringList[i]));
                       }
                       maskedSb.append(" ");
                   }
               }
           }
           return maskedSb.toString();
       }
       
       /**
        * 계좌번호 마스킹처리 : 계좌번호 첫 3자리 노출 
        * @param targetString
        * @return 
        */
       private static String getMaskedBankAccount(String targetString) {
          /*
          * 계좌번호 첫 3자리 노출 
          * */
           if(targetString==null) return "";
           else if(targetString.length()>4){
               String replaceString = String.format("%0"+((targetString.length()-3))+"d", 0);
               replaceString = replaceString.replaceAll("0", "*");
               targetString = targetString.replaceAll(targetString.substring(3), replaceString);
           }else{
               targetString = targetString.substring(0,1)+"*";
           }

           return targetString;
       }

       /**
        * 전체 마스킹 처리(상세주소에서 사용예정)
        * @param targetString
        * @return 
        */
       private static String getMaskedFull(String targetString) {
           if(targetString==null || targetString.length()==0) return "";
           else {
               String replaceString = String.format("%0"+(targetString.length())+"d", 0);
               replaceString = replaceString.replaceAll("0", "*");
               targetString = targetString.replaceAll(targetString, replaceString);
           }
           return targetString;
       }

       public static void main(String args[]){
//           Date currentTime = new Date();
//           java.text.SimpleDateFormat simpleFormat = new java.text.SimpleDateFormat(
//                   "yyyy-MM-dd");
//           java.util.Date reservDateTime = null;
//           try {
//               reservDateTime = simpleFormat.parse("2016-09-01");
//           } catch (ParseException e) {
//               // TODO Auto-generated catch block
//               e.printStackTrace();
//           }
//           Calendar cal = Calendar.getInstance();
//           cal.setTime(reservDateTime);
//           cal.add(Calendar.MINUTE, -90); // 90분 전
//           Calendar nowCal = Calendar.getInstance();
//           nowCal.setTime(currentTime);
//           System.out.println(simpleFormat.format(cal.getTime()));
//           System.out.println(currentTime.before(reservDateTime));
           
       }
}
