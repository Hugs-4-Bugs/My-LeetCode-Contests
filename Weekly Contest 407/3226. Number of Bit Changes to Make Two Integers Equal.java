class Solution {
    public int minChanges(int n, int k) {
        String binaryN = Integer.toBinaryString(n);
        String binaryK = Integer.toBinaryString(k);
        
        // Make the lengths of the two binary strings equal by adding leading zeros
        int maxLength = Math.max(binaryN.length(), binaryK.length());
        binaryN = String.format("%" + maxLength + "s", binaryN).replace(' ', '0');
        binaryK = String.format("%" + maxLength + "s", binaryK).replace(' ', '0');
        
        int count = 0;
        boolean possible = true;
        for (int i = 0; i < maxLength; i++) {
            if (binaryN.charAt(i) != binaryK.charAt(i)) {
                if (binaryN.charAt(i) == '0') {
                    possible = false;
                    break;
                } else {
                    count++;
                }
            }
        }
        
        return possible ? count : -1;
    }
}
