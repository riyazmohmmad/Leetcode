class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);

        backtrack(candidates, target, 0, new ArrayList<>(), ans);

        return ans;
    }

    private void backtrack(int[] arr, int target, int start,
                           List<Integer> current, List<List<Integer>> ans) {

        if (target == 0) {
            ans.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < arr.length; i++) {

            // Skip duplicates at the same recursion level
            if (i > start && arr[i] == arr[i - 1]) {
                continue;
            }

            // Since the array is sorted, no further candidate can work
            if (arr[i] > target) {
                break;
            }

            current.add(arr[i]);

            // Move to i + 1 because each number can be used only once
            backtrack(arr, target - arr[i], i + 1, current, ans);

            // Backtrack
            current.remove(current.size() - 1);
        }
    }
}