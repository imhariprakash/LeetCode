 import java.util.HashMap;

// @lc code=start
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            int difference = target - nums[i];

            if(map.containsKey(difference) && map.get(difference) != i){
                result[0] = map.get(difference);
                result[1] = i;
                break;
            }
            else{
                map.put(nums[i], i);
            }
        }
        return result;
    }
}
