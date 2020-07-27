import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.houyuli.common.utils.NumberUtil;
import com.houyuli.common.utils.StreamUtil;
import com.houyuli.common.utils.StringUtil;
import com.houyuli.domain.Goods;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-redis.xml")
public class Test {

	@Resource
	private RedisTemplate<String, Goods> redisTemplate;

	@org.junit.Test
	public void test() {
//		System.out.println(redisTemplate);
		// 定义一个value为list的类型
		ListOperations<String, Goods> opsForList = redisTemplate.opsForList();
		// 获取文件内信息
		List<String> readLineFile = StreamUtil
				.readLineFile(new File("F:\\shiyan1\\houyuli-senior2-week2\\src\\test\\resources\\data.txt"));
		for (String string : readLineFile) {
			Goods goods = new Goods();
			String[] split = string.split("==");
//			for (String string2 : split) {
//				System.out.println(string2);
//			}
			if (NumberUtil.isNumber(split[0])) {
				goods.setId(Integer.valueOf(split[0]));
			}
			if (StringUtil.hasText(split[1])) {
				goods.setName(split[1]);
			}
			if (StringUtil.hasText(split[2])) {
				if (!NumberUtil.isNumber(split[2])) {
					split[2] = split[2].replace("¥", "").trim();
					goods.setPrice(BigDecimal.valueOf(Double.valueOf(split[2])));
				}
			}
			if (split.length > 3) {
				if (StringUtil.hasText(split[3])) {
					if (!NumberUtil.isNumber(split[3])) {
						split[3] = split[3].replace("%", "").trim();
						goods.setBfb(Double.valueOf(split[3]));
					}
				} else {
					goods.setBfb(0);
				}
			} else {
				goods.setBfb(0);
			}
			// 将信息填入到redis中
			opsForList.leftPush("goods_list", goods);
		}
	}

	@org.junit.Test
	public void test1() {
//		System.out.println(redisTemplate);
		// 定义一个value为ZSet的类型
		ZSetOperations<String, Goods> opsForZSet = redisTemplate.opsForZSet();
		// 获取文件内信息
		List<String> readLineFile = StreamUtil
				.readLineFile(new File("F:\\shiyan1\\houyuli-senior2-week2\\src\\test\\resources\\data.txt"));
		for (String string : readLineFile) {
			Goods goods = new Goods();
			String[] split = string.split("==");
//			for (String string2 : split) {
//				System.out.println(string2);
//			}
			if (NumberUtil.isNumber(split[0])) {
				goods.setId(Integer.valueOf(split[0]));
			}
			if (StringUtil.hasText(split[1])) {
				goods.setName(split[1]);
			}
			if (StringUtil.hasText(split[2])) {
				if (!NumberUtil.isNumber(split[2])) {
					split[2] = split[2].replace("¥", "").trim();
					goods.setPrice(BigDecimal.valueOf(Double.valueOf(split[2])));
				}
			}
			if (split.length > 3) {
				if (StringUtil.hasText(split[3])) {
					if (!NumberUtil.isNumber(split[3])) {
						split[3] = split[3].replace("%", "").trim();
						goods.setBfb(Double.valueOf(split[3]));
					}
				} else {
					goods.setBfb(0);
				}
			} else {
				goods.setBfb(0);
			}
			// 将信息加入到ZSet中
			// 且将goods的已售百分比当作权重写入
			opsForZSet.add("goods_zset", goods, goods.getBfb());
		}
	}

	@org.junit.Test
	public void test2() {

	}
}
