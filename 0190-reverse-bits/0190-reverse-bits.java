public class Solution {
    public int reverseBits(int n) {
        int result = 0;

        for (int i = 0; i < 32; i++) {
            result <<= 1;          // shift left
            result |= (n & 1);     // add last bit
            n >>= 1;               // move to next bit
        }

        return result;
    }
}