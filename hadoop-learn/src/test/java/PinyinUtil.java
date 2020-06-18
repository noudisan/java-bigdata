import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.Scanner;

public class PinyinUtil {
    public static void main(String[] args) throws Exception {
	 /*  String[] pyStrs = PinyinHelper.toHanyuPinyinStringArray('重');
	    for (String s : pyStrs) {
	        System.out.println(s);
	    }
	    //-------------------指定格式转换----------------------------
	    HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
	    // UPPERCASE：大写  (ZHONG)
	    // LOWERCASE：小写  (zhong)
	    format.setCaseType(HanyuPinyinCaseType.UPPERCASE);//输出大写
	    // WITHOUT_TONE：无音标  (zhong)
	    // WITH_TONE_NUMBER：1-4数字表示音标  (zhong4)
	    // WITH_TONE_MARK：直接用音标符（必须WITH_U_UNICODE否则异常）  (zhòng)
	    //format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
	    // WITH_V：用v表示ü  (nv)
	    // WITH_U_AND_COLON：用"u:"表示ü  (nu:)
	    // WITH_U_UNICODE：直接用ü (nü)
	   // format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
	    String[] hao = PinyinHelper.toHanyuPinyinStringArray('好', format);
	    for (String s : hao) {
	        System.out.println(s);
	    }*/


        //String pinyin = convertToPinyin('重');
        //String pinyin = ToPinyin("武汉");
//        char charAt = ToFirstChar("武汉").toUpperCase().charAt(0);
//        System.out.println(ToPinyin("渣渣禾"));
        Scanner sc = new Scanner(System.in);
        String str = "";
        System.out.println("😎suisuipin开始了，开始了，四字成语准备。");
        str = sc.next();
        while (str.length() < 4) {
            System.out.println("建议重新输入");
            str = sc.next();
        }
        String pinyin = ToPinyin(str.charAt(str.length() - 1) + "");
        String pinyin1 = "";
        String chenyu = "";
        int count = 0;
        while (true) {
            System.out.println("尾字" + pinyin);
            System.out.println("接龙🤓");
            str = sc.next();
            if (str.length() < 4) {
                System.out.println("😖建议重新输入");
                continue;
            }
            if (str.length() > 4 || (str.charAt(0) == str.charAt(1)
                    && str.charAt(0) == str.charAt(2)) || (str.charAt(3) == str.charAt(1) && str.charAt(3) == str.charAt(2))) {
                System.out.println("🥶成语 懂？");
                continue;
            }
            pinyin1 = ToPinyin(str.charAt(0) + "");
            if (pinyin1.equals(pinyin)) {
                System.out.println("😏完美，继续---");
                pinyin = ToPinyin(str.charAt(str.length() - 1) + "");
                count++;
            } else {
                System.out.println("垃圾，会不会啊😡！！！");
                return;
            }
            if (count > 10) {
                System.out.println("可以呢小伙子🤗，你居然接了 " + count + " 次！");
            }

        }
    }

    public static String convertToPinyin(char hanzi) throws Exception {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        String[] pyStrs = PinyinHelper.toHanyuPinyinStringArray(hanzi, format);
        return pyStrs[0];

    }

    /**
     * 获取字符串拼音的第一个字母
     *
     * @param chinese
     * @return
     */
    public static String ToFirstChar(String chinese) {
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();  //转为单个字符
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0].charAt(0);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }

    /**
     * 汉字转为拼音
     *
     * @param chinese
     * @return
     */
    public static String ToPinyin(String chinese) {
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }
}