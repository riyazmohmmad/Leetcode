class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        findCombinations(candidates, target, 0, new ArrayList<>(), ans);
        return ans;
    }

    void findCombinations(int[] arr, int target, int index,
                          List<Integer> list, List<List<Integer>> ans) {

        if (target == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }

        if (index == arr.length || target < 0) {
            return;
        }

        // Pick the current element (can be used again)
        list.add(arr[index]);
        findCombinations(arr, target - arr[index], index, list, ans);

        // Backtrack: remove the picked element
        list.remove(list.size() - 1);

        // Skip the current element
        findCombinations(arr, target, index + 1, list, ans);
    }
}