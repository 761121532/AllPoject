package spider;

import java.util.Random;

public class ArrayRandom {

    static int[] random(int amount, int max) {

        if (amount > max) { // 需要数字总数必须小于数的最大值，以免死循环！
            throw new ArrayStoreException(
                    "The amount of array element must smallar than the maximum value !");
        }
        int[] array = new int[amount];
        for (int i = 0; i < array.length; i++) {
            array[i] = -1; // 初始化数组，避免后面比对时数组内不能含有0。
        }
        Random random = new Random();
        int num;
        amount -= 1; // 数组下标比数组长度小1
        while (amount >= 0) {
            num = random.nextInt(max);
            if (exist(num, array, amount - 1)) {
                continue;
            }
            array[amount] = num;
            amount--;
        }
        return array;
    }

    /**
            * 判断随机的数字是否存在数组中
     *
             * @param num
     *            随机生成的数
     * @param array
     *            判断的数组
     * @param need
     *            还需要的个数
     * @return 存在true，不存在false
     */
    private static boolean exist(int num, int[] array, int need) {

        for (int i = array.length - 1; i > need; i--) {// 大于need用于减少循环次数，提高效率。
            if (num == array[i]) {
                return true;
            }
        }
        return false;
    }

    /**
            * 随机生成一个数
     *
             * @param max
     *            最大值(不含)
     * @return 整型数
     */
    public static int random(int max) {

        return random(1, max)[0];
    }
}