package cu.just.shinelon.utils;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * JAVA操作HBase工具类，使用单例模式编写
 */
public class HBaseUtils {
    private HBaseAdmin hBaseAdmin=null;
    private Configuration configuration=null;

    private static HBaseUtils instance=null;

    private HBaseUtils(){
        configuration=new Configuration();
        configuration.set("hbase.rootdir","hdfs://hadoop-senior04.shinelon.com:8020/user/shinelon/hbase");
        configuration.set("hbase.zookeeper.quorum","hadoop-senior04.shinelon.com");
        try {
            hBaseAdmin = new HBaseAdmin(configuration);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static synchronized HBaseUtils getInstance(){
        if(instance==null){
            instance=new HBaseUtils();
        }
        return instance;
    }

    /**
     * 获取表名
     * @param tableName
     * @return
     */
    public HTable getTable(String tableName){
        HTable table=null;
        try {
        table=new HTable(configuration,tableName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return table;
    }

    /**
     * 向表中写入数据
     * @param tableName     表名
     * @param rowKey        rowkey
     * @param cf            列簇
     * @param column        列名
     * @param value         列的值
     */
    public void put(String tableName,String rowKey,String cf,String column,String value){
        HTable table=getTable(tableName);
        Put put=new Put(Bytes.toBytes(rowKey));

        put.add(Bytes.toBytes(cf),Bytes.toBytes(column),Bytes.toBytes(value));
        try{
        table.put(put);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Map<String,Long> queryCount(String tableName,String dayCourse) throws Exception{
    	String cf="info";
    	String column="clickcount";
    	Map<String, Long> map=new HashMap<String, Long>();
    	HTable table=getTable(tableName);
    	Scan scan=new Scan();
    	scan.setFilter(new PrefixFilter(Bytes.toBytes(dayCourse)));
    	ResultScanner result=table.getScanner(scan);
    	for(Result rs:result) {
    		String row=Bytes.toString(rs.getRow());
    		Long clickCount=Bytes.toLong(rs.getValue(Bytes.toBytes(cf), Bytes.toBytes(column)));
    		map.put(row ,clickCount);
    	}
    	return map;
    }
    /**
     * course_search_clickcount
     * @param tableName
     * @param day
     * @return
     * @throws Exception
     */
    public Map<String,Long> querySearch(String tableName,String day) throws Exception{
    	String cf="info";
    	String column="clickcount";
    	Map<String, Long> map=new HashMap<String, Long>();
    	HTable table=getTable(tableName);
    	Scan scan=new Scan();
    	scan.setFilter(new PrefixFilter(Bytes.toBytes(day)));
    	ResultScanner result=table.getScanner(scan);
    	for(Result rs:result) {
    		String row=Bytes.toString(rs.getRow());
    		Long clickCount=Bytes.toLong(rs.getValue(Bytes.toBytes(cf), Bytes.toBytes(column)));
    		map.put(row ,clickCount);
    	}
    	return map;
    }
    
//    public Map<String,String> query(String tableName,String cf,String... column){
//    	String columnFimally = cf;
////    	String columns[] = null;
////    	for(int i=0;i<column.length;i++) {
////    		columns[i] = columns[i];
////    	}
//    	Map<String, Long> map=new HashMap<String, Long>();
//    	HTable table=getTable(tableName);
//    	Scan scan = new Scan();
//    	try {
//			ResultScanner result=table.getScanner(scan);
//			for(Result rs:result) {
//	    		String row=Bytes.toString(rs.getRow());
//	    		
//	    	}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//    	
//    	return null;
//    	
//    }
    
	public void scanData(String tableName,String cf,String... column) throws IOException {
		HTable table=getTable(tableName);
		Scan scan=new Scan();
//		scan.setMaxResultSize(100);
		ResultScanner scanner=table.getScanner(scan);
		for(Result result:scanner) {
			System.out.println(Bytes.toString(result.getRow()));
//			System.out.println(result);
			for(Cell cell:result.rawCells()) {
				System.out.println(
						Bytes.toString(CellUtil.cloneFamily(cell))+" : "
					+   Bytes.toString(CellUtil.cloneQualifier(cell))+"->"
					+   Bytes.toString(CellUtil.cloneValue(cell))
				);
			}
			System.out.println("=============================================");
		}
	}
	
    
    
    public static void main(String[] args) throws Exception{
       HTable table= HBaseUtils.getInstance().getTable("course_clickcount");

        System.out.println(table.getName().getNameAsString());
    	
    	Map<String,Long> map=HBaseUtils.getInstance().queryCount("course_clickcount", "20180724");
    	
    	for(Map.Entry<String,Long> m:map.entrySet()) {
    		System.out.println(m.getKey()+"    :"+m.getValue());
    	}
    	Map<String,Long> searchmap=HBaseUtils.getInstance().queryCount("course_search_clickcount", "20180724");
    	
    	for(Map.Entry<String,Long> m:searchmap.entrySet()) {
    		System.out.println(m.getKey()+"    :"+m.getValue());
    	}
    	
    }


}
