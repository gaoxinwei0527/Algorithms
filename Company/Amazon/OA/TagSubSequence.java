package Company.Amazon.OA;

import java.util.*;

/**
 Given two list of strings - targetList & availableTagList
 return start & end index of minimal window in availableTagList, which includes all tags in targetList

 if multiple minimal windows exist, return the minimal lexi order one
 if no such window, return int[0]
 targetList has no duplicate tags
 string matching is case insensitive
 */
public class TagSubSequence {
    public List<Integer> subSequenceTags(List<String> targetList, List<String> availableTagList){
        int idx1 = -1;
        int idx2 = -1;
        int len = Integer.MAX_VALUE;
        Map<String, Integer> window = new HashMap<>();
        for(String tag : targetList){
            window.put(tag.toLowerCase(), 0);
        }

        int count = 0;
        int i = 0;
        int j = 0;
        while(j < availableTagList.size()){
            while(j < availableTagList.size() && count < targetList.size()){
                String next = availableTagList.get(j).toLowerCase();
                if(window.containsKey(next)){
                    if(window.get(next) == 0) count++;
                    window.put(next, window.get(next) + 1);
                }
                j++;
            }

            if(count < targetList.size()) break;
            while(i < j && (!window.containsKey(availableTagList.get(i).toLowerCase()) || window.get(availableTagList.get(i).toLowerCase()) > 1)){
                String next = availableTagList.get(i).toLowerCase();
                if(window.containsKey(next)) window.put(next, window.get(next) - 1);
                i++;
            }

            if(j - i < len){
                len = j - i;
                idx1 = i;
                idx2 = j - 1;
            }

            window.put(availableTagList.get(i).toLowerCase(), window.get(availableTagList.get(i).toLowerCase()) - 1);
            i++;
            count--;
        }

        List<Integer> res = new ArrayList<>();
        if(idx1 == -1) return res;
        res.add(idx1);
        res.add(idx2);
        return res;
    }

    public static void main(String[] args){
        TagSubSequence tagSubSequence = new TagSubSequence();
        List<Integer> res = tagSubSequence.subSequenceTags(
                Arrays.asList("made", "in", "Spain"),
                Arrays.asList("made", "weather", "forcast", "says", "that", "made", "rain", "in", "spain", "stays"));
        System.out.println(res);

        res = tagSubSequence.subSequenceTags(
                Arrays.asList("2abc", "bcd", "cab"),
                Arrays.asList("dbc", "2abc", "cab", "bcd", "bcb"));
        System.out.println(res);

        res = tagSubSequence.subSequenceTags(
                Arrays.asList("in", "the", "spain"),
                Arrays.asList("the", "spain", "that", "the", "rain", "in", "spain", "stays", "forcast", "in", "the"));
        System.out.println(res);
    }
}
