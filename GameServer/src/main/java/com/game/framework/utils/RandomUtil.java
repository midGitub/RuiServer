package com.game.framework.utils;

import com.google.common.base.Preconditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机数工具
 * @author liguorui
 * @date 2017年3月6日 下午9:10:58
 */
public class RandomUtil {

	/**
	 * 在[min, max)区间产生一个随机数，并比较num是否大于该随机数。
	 * 
	 * @param num
	 * @param min
	 *            大于等于0
	 * @param max
	 *            大于min
	 * @return 当num大于在[min, max)区间产生一个随机数时，返回true。 当num小于等于在[min,
	 *         max)区间产生一个随机数时，返回false。
	 */
	public static boolean isLarger(int num, int min, int max) {
		int rd = generateBetween(min, max);
		return num > rd;
	}

	/**
	 * 从[min, max]区间随机产生一个数，包括上限
	 * @param min
	 * @param max
	 * @return
	 */
	public static int generateBetweenIncludeMax(int min, int max) {
		return generateBetween(min, max +1);
	}

	/**
	 * 在[min, max)区间产生一个随机数
	 *
	 * @param min
	 *            大于等于0
	 * @param max
	 *            大于min
	 * @return
	 */
	public static int generateBetween(int min, int max) {
		if (min == max) {
			return min;
		}

		Preconditions.checkArgument(min >= 0, "generateBetween.min <0", min);
		Preconditions.checkArgument(min <= max, "generateBetween.min > max", min, max);
		return ThreadLocalRandom.current().nextInt(max - min) + min;
	}

	/**
	 * 在[min, max]区间产生一个随机数
	 * @param min 大于等0
	 * @param max 大于min
	 * @return
	 */
	public static int randomBothInclude(int min, int max) {
		if (min < 0) {
			throw new IllegalArgumentException("min must > 0");
		}

		if (min > max) {
			throw new IllegalArgumentException("min must <= max");
		}

		if (min == max) {
			return min;
		}

		return ThreadLocalRandom.current().nextInt(max - min + 1) + min;
	}

	public static int nextInt(int i) {
		return ThreadLocalRandom.current().nextInt(i);
	}

	public static int nextInt() {
		return ThreadLocalRandom.current().nextInt();
	}

	public static boolean nextBoolean() {
		return ThreadLocalRandom.current().nextBoolean();
	}

	/**
	 * 随机值是否处在[0,rate]的范围内
	 * @param rate 	概率区间值
	 * @param baseRate 	随机值最大值
	 * @return
	 */
	public static boolean isInTheRange(int rate, int baseRate) {
		if (rate >= baseRate) {
			return true;
		}
		int random = generateBetween(0, baseRate);
		return random < rate;
	}

	/**
	 * 随机是否成功，以10000为基数
	 * @param pro 概率
	 * @return
	 */
	public static boolean isInTheRange(int pro) {
		return isInTheRange(pro, 10000);
	}

	/**
	 * 返回随机改了的某个下标
	 * @param rates
	 * @return
	 */
	public static int getRandomIndex(final int rates[]) {
		int sum = 0;
		for (int rate : rates) {
			sum += rate;
		}
		if (sum <= 0) {
			return -1;
		}
		int random = ThreadLocalRandom.current().nextInt(sum);
		sum = 0;
		for (int i=0; i < rates.length; i++) {
			sum += rates[i];
			if (random < sum ) {
				return i;
			}
		}
		return -1; //不能发生了
	}

//	public static <T extends ProEntity> T randProList(List<T> proList) {
//		if (proList.size() == 1) {
//			return proList.get(0);
//		}
//
//		int sumPro = 0;
//		for (ProEntity proEntity : proList) {
//			sumPro += proEntity.getPro();
//		}
//
//		if (sumPro < 0) {
//			return null;
//		}
//
//		int randValue = ThreadLocalRandom.current().nextInt(sumPro);
//		int curPro = 0;
//		for (T entity : proList) {
//			curPro += entity.getPro();
//			if (randValue < curPro) {
//				return entity;
//			}
//		}
//		return null;
//	}

	/**
	 * 等概率随机一个
	 * @param randList
	 * @param <T>
	 * @return
	 */
	public static <T> T randEqualPro(List<T> randList) {
		if (randList.size() == 0) {
			return null;
		}

		int index = ThreadLocalRandom.current().nextInt(randList.size());
		return randList.get(index);
	}

	public static <T> T randArray(T[] ts) {
		if (ts == null || ts.length == 0) {
			return null;
		}

		int index = ThreadLocalRandom.current().nextInt(ts.length);
		return ts[index];
	}

	public static int randIntArray(int [] ints) {
		if (ints == null || ints.length == 0) {
			return 0;
		}

		int index = ThreadLocalRandom.current().nextInt(ints.length);
		return ints[index];
	}

	/**
	 * 在[min, max)区间产生一个随机数，并比较num是否小于该随机数。
	 * 
	 * @param num
	 * @param min
	 *            大于等于0
	 * @param max
	 *            大于min
	 * @return
	 */
	public static boolean isSmaller(int num, int min, int max) {
		int m = generateBetween(min, max);
		return num < m;
	}


	/**
	 * <pre>
	 * 判断概率是否命中。
	 * 		比如想判断是否命中70%的概率。那么传入 probability值70，传入base值100，返回true则表示命中概率。
	 * </pre>
	 * 
	 * @param probability
	 *            概率
	 * @param base
	 *            概率基数
	 * @return
	 */
	public static boolean isHitProbability(int probability, int base) {
		int generateBetween = generateBetween(0, base);
		return generateBetween <= probability - 1;
	}

	/**
	 * 获取一个数随机正负
	 * 
	 * @param num
	 * @return
	 */
	public static int getRandomPlusOrMinus(int num) {
		int random = generateBetween(0, 2);
		if (random == 0)
			return num * -1;
		else
			return num;
	}

}
