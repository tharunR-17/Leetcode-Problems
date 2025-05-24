class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < words.length; i++){
            for(int j = 0 ; j < words[i].length(); j++){
                if(x == words[i].charAt(j)){
                    res.add(i);
                    break;
                }else{
                    continue;
                }
            }
        }
        return res;
    }
}
