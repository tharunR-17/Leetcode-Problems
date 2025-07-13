class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players); // Sort players by skill
        Arrays.sort(trainers); // Sort trainers by skill

        int count = 0;
        int i = players.length - 1; // Pointer for players, starting from highest skill
        int j = trainers.length - 1; // Pointer for trainers, starting from highest skill

        while (i >= 0 && j >= 0) {
            if (players[i] <= trainers[j]) { // If player can be trained by this trainer
                i--; // Player matched
                j--; // Trainer used
                count++; // Increment match count
            } else {
                i--; // Player too skilled for this trainer, try next player
            }
        }
        return count;
    }
}
