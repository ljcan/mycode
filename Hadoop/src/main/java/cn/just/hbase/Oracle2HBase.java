package cn.just.hbase;
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
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IOUtils;
/**
 * HBase CRUD Operation
 * @author shinelon
 *
 */
public class Oracle2HBase {
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
	
	
	public static void scanData(HTable table,String tableName) throws Exception {
		table=HbaseTableGetByName(tableName);
		Scan scan=new Scan();
		
		scan.setMaxResultSize(100);
		
		
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
	
	public static void main(String[] args) throws Exception {
		String tableName="personInfo2";
//		String rowKey="1002";
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
