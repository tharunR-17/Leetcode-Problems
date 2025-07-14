/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
 import java.lang.Math;
 import java.util.Date;

class Solution {
    public int getDecimalValue(ListNode head) {
        ListNode current=head;
        int sum=0;
        int i=0;
        ListNode temp = head;
        while(temp !=null){
            i++;
            temp=temp.next;
        }
        while(current !=null){
            i--;
            if(current.val==1){
                sum+=(int)Math.pow(2,i);
            }
            current=current.next;
        }
        return sum;
    }
}
