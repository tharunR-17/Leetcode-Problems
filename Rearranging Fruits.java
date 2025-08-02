class Solution {
    public long minCost(int[] b1, int[] b2) {
        // 1. Create two "counters" (Maps) to see how many of each fruit are in each basket. 🍎🍌🍇
        Map<Integer, Integer> m1 = new HashMap<>(), m2 = new HashMap<>();

        // 2. Count the fruits in basket 1 (b1).  "x" is each fruit, and we're keeping track of the count.
        //    If we see a fruit for the first time, it's count is 1.  Otherwise, we add 1 to the existing count.
        for (int x : b1) m1.put(x, m1.getOrDefault(x, 0) + 1);

        // 3. Do the same counting for basket 2 (b2).
        for (int x : b2) m2.put(x, m2.getOrDefault(x, 0) + 1);

        // 4. Create a set "all" that contains ALL the *different* types of fruit in either basket. 🍉🍓🍍
        //    It's like merging your fruit list with your friend's to see ALL the available options.
        Set<Integer> all = new HashSet<>(m1.keySet());
        all.addAll(m2.keySet());

        // 5. Find the *cheapest* fruit (the fruit with the lowest "cost"). We'll use this for sneaky cheap swaps later! 🤑
        int min = Integer.MAX_VALUE;

        // 6. Create two lists to store the fruits we need to *move* from one basket to the other. 🚚
        List<Integer> l1 = new ArrayList<>(), l2 = new ArrayList<>();

        // 7. Check each type of fruit in the combined list "all".
        for (int x : all) {
            // 8. Update the "min" to find the cheapest fruit price. 
            min = Math.min(min, x);

            // 9. Calculate the difference in the number of fruits "x" between the two baskets.
            int d = m1.getOrDefault(x, 0) - m2.getOrDefault(x, 0);

            // 10. If the total number of fruits "x" is *odd*, we can't balance the baskets. Return -1. 🙅‍♀️
            if (d % 2 != 0) return -1;

            // 11. If we have *more* of fruit "x" in basket 1 (d > 0), add it to list "l1".
            if (d > 0) for (int i = 0; i < d/2; i++) l1.add(x);

            // 12. If we have *more* of fruit "x" in basket 2 (d < 0), add it to list "l2".
            else if (d < 0) for (int i = 0; i < -d/2; i++) l2.add(x);
/*


Okay, why `i < d/2` and `i < -d/2`:

These lines tell **how many fruits to move from one basket to another**.

* `d = m1.getOrDefault(x, 0) - m2.getOrDefault(x, 0)`: The difference in the number of fruits `x` between the baskets.

* `i < d/2`: If `d` is positive (the first basket has more fruit `x`), move `d/2` pieces from the first to the second.

* `i < -d/2`: If `d` is negative (the second basket has more fruit `x`), move `-d/2` pieces from the second to the first.

**That's it! 🚀**

*/        
}

        // 13. Sort the lists of fruits we need to move.  "l1" in ascending order, "l2" in descending order. 🗂️
        //     Why? We want to match the *expensive* fruits in one basket with the *cheapest* ones in the other.
        Collections.sort(l1);
        Collections.sort(l2, Collections.reverseOrder());
/*
**Why sort `l1` and `l2` differently?**

To **profitably** change fruits.

* `l1` (to give from basket 1): Sort by **ascending** (from cheap to expensive).
* `l2` (to give from basket 2): Sort by **descending** (from expensive to cheap).

** Why is this so?**

We want to:
1. The most expensive fruit to be given from basket 1 collided with the most expensive fruit to be given from basket 2.
2. Then we compare:
    * The cost of direct exchange of these two fruits.
    * The cost of exchanging through the cheapest fruit ('2 * min`).

**Thus, we ensure that we always choose the most profitable option for each exchange.**

why colections?

`Collections.sort()` is used to sort `ArrayList`. And `Arrays.sort()` is for regular arrays (like `int[]`).
*/

        // 14. Calculate the *minimum cost* to balance the baskets by swapping the fruits. 💰
        long res = 0;
        for (int i = 0; i < l1.size(); i++)
            // 15. For each pair of fruits, find the cheaper option:
            //     - Swapping them directly (min of l1.get(i) and l2.get(i)).
            //     - Swapping them using the *cheapest* fruit as an intermediary (2 * min).
            res += Math.min((long)Math.min(l1.get(i), l2.get(i)), 2L * min);

/*
why 2L * min?
Imagine you have two coins that need to be exchanged: 5 rubles and 10 rubles.

And there is also a "magic" coin of 2 rubles.

`res += Math.min((long)Math.min(l1.get(i), l2.get(i)), 2L * min);` – this is like a choice:

1. **Direct exchange:** Exchange coins of 5 and 10 rubles. Cost = the smallest coin (5 rubles). `Math.min(l1.get(i), l2.get(i))`
2. **Through the "magic":** Exchange 5 rubles for the magic one (2 rubles), then the magic one for 10 rubles. Cost = 2 + 2 = 4 rubles. `2L * min`

This line decides:

*What is cheaper: to exchange coins directly, or through "magic"?* And adds this cost to the total.
*/

        // 16. Return the total minimum cost. 🎉
        return res;
    }
}
