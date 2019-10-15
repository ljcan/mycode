package cn.just.hive.udf;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;
/**
 * 1. Implement one or more methods named
 * "evaluate" which will be called by Hive.
 * 2."evaluate" should never be a void method. However it can return "null" if
 * needed.
 * @author shinelon
 *
 */

//@Description() 利用该注解编写函数注释
public class DateFormat extends UDF{
	private final SimpleDateFormat inputFormat=new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
	private final SimpleDateFormat outputFormat=new SimpleDateFormat("yyyyMMddHHmmss");
	 public Text evaluate(Text input) {
		 Text output=new Text();
		 if(null==input) {
			 return null;
		 }
		 String inputDate=input.toString().trim();
		 if(null==input.toString()) {
			 return null;
		 }
		 try {
			 Date parseDate=inputFormat.parse(inputDate);
			 String outputDate=outputFormat.format(parseDate);
			 output.set(outputDate);
		 }catch (Exception e) {
			 e.printStackTrace();
			 return output;
		 }
		 return output;
	 }
	 public static void main(String[] args) {
		System.out.println(new DateFormat().evaluate(new Text("31/Aug/2015:00:04:37 +0800")));
	}
}
