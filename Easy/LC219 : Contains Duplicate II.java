//USING HASHMAP :
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        //Create a hashmap to store value of nums with their index.
        HashMap<Integer,Integer> map = new HashMap<>();

        for(int i = 0; i<nums.length; i++){
            // If nums[i] not present put it normally.
            if(map.get(nums[i]) == null)
                map.put(nums[i],i);
            else{
                //If already present get the value first
                Integer in = map.get(nums[i]);
                //Check out condition after getting the value
                //If satisfied return true here.
                if(Math.abs(in-i) <= k) return true;
                //If above statement return false it means we have
                // larger value than k, we need to update the value till now.
                map.put(nums[i],i);
            }
        }
        return false;
    }
}

//SLIDING WINDOW USING HASHSET !
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        
        // It means both i,j are same.
        if(k == 0) return false;
        
        HashSet<Integer> set = new HashSet<>();
        
        for(int i = 0; i<nums.length; i++){
            // Check if already present in set
            // Only that element is present in set who fulfil the given condition
            // abs(i-j) <=k
            if(set.contains(nums[i]))
                return true;
            // We are removing the elements who are unable to fulfil the given condition.
            if(i >= k)
                set.remove(nums[i-k]);
            // finally add the element
            set.add(nums[i]);
            
        }
        return false;
    }
}
