package LeetCode;

/**
 134. Gas Station

 There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

 You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

 Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

 Note:
 The solution is guaranteed to be unique.
 */
public class H_Leetcode_134 {
    /**
     * @param gas
     * @param cost
     * @return
     *
     * general idea is, if the total_gas >= total_cost, then there should be a solution, otherwise just return -1;
     * and iterate from 0 to n - 1, for each station, we calculate gas and cost for the journey. Let's say if at i station,
     * we first find that gas < cost, then it means in [0, i], there is no proper start point.
     * Reason is simple, when we do station[0] -> station[1], we have some gas remaining (>= 0), so if we start at station[1] instead,
     * the situation will become even worse. So we find this kind of i, we can refresh gas & cost to 0, and start at i + 1.
     * Until we can find a start point k, which can carry us to n - 1; then return k;
     *
     * Time - O(n)
     * Space - O(1)
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int gas_total = 0;
        int cost_total = 0;
        for(int i = 0; i < gas.length; i++){
            gas_total += gas[i];
            cost_total += cost[i];
        }
        if(gas_total < cost_total) return -1;

        gas_total = 0;
        cost_total = 0;
        int res = 0;
        for(int i = 0; i < gas.length; i++){
            gas_total += gas[i];
            cost_total += cost[i];
            if(gas_total < cost_total){
                res = i + 1;
                gas_total = 0;
                cost_total = 0;
            }
        }

        return res;
    }
}
