public class ArrayTest2 {
	public static void main(String[] args) {
		int[][] nums = new int[3][];
		String[][] strs = new String[3][];
		nums[0] = new int[3];
		nums[1] = new int[]{2,3};
		strs[0] = new String[3];
		strs[1] = new String[] {"zhon","dafa"};
		System.out.println(nums[0]);
		System.out.println(nums[0][1]);
		System.out.println(nums[1][1]);
		System.out.println(nums[2]);
		System.out.println(strs[0]);
		System.out.println(strs[0][1]);
		System.out.println(strs[1][1]);
		System.out.println(strs[2]);
//	System.out.println(nums[2][0]);
	}
}