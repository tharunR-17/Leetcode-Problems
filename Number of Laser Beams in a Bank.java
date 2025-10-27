class Solution {
    public int numberOfBeams(String[] bank) {
        if (bank == null || bank.length == 0) {
            return 0;
        }
        
        int totalBeams = 0;
        int prevRowDevices = countDevices(bank[0]);
        
        for (int i = 1; i < bank.length; i++) {
            int currentRowDevices = countDevices(bank[i]);
            
            if (currentRowDevices == 0) {
                continue;
            }
            
            totalBeams += prevRowDevices * currentRowDevices;
            prevRowDevices = currentRowDevices;
        }
        
        return totalBeams;
    }
    
    private int countDevices(String row) {
        int deviceCount = 0;
        for (char cell : row.toCharArray()) {
            if (cell == '1') {
                deviceCount++;
            }
        }
        return deviceCount;
    }
}
