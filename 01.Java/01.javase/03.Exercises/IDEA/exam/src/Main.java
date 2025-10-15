import java.util.*;

//有一个整数数组，数组中所有整数值不相等。请编程完成方法subsets，返回该数组的子集。
// 返回结果不能有重复的子集。返回结果不需要考虑顺序。
//public List<List<Integer>> subsets(int[] nums) {
//        // 请在此完成编程算法并返回结果
//        }
//
//        示例 1：
//        输入：nums = [1,2,3]
//        输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
//
//        示例 2：
//        输入：nums = [0]
//        输出：[[],[0]]
//          [[],[0],[1],[0,1]]
//[[],[0],[1],[0,1],[2],[0,2],[1,2],[0,1,2]]]
//        提示：
//        1 <= nums.length <= 10
//        -10 <= nums[i] <= 10
//        nums 中的整数互不相等
public class Main {
    public static void main(String[] args) {
        int[] nums = {0,1,2};
        List<List<Integer>> subsets = subsets(nums);
        System.out.println(subsets);
    }

    public static List<List<Integer>> subsets(int[] nums) {
        // 请在此完成编程算法并返回结果
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        System.out.println(list);
        System.out.println(list.size());
        for (int i = 0; i < nums.length; i++) {
            int n = list.size();
            for (int j = 0; j < n; j++) {
                List<Integer> temp = new ArrayList<>(list.get(j));
//                不能采用下面这种写法，会导致temp与list.get(j)指向同一对象，修改temp时，get(j)也发生修改
//                List<Integer> temp = new ArrayList<>();
//                temp=list.get(j);
                System.out.println(temp);
                temp.add(nums[i]);
                System.out.println(temp);
                list.add(temp);
            }
        }

        return list;
    }
}