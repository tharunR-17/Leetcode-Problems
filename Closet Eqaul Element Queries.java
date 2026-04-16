class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            List<Integer> list = map.getOrDefault(nums[i], new ArrayList<>());
            list.add(i);
            map.put(nums[i], list);
        }
        
        for (int i = 0; i < queries.length; i++) {
            int queryIndex = queries[i];
            int targetValue = nums[queryIndex];
            List<Integer> indices = map.get(targetValue);
            
            if (indices.size() == 1) {
                res.add(-1);
                continue;
            }
            
            int pos = binSearch(indices, queryIndex);
            int size = indices.size();
            int prevIndex = indices.get((pos - 1 + size) % size);
            int nextIndex = indices.get((pos + 1) % size);
            
            int distPrev = Math.min(Math.abs(queryIndex - prevIndex), n - Math.abs(queryIndex - prevIndex));
            int distNext = Math.min(Math.abs(queryIndex - nextIndex), n - Math.abs(queryIndex - nextIndex));
            
            res.add(Math.min(distPrev, distNext));
        }
        
        return res;
    }
    
    private int binSearch(List<Integer> list, int target) {
        int left = 0;
        int right = list.size() - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midVal = list.get(mid);
            
            if (midVal == target) {
                return mid;
            } else if (midVal < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
}
