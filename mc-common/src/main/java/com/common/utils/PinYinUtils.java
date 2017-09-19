 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

import org.apache.commons.lang.StringUtils;

public class PinYinUtils {

    /**
     * 
     * 获取全拼(多音字只取首个读音) <br/> 
     * getPinyin <br/> 
     * @param src
     * @param partition 分割符
     * @return  String <br/>
     */
	public static String getPinyin(String src,String partition) {
		String result=null;
		try {
			if (src != null && !src.trim().equalsIgnoreCase("")) {
				char[] srcChar;
				srcChar = src.toCharArray();
				// 汉语拼音格式输出类
				HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();
				// 输出设置，大小写，音标方式等
				hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
				hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
				hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
				String[][] temp = new String[src.length()][];
				for (int i = 0; i < srcChar.length; i++) {
					char c = srcChar[i];
					if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")) {
						temp[i] = PinyinHelper.toHanyuPinyinStringArray(
									srcChar[i], hanYuPinOutputFormat);
					} else {
						temp[i] = new String[] {String.valueOf(srcChar[i]).toUpperCase()};
					}
				}
				for(String[] str:temp){
					if(StringUtils.isNotBlank(result)){
						result=result+partition+str[0];
					}else{
						result=str[0];
					}
				}
				
				return result;
			}
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
	}

    public static String getPinyin(String src){
	    return getPinyin(src,"");
    }

    public static String firstLetter(String src){
    	String str="";      
        String str0 = getPinyin(src,",");
        if(StringUtils.isNotBlank(str0)){
    	String [] s = str0.split(",");
    		for(String ts:s){
    			ts =ts.substring(0, 1);
    			str=str+ts;
    		}
    	}
    	return str.toLowerCase();
    }
    
	public static void main(String[] args) {
		String str = "凯越";
		System.out.println(getPinyin(str));
	}
}
