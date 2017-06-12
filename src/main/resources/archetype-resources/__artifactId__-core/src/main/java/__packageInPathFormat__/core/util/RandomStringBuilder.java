package ${package}.core.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/** 사용법

// 16자리 랜덤 문자열 생성
String randStr = new RandomStringBuilder().setLength(16).build();

// 숫자를 제외한 길이 16의 랜덤 문자열 생성.
randStr =  new RandomStringBuilder().
           putExcludedChar(RandomStringBuilder.NUMBER).
           setLength(16).build();
     
// 알파벳으로 이루어진 길의 16의 랜덤 문자열 생성.
randStr =  new RandomStringBuilder().
           putLimitedChar(RandomStringBuilder.ALPHABET).
           setLength(16).build();
     
// 대문자와 특수문자로 이루어져있고, ?.,&$/\"' 를 제외한 길이 16의 랜덤 문자열 생성. 
randStr =  new RandomStringBuilder().
           putLimitedChar(RandomStringBuilder.ALPHABET_UPPER_CASE).
           putLimitedChar(RandomStringBuilder.SPECIAL).
           putExcludedChar("?.,&$/\\\"'").
           setLength(16).build();
 * */

public class RandomStringBuilder {
    
    public static final String NUMBER = "0123456789";
    public static final String ALPHABET_LOWER_CASE = "abcdefghijkmnlopqrstuvwxyz";
    public static final String ALPHABET_UPPER_CASE = "ABCDEFGHIJKMNLOPQRSTUVWXYZ";
    public static final String ALPHABET = ALPHABET_LOWER_CASE + ALPHABET_UPPER_CASE;
    public static final String SPECIAL = "~!@#$%^&*()_+{}|\\\"`;:'<>?,./=-[]";
     
    private HashSet<Character> mExcludedCharSet = new HashSet<Character>(); 
    private ArrayList<Character> mLimitCharList = new ArrayList<Character>();
             
    int mLength = 32;
     
    public String build() {
        return generateRandomString(mLength);
    }
     
    public RandomStringBuilder setLength(int length) {
        mLength = length;
        return this;
    }
     
    public int getLength() {
        return mLength;
    }
     
    public RandomStringBuilder putExcludedChar(char excluded) {
        mExcludedCharSet.add(excluded);
        return this;
    }
     
    public RandomStringBuilder putExcludedChar(char[] excludedList) {
        for(char excluded : excludedList) 
                putExcludedChar(excluded);
        return this;
    }
     
    public RandomStringBuilder putExcludedChar(String excluded) { 
                putExcludedChar(excluded.toCharArray());
        return this;
    }
     
    public RandomStringBuilder putLimitedChar(char limited) {
        mLimitCharList.add(limited);
        return this;
    }
     
    public RandomStringBuilder putLimitedChar(char[] limitedList) {
        for(char limited : limitedList) 
                putLimitedChar(limited);
        return this;
    }
     
    public RandomStringBuilder putLimitedChar(String limited) {
        putLimitedChar(limited.toCharArray());
        return this;
    }
     
    public boolean removeExcluded(char excluded) {
        return mExcludedCharSet.remove(excluded);
    }
     
    public boolean removeLimitedChar(char limited) {
        return mLimitCharList.remove((Character)limited);
    }
     
    public void clearExcluded() {
        mExcludedCharSet.clear();
    }
    public void clearLimited() {
        mLimitCharList.clear();
    }
     
    /**
     * 랜덤 문자열 생성.
     * @param length 문자열 길이
     * @return 랜덤 문자열
     */
    public String generateRandomString(int length) {
        boolean runExcludeChar = !isExcludedCharInLimitedChar();            
        StringBuffer strBuffer = new StringBuffer(length);
        Random rand = new Random(System.nanoTime());
        for(int i = 0; i < length; ++i ) {
            char randomChar = makeChar(rand);
            if(runExcludeChar)
                randomChar = excludeChar(rand, randomChar);
            strBuffer.append(randomChar);
        }
        return strBuffer.toString();
    }
     
    private boolean isExcludedCharInLimitedChar() {
        return mExcludedCharSet.containsAll(mLimitCharList);
    }
     
    private char excludeChar(Random rand, char arg) {
        while(mExcludedCharSet.contains(arg)) {
            arg = makeChar(rand);
        }
        return arg;
    }
    private char makeChar(Random rand) {
        if(mLimitCharList.isEmpty())
            return (char)(rand.nextInt(94) + 33);
        else
            return mLimitCharList.get(rand.nextInt(mLimitCharList.size()));
    }
}
