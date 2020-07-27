package com.houyuli.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

import com.github.pagehelper.PageInfo;
import com.houyuli.domain.Goods;

public interface GoodsService {
	// 查找value为list的信息
	List<Goods> selectGoods(Integer pageNum, Integer pageSize);

	// 查找value为Zset的信息
	Set<TypedTuple<Goods>> selectZSetGoods(Integer pageNum, Integer pageSize);
}
