package example;

/**
 * Created by liqiang on 2018/4/24.
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] nums = {26, 53, 67, 48, 57, 13, 48, 32, 60, 50 };
        print(nums);
        System.out.println(System.nanoTime()/1000L);
        shellSort(nums);
        print(nums);
        System.out.println(System.nanoTime()/1000L);
    }

    private static void shellSort(int[] nums) {
        int dk = nums.length / 2;
        while (dk >= 1) {
            shellInsertSort(nums, dk);
            dk = dk / 2;
        }
    }

    private static void shellInsertSort(int[] nums, int dk) {
        for (int i = dk; i < nums.length; i++) {
            if (nums[i] < nums[i - dk]) {
                int j;
                int x = nums[i];
                nums[i] = nums[i - dk];
                for (j = i - dk; j >= 0 && x < nums[j]; j = j - dk) {
                    nums[j + dk] = nums[j];
                }
                nums[j + dk] = x;
            }
        }
    }

    public static void print(int[] nums) {
        for (int num : nums) {
            System.out.print(num + "\t");
        }
        System.out.println();
    }
}
