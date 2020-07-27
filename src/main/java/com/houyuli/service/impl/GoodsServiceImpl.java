package com.houyuli.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.houyuli.domain.Goods;
import com.houyuli.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Resource
	private RedisTemplate<String, Goods> redisTemplate;

	@Override
	public List<Goods> selectGoods(Integer pageNum, Integer pageSize) {
		// 获取到redis中list对象
		ListOperations<String, Goods> opsForList = redisTemplate.opsForList();
		// 从list中获取到商品集合
		List<Goods> range = opsForList.range("goods_list", (pageNum - 1) * pageSize, pageNum * pageSize - 1);
		return range;
	}

	@Override
	public Set<TypedTuple<Goods>> selectZSetGoods(Integer pageNum, Integer pageSize) {
		// 获取到redis中ZSet对象
		ZSetOperations<String, Goods> opsForZSet = redisTemplate.opsForZSet();
		// 从ZSet中获取到针对于权重值倒序的商品set集合
		Set<TypedTuple<Goods>> rangeWithScores = opsForZSet.reverseRangeWithScores("goods_zset",
				(pageNum - 1) * pageSize, pageNum * pageSize - 1);
		return rangeWithScores;
	}

}
