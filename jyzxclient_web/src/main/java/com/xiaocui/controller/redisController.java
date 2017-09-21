package com.xiaocui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaocui.entity.Result;

@Controller
@RequestMapping("/redis")
public class redisController {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	//set and zset
	@RequestMapping("testRedis7")
	public void testRedis7(){
		System.out.println("执行开始！");
		SetOperations<String, Object> ops = redisTemplate.opsForSet();
		ops.add("a", "我是好人","我是帅哥");
		
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Integer.class));
		ZSetOperations<String, Object> opsz = redisTemplate.opsForZSet();
		opsz.rank("b", 1);
		
		System.out.println("执行结束！");		
	}
	

	@RequestMapping("testRedis8")
	public void testRedis8(){
		System.out.println("执行开始！");
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(String.class));
		SetOperations<String, Object> ops = redisTemplate.opsForSet();
		String a = (String)ops.pop("a");
		System.out.println(a);
		
		ZSetOperations<String, Object> opsz = redisTemplate.opsForZSet();
		Long size = opsz.zCard("a");
		System.out.println(size);
		System.out.println("执行结束！");		
	}
	
	//String 
	@RequestMapping("testRedis5")
	public void testRedis5(){
		System.out.println("执行开始！");
		String key = "c";
		ValueOperations<String, Object> ops = redisTemplate.opsForValue();
		ops.increment(key, 15L);
		ops.increment(key, 12L);
		ops.set("d", "waliwaliwa");
		System.out.println("执行结束！");		
	}
	
	@RequestMapping("testRedis6")
	public void testRedis6(){
		System.out.println("执行开始！");
		ValueOperations<String, Object> ops = redisTemplate.opsForValue();
		String val = (String)ops.get("c");
		String val1 = (String)ops.get("d");
		System.out.println("数字："+val);
		System.out.println("字符串："+val1);
		System.out.println("执行结束！");		
	}
	
	//list
	@RequestMapping("testRedis3")
	public void testRedis3(){
		System.out.println("执行开始！");
		String key = "b";
		ListOperations<String, Object> ops = redisTemplate.opsForList();
		ops.leftPush(key,"aaa");
		ops.leftPush(key,"bbb");
		System.out.println("执行结束！");
	}
	
	@RequestMapping("testRedis4")
	public void testRedis4(){
		System.out.println("执行开始！");
		ListOperations<String, Object> ops = redisTemplate.opsForList();
		System.out.println(ops.size("b"));
		String res = (String) ops.rightPop("b");
		System.out.println(res);
		System.out.println("执行结束！");
	}
	
	//hashMap
	@RequestMapping("testRedis1")
	public void testRedis1(){
		//hashmap
		System.out.println("执行开始！");
//        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
		Result result = new Result();
		result.setBlackLevel("a");
		result.setBlackReason("T01");
		HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
		ops.put("user", result.getBlackLevel(), result);
		System.out.println("执行结束！");

	}
	
	@RequestMapping("testRedis2")
	public void testRedis2(){
		System.out.println("执行开始！");
		HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
		Result res = (Result) ops.get("user", "a");
		System.out.println(res.getBlackReason());
		System.out.println("执行结束！");
	}
	
}
