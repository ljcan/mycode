package cn.just.hive.udf;

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
public class RemoveQuotesUDF extends UDF{
	 public Text evaluate(Text str) {
		 if(null==str) {
			 return new Text();
		 }
		 if(null==str.toString()) {
			 return new Text();
		 }
		 return new Text(str.toString().replaceAll("\"",""));
	 }
	 public static void main(String[] args) {
		System.out.println(new RemoveQuotesUDF().evaluate(new Text("\"31/Aug/2015:23:57:46 +0800\"")));
	}
}
