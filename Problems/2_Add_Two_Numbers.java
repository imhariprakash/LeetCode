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

 /*

1. Maintaining a seperate head and tail gives an advantage : no need to traverse each time to add a new value by searching null(tail)
2. sum(sum_carry) : when sum is greater than 9 (as we are adding single digits - max carry is 1) - sum is zeroth position value and carry is one th position value
                    meaning digit at ones is sum and carry is digit at tens position 

                    2  4  3                 ------->   3  4  2 
                    5  6  4                 ------->   4  6  5
                   ---------                           ----------
                    7  0  8                            8  0  7   (normal addition)     ------> 7 0 8 (reverse to get answer)
                   ---------                           ----------
                instead of reversing 
                add the carry rightwards

3. first while   ->   [1 1 1 1 1] [2 2 2 2]     --------> [3 3 3 3 1]   (add both digits until anyone goes to null)
   second while  ->   when only one digit goes null another have digits just place it as it is that's the reason for while 2 and 3
 */


class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode[] head_tail = {null, null};  
        int[] sum_carry = {0, 0};

        while(l1 != null && l2 != null){                         // until any one goes to null
            sum_carry[0] = l1.val + l2.val + sum_carry[1];
            
            sum(sum_carry);
            
            addNode(head_tail, sum_carry[0]);   
            l1 = l1.next;
            l2 = l2.next;
        }
        
        while(l1 != null){                                      // when l1 is not null yet
            sum_carry[0] = l1.val + sum_carry[1];
            
            sum(sum_carry);
            
            addNode(head_tail, sum_carry[0]);
            l1 = l1.next;
        }
        
        while(l2 != null){                                    // when l2 is not null yet
            sum_carry[0] = l2.val + sum_carry[1];
            
            sum(sum_carry);
            
            addNode(head_tail, sum_carry[0]);
            l2 = l2.next;
        }
        
        if(sum_carry[1] != 0){                               // at the end carry may not be 0 - need to put it at the end as we put at first in normal addition(11 + 89 = 100)
            addNode(head_tail, sum_carry[1]);
        }
        return head_tail[0];
    }
    
    public static void sum(int[] sum_carry){
        if(sum_carry[0] > 9){                                 // sum is > 9 -> say 12 (sum = 2 and carry = 1)
                sum_carry[1] = sum_carry[0] / 10;         
                sum_carry[0] = sum_carry[0] % 10;
            }
        else{
            sum_carry[1] = 0;
        }
    }
    
    public static void addNode(ListNode[] head_tail, int value){        // add to tail and update tail
        
        ListNode temp = new ListNode(value);        
        
        if(head_tail[0] == null){
            head_tail[0] = temp;
            head_tail[1] = head_tail[0];
        }
        else{
            head_tail[1].next = temp;
            head_tail[1] = head_tail[1].next;
        }
    }
}

/***************************************************************** Not a good way to do this - break problems do sub problems *************************

// /**
//  * Definition for singly-linked list.
//  * public class ListNode {
//  *     int val;
//  *     ListNode next;
//  *     ListNode() {}
//  *     ListNode(int val) { this.val = val; }
//  *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
//  * }
//  */

/*

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;
        int sum = 0;
        int carry = 0;

        while(l1 != null && l2 != null){
            sum = l1.val + l2.val + carry;
            carry = 0;
            if(sum > 9){
                carry = sum / 10;
                sum = sum % 10;
            }
            
            if(head == null){
                head = new ListNode(sum);
                tail = head;
            }  
            else{
                tail.next = new ListNode(sum);
                tail = tail.next;
            }
            
            l1 = l1.next;
            l2 = l2.next;
        }
        
        while(l1 != null){
            sum = l1.val + carry;
            carry = 0;
            if(sum > 9){
                carry = sum / 10;
                sum = sum % 10;
            }
            tail.next = new ListNode(sum);
            tail = tail.next;
            l1 = l1.next;
        }
        
        while(l2 != null){
            sum = l2.val + carry;
            carry = 0;
            if(sum > 9){
                carry = sum / 10;
                sum = sum % 10;
            }
            tail.next = new ListNode(sum);
            tail = tail.next;
            l2 = l2.next;
        }
        
        if(carry != 0){
            tail.next = new ListNode(carry);
        }
        return head;
    }
    
}

*/

/*


//  * Definition for singly-linked list.
//  * public class ListNode {
//  *     int val;
//  *     ListNode next;
//  *     ListNode() {}
//  *     ListNode(int val) { this.val = val; }
//  *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
//  * }


class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sum_linked_list = null;
        int carry = 0;
        int sum = 0;
        while(l1 != null && l2 != null){
            sum = l1.val + l2.val + carry;
            carry = 0;
            if(sum > 9){
                carry = sum / 10;
                sum = sum % 10;
            }
            sum_linked_list = addNode(sum_linked_list, sum);   
            l1 = l1.next;
            l2 = l2.next;
        }
        
        while(l1 != null){
            sum = l1.val + carry;
            carry = 0;
            if(sum > 9){
                carry = sum / 10;
                sum = sum % 10;
            }
            sum_linked_list = addNode(sum_linked_list, sum);
            l1 = l1.next;
        }
        
        while(l2 != null){
            sum = l2.val + carry;
            carry = 0;
            if(sum > 9){
                carry = sum / 10;
                sum = sum % 10;
            }
            sum_linked_list = addNode(sum_linked_list, sum);
            l2 = l2.next;
        }
        
        if(carry != 0){
            sum_linked_list = addNode(sum_linked_list, carry);
        }
        return sum_linked_list;
    }
    
    public static ListNode addNode(ListNode list, int value){
        ListNode head = list;
        ListNode temp = new ListNode(value);
        
        
        if(list == null){
            head = temp;
        }
        else{
            while(list.next != null){
                list = list.next;
            }
            list.next = temp;
        }
        
        return head;
    }
}

*/