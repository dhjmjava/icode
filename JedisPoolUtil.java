/**  
 * Project Name:blog  
 * File Name:JedisPoolUtil.java  
 * Package Name:com.blog.cache  
 * Date:2017年1月23日下午8:01:14  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package redisTest;  


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**  
 * ClassName:JedisPoolUtil <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年1月23日 下午8:01:14 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public class JedisPoolUtil {
	private static volatile JedisPool jedisPool=null;
	
	private JedisPoolUtil(){};
	
	/**
	 * 
	 * getJedisPoolInstance:(获取唯一连接池实例). <br/>   
	 * @author Administrator  
	 * @return  
	 * @since JDK 1.6
	 */
	public static JedisPool getJedisPoolInstance(){
		if(null == jedisPool){
			synchronized (JedisPoolUtil.class) {
				if(null == jedisPool){
					JedisPoolConfig poolConfig = new JedisPoolConfig();
					poolConfig.setMaxActive(1000);
					poolConfig.setMaxIdle(32);
					poolConfig.setMaxWait(100*1000);
					poolConfig.setTestOnBorrow(true);
					
					jedisPool = new JedisPool(poolConfig,"127.0.0.1",6379);
				}
			}
		}
		return jedisPool;
	}

	/**
	 * 
	 * release:(将空闲链接放回池中). <br/>   
	 *  
	 * @author Administrator  
	 * @param jedisPool
	 * @param jedis  
	 * @since JDK 1.6
	 */
	public static void release(JedisPool jedisPool,Jedis jedis){
		if(null != jedis){
			jedisPool.returnResourceObject(jedis);
		}
	}
}
  
