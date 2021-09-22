package mc.open.leetcode;

/**
 * @author macheng
 * @date 2021/9/22 9:05
 */
public class SplitListToParts_725 {

    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode cur=head;
        ListNode next;
        int length=0;
        while (cur.next!=null){
            cur=cur.next;
            length++;
        }
        ListNode[] result=new ListNode[k];
        if (length<=k){
            cur=head;
            int i=0;
            while (cur!=null){
                result[i]=cur;
                next=cur.next;
                cur.next=null;
                cur=next;
                i++;
            }

            return result;
        }

        int more = length % k;

        int one = length / k;
        cur=head;

        for (int i = 0; i < k; i++) {
            result[i]=cur;
            for (int j = 0; j < one-1; j++) {
                cur=cur.next;
            }
            if (more>0){
                more--;
                cur=cur.next;
            }
            next=cur.next;
            cur.next=null;
            cur=next;
        }
        return result;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
