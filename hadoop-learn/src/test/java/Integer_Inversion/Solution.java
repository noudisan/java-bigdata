package Integer_Inversion;

/**
 * leedcode#7 整数反转
 */
class Solution {
    public int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            // 做溢出判断，其中的 ans*10 ，java虚拟机内部实际上是进行了数值类型提升，
            // 即溢出时，用long类型数据暂时存储，最后通过变窄转换，保留低32位数值得到 (ans * 10) / 10 != ans
            if ((ans * 10) / 10 != ans) {
                ans = 0;
                break;
            }
            ans = ans * 10 + x % 10;
            x = x / 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reverse(1534236469));
    }
}