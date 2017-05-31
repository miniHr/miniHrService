/**
 * @(#) MoneyUtil.java
 * module  : util
 * version : 版本管理系统中的文件版本
 * date    : 2009-4-8
 * name    : 马仁配
 */
package com.miniHr.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;


/**
 * 如果有任何对代码的修改,请按下面的格式注明修改的内容.
 * 
 * 序号 时间 作者 修改内容
 * 
 * 1. 2009-4-8 马仁配 created this class.
 */
public abstract class MoneyUtil {
	/**
	 * 金额单位
	 */
	public static final String Fen = "fen";//分
	public static final String Jiao = "jiao";//角
	public static final String Yuan = "yuan";//元
	public static final String Li = "li";//厘
	public static final String TenthLi = "tenthLi";//十分之一厘
	

	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;

	/**
	 * 解析返回精确到分的整型可读金额.
	 * 
	 * @param amount
	 *            精确到1/10厘.
	 * @return 整型分单位
	 */
	public static String parseReadableAmount(Long amount) {
		try {
			BigDecimal bigDecimal = new BigDecimal(Long.toString(amount));
			return bigDecimal.divide(new BigDecimal("100"), 0,
					BigDecimal.ROUND_HALF_UP).toString();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 解析单位为1/10厘的整型可读金额 成 四舍五入到分的整型可读金额.
	 * 
	 * @param amount 精确到1/10厘.
	 * @return 整型分单位
	 */
	public static Long parseAmountToRoundCent(Long amount) {
		return (new BigDecimal(amount).divide(new BigDecimal("100")))
			.setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
	}

	/**
	 * 从可读的分金额解析返回精确到1/10厘的金额.
	 * 
	 * @param amountStr
	 *            两位小数的分单位
	 * @return 长整型1/10厘
	 */
	public static Long parseFromReadableAmount(String amountStr) {
		try {
			return new Long(new BigDecimal(amountStr).multiply(
					new BigDecimal("100")).longValue());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 从厘金额解析返回精确到1/10厘的金额.
	 * 
	 * @param amountStr
	 *            厘单位
	 * @return 长整型1/10厘
	 */
	public static Long parseFromLiAmount(String amountStr) {
		try {
			return new Long(new BigDecimal(amountStr).multiply(
					new BigDecimal("10")).longValue());
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 解析金额(从十分之一厘到带有两位小数的元单位)
	 */
	public static String parseFromReadableAmountToRMB(String amount) {
		try {
			BigDecimal bigDecimal = new BigDecimal(amount);
			return bigDecimal.divide(new BigDecimal("10000"), 2,
					BigDecimal.ROUND_HALF_UP).toString();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 解析金额(从十分之一厘到带有两位小数的元单位)（不带舍入的解析，比如：数据库存的是15555，解析后：1.55）
	 */
	public static String parseFromReadableAmountToRMBNotRoundOff (String amount) {
		try {
			BigDecimal bigDecimal = new BigDecimal(amount);
			return bigDecimal.divide(new BigDecimal("10000"), 2,
					BigDecimal.ROUND_DOWN).toString();
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 解析金额(从十分之一厘到带有两位小数的元单位)
	 */
	public static BigDecimal parseFromReadableAmountToRMB(Long amount) {
		try {
			BigDecimal bigDecimal = new BigDecimal(amount);
			return bigDecimal.divide(new BigDecimal("10000"), 2,
					BigDecimal.ROUND_HALF_UP);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 解析金额(元单位到十分之一厘)
	 */
	public static String parseFromRMBToReadableAmount(String amount) {
		try {
			Long readableAmount = parseFromRMBToReadableAmountLong(amount);
			
			return readableAmount == null ? null : String.valueOf(readableAmount);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 解析金额(元单位到十分之一厘)
	 * 
	 * @param amount
	 * @return
	 */
	public static Long parseFromRMBToReadableAmountLong(String amount) {
		try {
			return new BigDecimal(amount).multiply(new BigDecimal("10000"))
					.longValue();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 解析金额(元单位到十分之一厘)
	 */
	public static Long parseFromRMBToReadableAmount(BigDecimal amount) {
		try {
			return amount.multiply(new BigDecimal("10000")).setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 解析金额（从分单位到带有两位小数的元单位）
	 */
	public static String parseFromFenAmountToRMB(String amount) {
		try {
			BigDecimal bigDecimal = new BigDecimal(amount);
			return bigDecimal.divide(new BigDecimal("100"), 2,
					BigDecimal.ROUND_HALF_UP).toString();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 解析金额(从带有两位小数的元单位到分)
	 */
	public static String parseFromRMBAmountToFen(String amount) {
		try {
			return String.valueOf(new BigDecimal(amount).multiply(
					new BigDecimal("100")).longValue());
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**
	 * 解析金额(元(US格式)单位到十分之一厘)
	 * @throws ParseException 
	 */
	
	public static String parseFromUSToReadableAmount(String amount) throws ParseException{
		NumberFormat format = java.text.NumberFormat.getNumberInstance(java.util.Locale.US);
		return parseFromRMBToReadableAmount(String.valueOf(format.parse(amount)));
	}

	/**
	 * 两位小数的元单位大小比较
	 */
	public static boolean isCopmare(String first, String second) {
		first = parseFromRMBToReadableAmount(first);
		second = parseFromRMBToReadableAmount(second);
		if (Double.valueOf(first) > Double.valueOf(second)) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * 
	 * @param v2
	 *            加数
	 * 
	 * @return 两个参数的和
	 * 
	 */

	public static double add(double v1, double v2) {

		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));

		return b1.add(b2).doubleValue();

	}

	/**
	 * 
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * 
	 * @param v2
	 *            减数
	 * 
	 * @return 两个参数的差
	 * 
	 */

	public static double sub(double v1, double v2) {

		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));

		return b1.subtract(b2).doubleValue();

	}

	public static double sub(String v1, String v2) {

		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);

		return b1.subtract(b2).doubleValue();

	}

	/**
	 * 
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * 
	 * @param v2
	 *            乘数
	 * 
	 * @return 两个参数的积
	 * 
	 */

	public static double mul(double v1, double v2) {

		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));

		return b1.multiply(b2).doubleValue();

	}

	/**
	 * 
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * 
	 * @param v2
	 *            乘数
	 * 
	 * @return 两个参数的积
	 * 
	 */

	public static BigDecimal multiply(String v1, String v2) {

		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);

		return b1.multiply(b2);

	}

	/**
	 * 
	 * 提供精确的乘法运算，结果四舍五入。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @param scale
	 *            精度
	 * @return 两个参数的积
	 */

	public static double multiply(double v1, double v2, int scale) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
	}

	/**
	 * 
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
	 * 
	 * 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * 
	 * @param v2
	 *            除数
	 * 
	 * @return 两个参数的商
	 * 
	 */

	public static double div(double v1, double v2) {

		return div(v1, v2, DEF_DIV_SCALE);

	}

	/**
	 * 
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
	 * 
	 * 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * 
	 * @param v2
	 *            除数
	 * 
	 * @param scale
	 *            表示需要精确到小数点以后几位。
	 * 
	 * @return 两个参数的商
	 * 
	 */

	public static double div(double v1, double v2, int scale) {

		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}

		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));

		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

	}

	/**
	 * 
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数值
	 * 
	 * @param scale
	 *            小数点后保留几位小数
	 * 
	 * @return 四舍五入后的结果
	 * 
	 */

	public static Double round(double v, int scale) {

		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}

		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");

		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

	}
	

	
	
}
