class Solution1 {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> wordCounts = new HashMap<>();
        for (String word : words)
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);

        PriorityQueue<String> minHeap = new PriorityQueue<>((s1, s2) -> {
            int s1Count = wordCounts.get(s1);
            int s2Count = wordCounts.get(s2);

            if (s1Count == s2Count)
                return s2.compareTo(s1);
            return s1Count - s2Count;
        });

        for (String word : wordCounts.keySet()) {
            minHeap.offer(word);

            if (minHeap.size() > k)
                minHeap.poll();
        }

        LinkedList<String> toReturn = new LinkedList<>();
        while (!minHeap.isEmpty())
            toReturn.addFirst(minHeap.poll());

        return toReturn;
    }}

    #**692. 

    Top K
    Frequent Words**

    Given an
    array of strings `words` 
    and an integer `k`,return *the* `k` *most frequent strings*.

Return the answer **sorted** by **the frequency** 
    from highest
    to lowest.
    Sort the
    words with
    the same
    frequency by their **lexicographical order**.

    **Example 1:**

    ```Input:words=["i","love","leetcode","i","love","coding"],k=2 Output:["i","love"]Explanation:"i"and"love"
    are the
    two most
    frequent words.
    Note that"i"
    comes before"love"
    due to
    a lower
    alphabetical order.

    ```

    **Example 2:**

    ```Input:words=["the","day","is","sunny","the","the","the","sunny","is","is"],k=4 Output:["the","is","sunny","day"]Explanation:"the","is","sunny"and"day"
    are the
    four most
    frequent words, with
    the number
    of occurrence being 4,3,2 and 1 respectively.

    ```

    **Constraints:**

    -`1<=words.length<=500`-`1<=words[i].length<=10`-`words[i]` 
    consists of
    lowercase English letters.-`k` 
    is in
    the range `[1,
    The number of**unique**words[i]]`

    **Follow-up:** 
    Could you
    solve it in `O(n log(k))` time and `O(n)` extra space?

```java
class Solution {
  public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> wordCounts = new HashMap<>();
        for(String word : words) wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        
        PriorityQueue<String> minHeap = new PriorityQueue<>((s1, s2) -> {
            int s1Count = wordCounts.get(s1);
            int s2Count = wordCounts.get(s2);
            
            if(s1Count == s2Count) return s2.compareTo(s1);
            return s1Count - s2Count;
        });
        
        for(String word : wordCounts.keySet()) {
            minHeap.offer(word);
            
            if(minHeap.size() > k) minHeap.poll(); 
        }
        
        LinkedList<String> toReturn = new LinkedList<>();
        while(!minHeap.isEmpty()) toReturn.addFirst(minHeap.poll());
        
        return toReturn;
    }
}
```

```
class Solution2 {
  public List<String> topKFrequent(String[] words, int k) {

        // map hold the word: counts
        HashMap<String, Integer> map = new HashMap();

        // sort the map by frequency high->low order, sort words lexi order
        PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<>(
            (a,b)->{
                if(a.getValue() != b.getValue())
                    return a.getValue().compareTo(b.getValue());
                return -a.getKey().compareTo(b.getKey());
            }
        );

        // fill the map
        for(String word: words){
            map.merge(word, 1, Integer::sum);
        }

        // put into heap
        for(Map.Entry<String, Integer> entry: map.entrySet()){
            heap.offer(entry);
            if(heap.size() > k)
                heap.poll();
        }

        // pop out the answer
        List<String> ans = new ArrayList();
        while(heap.size() > 0)
            ans.add(heap.poll().getKey());

        // check the order
        Collections.reverse(ans);
        return ans;
    }
}
```