package cn.just.hbase.mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class UserToBasicMapreduce extends Configured implements Tool{
	
	//Mapper class
	public static class UserMapper extends TableMapper<Text, Put>{
		public Text mapOutPutKey=new Text();
		
		//key:rowKey   value:cell
		@Override
		public void map(ImmutableBytesWritable key, Result value,
				Mapper<ImmutableBytesWritable, Result, Text, Put>.Context context)
				throws IOException, InterruptedException {
			//set rowkey
			String rowkey=Bytes.toString(key.get());
			
			mapOutPutKey.set(rowkey);
			
			Put put=new Put(key.get());
			
			for(Cell cell:value.rawCells()) {
				//add cf
				if("info".equals(Bytes.toString(CellUtil.cloneFamily(cell)))) {
					//add name column
					if("name".equals(Bytes.toString(CellUtil.cloneQualifier(cell)))) {
						put.add(cell);
					}
					//add age column
					if("age".equals(Bytes.toString(CellUtil.cloneQualifier(cell)))) {
						put.add(cell);
					}
				}
			}
			context.write(mapOutPutKey, put);
			
		}
		
	}
	//Reducer class
	public static class BasicReducer extends TableReducer<Text, Put, ImmutableBytesWritable>{

		@Override
		public void reduce(Text key, Iterable<Put> values,
				Reducer<Text, Put, ImmutableBytesWritable, Mutation>.Context context)
				throws IOException, InterruptedException {
			for(Put put:values) {
				context.write(null, put);
			}
		}
	}
	//Driver class
	public int run(String[] arg0) throws Exception {
		//create job
		Job job=Job.getInstance(this.getConf(), this.getClass().getSimpleName());
		
		//set job jar
		job.setJarByClass(this.getClass());
		
		//set job
		Scan scan = new Scan();
		scan.setCaching(500);        // 1 is the default in Scan, which will be bad for MapReduce jobs
		scan.setCacheBlocks(false);  // don't set to true for MR jobs
		
		// set other scan attrs

		TableMapReduceUtil.initTableMapperJob(
		  "user",        // input table
		  scan,               // Scan instance to control CF and attribute selection
		  UserMapper.class,     // mapper class
		  Text.class,         // mapper output key
		  Put.class,  // mapper output value
		  job);
		TableMapReduceUtil.initTableReducerJob(
		  "basic",        // output table
		  BasicReducer.class,    // reducer class
		  job);
		job.setNumReduceTasks(1);   // at least one, adjust as required

		boolean b = job.waitForCompletion(true);
		if(!b) {
			throw new IOException("error with job!");
		}
		return b?1:0;
	}
	public static void main(String[] args) throws Exception{
		//set configration
		Configuration configuration=HBaseConfiguration.create();
		//submit job
		int status=ToolRunner.run(configuration, new UserToBasicMapreduce(), args);
		//exit program
		System.exit(status);
	}
}
