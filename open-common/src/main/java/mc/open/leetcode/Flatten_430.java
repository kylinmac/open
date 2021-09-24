package mc.open.leetcode;


import java.util.Stack;

/**
 * @author macheng
 * @date 2021/9/24 9:09
 */
public class Flatten_430 {
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    };

    public Node flatten(Node head) {
        Stack<Node> stack=new Stack<>();
        Node before=head;
        stack.push(head);
        while (!stack.empty()){
            Node pop = stack.pop();

            if (pop.next!=null){
                stack.push(pop.next);
            }
            if (pop.child!=null){
                stack.push(pop.child);
                pop.child=null;
            }
            before.next=pop;
            pop.prev=before;
            before=before.next;
        }
        head.prev=null;
        return head;
    }
}
