package com.algorithm.structure.filter;

/**
 * @author wangyj
 * @Description: (布隆过滤器)
 * @date 2021/6/15 10:21
 */
public class BloomFilter<T> {

    //二进制向量长度
    private int bitSize;
    //二进制向量
    private long[] bits;
    //hash函数个数
    private int hashSize;

    /**
     * @param n 数据规模
     * @param p 误判率
     */
    public BloomFilter(int n, double p) {
        if (n <= 0 || p <= 0 || p >= 1) {
            throw new IllegalArgumentException("wrong n or p");
        }
        double ln2 = Math.log(2);
        //求二进制向量长度
        bitSize = (int) (-(n * Math.log(p)) / (ln2 * ln2));
        //hash函数个数
        hashSize = (int) (bitSize * ln2 / n);
        //bits数组长度(向上取整)
//        bits=new long[(int) Math.ceil(bitSize/Long.SIZE)];
        bits = new long[(bitSize + Long.SIZE - 1) / Long.SIZE];
    }

    //添加元素
    public boolean put(T value) {
        nullCheck(value);
        //利用value生成两个整数
        int hash1 = value.hashCode();
        int hash2 = hash1 >>> 16;

        for (int i = 1; i <= hashSize; i++) {
            int combinedHash = hash1 + (i * hash2);
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            int index = combinedHash % bitSize;
            //设置index位置的二进制位(按位或运算)
            set(index);
        }
        return true;
    }

    //判断元素是否存在
    public boolean contains(T value) {
        nullCheck(value);
        // 利用value生成2个整数
        int hash1 = value.hashCode();
        int hash2 = hash1 >>> 16;

        for (int i = 1; i <= hashSize; i++) {
            int combinedHash = hash1 + (i * hash2);
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            // 生成一个二进位的索引
            int index = combinedHash % bitSize;
            // 查询index位置的二进位是否为0
            if (!get(index)) return false;
        }
        return true;
    }

    //获取index位置二进制 true 1 false 0
    private boolean get(int index) {
        //取下标在哪个数组中
        int position = index / Long.SIZE;
        //取需要改变的二进制数据在哪一位
        int size = index - position * Long.SIZE;
        //本身与那个位置下标为1的进行按位与运算看结果是否为0
        return (bits[position] & size << 1) != 0;
    }

    //设置index位置二进制
    private void set(int index) {
        //取下标在哪个数组中
        int position = index / Long.SIZE;
        //取需要改变的二进制数据在哪一位
        int size = index - position * Long.SIZE;
        //进行二进制与运算改变size的位置为1
        bits[position] |= 1 << size;
    }

    private void nullCheck(T value) {
        if (value == null) {
            throw new IllegalArgumentException("value must not be null");
        }
    }


    public static void main(String[] args) {
        System.out.println((2) | 1);
    }
}
