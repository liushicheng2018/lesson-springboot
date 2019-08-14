package com.example.cloud.gatewayclient.pojo;

/**
 * @Description TODO
 * @Classname TestO
 * @Date 2019/7/15 9:52
 * @Author liusc <liushicheng1993@vip.qq.com>
 * @Version 1.0
 */
public class TestO {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        int[] numbers = {1,2,3,4,5,6,7,8};
        int[] ints = twoSum(numbers, 10);
        int[] ints1 = twoSum2(numbers, 100);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
        for (int i : ints1) {
            System.out.println(i);
        }

    }
    public static int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;

        while (i != j) {
            if (numbers[i] + numbers[j] == target) {
                return new int[]{i, j};
            }

            if (numbers[i] + numbers[j] < target) {
                i++;
                continue;
            }

            if (numbers[i] + numbers[j] > target) {
                j--;
                continue;
            }
        }

        return new int[]{i, j};
    }
    public static int[] twoSum2(int[] nums, int target) {
        int[] a = new int[2];
        Boolean flag = false;
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                int sum = nums[i]+nums[j];
                if(sum==target){
                    a[0] = i;
                    a[1] = j;
                    flag = true;
                    break;
                }
            }
            if(flag){
                break;
            }
        }
        return a;
    }
}
