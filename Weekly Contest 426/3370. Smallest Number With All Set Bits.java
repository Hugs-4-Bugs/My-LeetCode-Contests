class Solution {
    public int smallestNumber(int n) {
        int result = n;
        while((result & (result + 1)) !=0){
            result++;
        }
        return result;
        
    }
}
