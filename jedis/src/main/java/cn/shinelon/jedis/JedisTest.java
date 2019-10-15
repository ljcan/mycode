package cn.shinelon.jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {
	@Test
	public void testJedis() {
//	Set<HostAndPort> jedisCluserNodes=new HashSet<HostAndPort>();
//	jedisCluserNodes.add(new HostAndPort("119.23.250.2", 6379));
//	JedisCluster js=new JedisCluster(jedisCluserNodes);
//	js.set("my", "liujunqinag");
//	System.out.println(js.get("my"));
		Jedis jedis=new Jedis("localhost");
		jedis.set("foo", "liujunqinag");
		System.out.println(jedis.get("foo"));
	}
	@Test
	public void testHash() {
		Jedis jedis=new Jedis("localhost",6379);
		Map<String,String> map=new HashMap<String,String>();
		map.put("frist", "张三");
		map.put("second","李四");
		map.put("third", "王五");
		map.put("forth", "小王");
		jedis.hmset("hash1",map);
		List<String> list=jedis.hmget("hash1", "second","third");
		for(String l:list) {
			System.out.println(l);
		}
	}
	//jedis连接池
	@Test
	public void testJedisPool() {
		JedisPoolConfig config=new JedisPoolConfig();
		JedisPool jedisPool=new JedisPool(config,"localhost",6379);
		Jedis jedis=null;
		try{
			jedis=jedisPool.getResource();
			String foo=jedis.get("foo");
			System.out.println(foo);
		}catch(Exception e) {
			System.out.println("连接出错");
		}finally {
			jedis.close();
		}
	}
	@Test
	//jedis保存对象
	public void testSaveObject() {
		User user=new User("shinelon",19);
		Jedis jedis=new Jedis("localhost");
		jedis.set("user:1".getBytes(), SerializeUtil.serialize(user));
		User obj=SerializeUtil.unserialize(jedis.get("user:1".getBytes()));
		System.out.println(obj);
	}
	@Test
	public void testWeibo() {
		Jedis jedis=new Jedis("localhost");
		jedis.lpush("pp", "{id:1}");
		System.out.println(jedis.lrange("pp", 0, -1));
	}
}
