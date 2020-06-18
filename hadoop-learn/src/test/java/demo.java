import net.minidev.json.JSONUtil;
import org.apache.commons.collections.map.LinkedMap;

import java.util.*;

public class demo {

    /**
     * TreeSet 去重，排序
     * @param args
     */
    public static void main(String[] args) {
        Set<String> set = new TreeSet<>();
        String word = "hello word hello haha jjj 123 666";
        String [] words = word.split(" ");
        for (String s : words) {
            set.add(s);
        }
        System.out.println("set items:");
        for (String s : set) {
            System.out.println(s);
        }
        System.out.println("size: "+set.size());

        String in1 = "1,2,3,4,5,6";
        String[] a = in1.split(",");
        int n = a.length;
        int k = 7;
        ringShift(a,n,k);
        System.out.println();
        //循环左移
        int[] in2 = new int[]{1,2,3,4,5,6};
        leftShitf(in2,n,k);

    }

    /**
     * 数组循环移动
     * @param a
     * @param n
     * @param k
     */
    public static void ringShift(String[] a,int n,int k){
        int[] s = new int[n];
        k %= n;
        for (int i = 0; i < n; i++) {
            s[i] = Integer.parseInt(a[n-k]);
            k--;
            if(k==0){
                k = n;
            }
        }
        for(int i=0;i<n;i++){
            a[i]= String.valueOf(s[i]);
        }
        for (String i : a) {
            System.out.print(i);
        }
    }

    public static void leftShitf(int[] a,int n,int k){
        int[] s = new int[n];
        k %= n;
        for (int i = 0; i < n; i++) {
            if((k + i) >= n){
                s[i] = a[(k+i)%n];
            }else {
                s[i] = a[k+i];
            }
        }
        for(int i=0;i<n;i++){
            a[i]= s[i];
        }
        for (int i : a) {
            System.out.print(i);
        }

        Map map = new LinkedHashMap();
        map.put(1,1);
        map.put(3,3);
        map.put(5,5);
        map.put(2,2);
        map.put(4,4);

        for (Iterator iter = map.keySet().iterator(); iter.hasNext(); ) {
            int it = (int) iter.next();
            System.out.println(it);
        }

        //bitMap 排序
        System.out.println("bitset");
        BitSet bs = new BitSet(8);
        int[] ia = {4,7,2,5,3};
        for (int i : ia) {
            bs.set(i, true);
        }
        int size = bs.size();
        for (int j=0; j<size; j++) {
            boolean b = bs.get(j);
            if (b)
                System.out.print(j + " ");
        }

    }
}
