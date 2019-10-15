package cn.just.hbase;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IOUtils;
/**
 * HBase CRUD Operation
 * @author shinelon
 *
 */
public class HbasePut01 {
	public static HTable HbaseTableGetByName(String tableName) throws Exception {
		Configuration configuration=HBaseConfiguration.create();
		HTable table=new HTable(configuration, tableName);
		return table;
	}
	
	public static void getData(HTable table,String rowKey) throws Exception {
		Get get=new Get(Bytes.toBytes(rowKey));

//========================================================================================
		//add column,get specials columns
		get.addColumn(
				Bytes.toBytes("info"),
				Bytes.toBytes("name")
				);
		get.addColumn(
				Bytes.toBytes("info"),
				Bytes.toBytes("age")
			);
		
		
		
		
		
		Result res=table.get(get);
		//Key : rowkey+cf+c+version
		//Value : value 
		for(Cell cell:res.rawCells()) {
			System.out.println(
					Bytes.toString(CellUtil.cloneFamily(cell))+" : "
				+   Bytes.toString(CellUtil.cloneQualifier(cell))+"->"
				+   Bytes.toString(CellUtil.cloneValue(cell))
			);
		}
	}
	public static void putData(HTable table,String tableName) throws Exception {
		table=HbaseTableGetByName(tableName);
		Put put=new Put(Bytes.toBytes("1004"));
		put.add(
			Bytes.toBytes("info"),	
			Bytes.toBytes("name"),
			Bytes.toBytes("shinelon")
			);
		put.add(
				Bytes.toBytes("info"),	
				Bytes.toBytes("age"),
				Bytes.toBytes(19)
				);
		table.put(put);
	}
	public static void deleteData(HTable table,String tableName) throws Exception {
		table=HbaseTableGetByName(tableName);
		Delete delete=new Delete(Bytes.toBytes("1004"));
		//因为一列数据有多个版本deleteColumn删除最新的版本的数据，deleteColumns删除所有版本的数据
		//删除一列数据
		/*
		delete.deleteColumn(
				Bytes.toBytes("info"),
				Bytes.toBytes("age")
				);
		*/
		//删除一行数据
		delete.deleteFamily(Bytes.toBytes("info"));
		table.delete(delete);
	}
	
	/**
	 * 添加数据
	 * @param table
	 * @param tableName 表名
	 * @param cf
	 * @param rowKey 
	 * @param keyValue 添加的列值对
	 * @throws Exception
	 */
	public static void putData(HTable table,String tableName,String cf,String rowKey,Map<String,String> keyValue)
			throws Exception {
		table=HbaseTableGetByName(tableName);
		Put put=new Put(Bytes.toBytes(rowKey));
		for(Entry<String, String> set:keyValue.entrySet()) {
			put.add(
					Bytes.toBytes(cf),	
					Bytes.toBytes(set.getKey()),
					Bytes.toBytes(set.getValue())
					);
		}
		
		table.put(put);
	}
	
	public static void scanData(HTable table,String tableName) throws Exception {
		table=HbaseTableGetByName(tableName);
		Scan scan=new Scan();
		
//		ValueFilter.parseFrom(pbBytes);
		
		
		
		//PrefixFilter
		//PageFilter
//		scan.setFilter(filter)
		
		//包头不包尾
		scan.setStartRow(Bytes.toBytes("1001"));
		scan.setStopRow(Bytes.toBytes("1003"));
		
//		Scan scan=new Scan(startRow, stopRow);
		
//		scan.addColumn(family, qualifier);
		
		//设置缓存
//		scan.getCacheBlocks()
//		scan.getCaching()
		
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
	
	
	public static void get(HTable table,String tableName,String rowKey,String... columns) throws Exception {
		table=HbaseTableGetByName(tableName);
		Get get=new Get(Bytes.toBytes(rowKey));
		get.addColumn(Bytes.toBytes(""), Bytes.toBytes(""));
		table.get(get);
		Result res=table.get(get);
		//Key : rowkey+cf+c+version
		//Value : value 
		for(Cell cell:res.rawCells()) {
			System.out.println(
					Bytes.toString(CellUtil.cloneFamily(cell))+" : "
				+   Bytes.toString(CellUtil.cloneQualifier(cell))+"->"
				+   Bytes.toString(CellUtil.cloneValue(cell))
			);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		String tableName="user2";
		String rowKey="1002";
		HTable table = null;
		try {
		   table=HbaseTableGetByName(tableName);
//		   getData(table, rowKey);
//		   putData(table, tableName);
//		   deleteData(table, tableName);
		   scanData(table, tableName);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			/*
			if(table!=null) {
			table.close();
			}
			*/
			IOUtils.closeStream(table);
		}
	}
}
