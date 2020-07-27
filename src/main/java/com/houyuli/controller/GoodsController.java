package com.houyuli.controller;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.houyuli.domain.Goods;
import com.houyuli.service.GoodsService;

@Controller
public class GoodsController {

	@Resource
	private GoodsService goodsService;
	@Resource
	private RedisTemplate<String, Goods> redisTemplate;

	@RequestMapping("selectListGoods")
	public String selectListGoods(Model model, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize) {
//		ListOperations<String, Goods> opsForList = redisTemplate.opsForList();
//		List<Goods> range = opsForList.range("goods_list", 0, -1);
//		model.addAttribute("range", range);
		// 获取信息
		List<Goods> selectGoods = goodsService.selectGoods(pageNum, pageSize);
		// 放入model域中
		model.addAttribute("info", selectGoods);
		// 去页面
		return "goods";
	}

	@RequestMapping("selectZSetGoods")
	public String selectZSetGoods(Model model, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		// 获取信息
		Set<TypedTuple<Goods>> selectZSetGoods = goodsService.selectZSetGoods(pageNum, pageSize);
		// 将信息写入model域中
		model.addAttribute("info", selectZSetGoods);
		// 去页面
		return "goodsZSet";
	}
}
