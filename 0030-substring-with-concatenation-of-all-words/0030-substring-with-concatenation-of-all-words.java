class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
         List<Integer> result = new ArrayList<>();

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        // Frequency of each word
        HashMap<String, Integer> need = new HashMap<>();

        for (String word : words) {
            need.put(word, need.getOrDefault(word, 0) + 1);
        }

        // Try each possible starting offset
        for (int start = 0; start < wordLen; start++) {

            int left = start;
            int count = 0;

            HashMap<String, Integer> seen = new HashMap<>();

            for (int right = start;
                 right + wordLen <= s.length();
                 right += wordLen) {

                String word = s.substring(right, right + wordLen);

                // Word is not present in words
                if (!need.containsKey(word)) {
                    seen.clear();
                    count = 0;
                    left = right + wordLen;
                    continue;
                }

                seen.put(word, seen.getOrDefault(word, 0) + 1);
                count++;

                // Too many copies of this word
                while (seen.get(word) > need.get(word)) {
                    String leftWord = s.substring(left, left + wordLen);
                    seen.put(leftWord, seen.get(leftWord) - 1);
                    left += wordLen;
                    count--;
                }

                // All words found
                if (count == wordCount) {
                    result.add(left);

                    String leftWord = s.substring(left, left + wordLen);
                    seen.put(leftWord, seen.get(leftWord) - 1);
                    left += wordLen;
                    count--;
                }
            }
        }

        return result;
    }
}