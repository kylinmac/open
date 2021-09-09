package mc.open.leetcode;

import java.util.*;

/**
 * @author macheng
 * @date 2021/9/8 15:04
 */
public class FindMaximizedCapital_502 {

    public static void main(String[] args) {
        PriorityQueue<Integer> p2 = new PriorityQueue<>();
        for (int i = 0; i < 10; i++) {
            p2.add(i);
        }
        p2.remove(3);
        System.out.println(p2);
        for (int i = 0; i < 10; i++) {
            System.out.println(p2.poll());
        }
    }

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {

        PriorityQueue<MyMap> priorityQueue1 = new PriorityQueue<>((o1, o2) -> o2.pofit-o1.pofit);
        TreeSet<MyMap> myMaps=new TreeSet<>();
        PriorityQueue<Integer> p2 = new PriorityQueue<>();
        for (int i = 0; i < profits.length; i++) {
            priorityQueue1.add(new MyMap(profits[i],capital[i]));
        }
        A:
        for (int i = 0; i < k; i++) {

            Iterator<MyMap> iterator = myMaps.iterator();
            while (iterator.hasNext()&&p2.peek()<=w){
                MyMap poll = iterator.next();
                if (poll.captial<=w){
                    iterator.remove();
                    w+=poll.pofit;
                    p2.remove(poll.captial);
                    continue A;
                }
            }

            while (!priorityQueue1.isEmpty()){
                MyMap poll = priorityQueue1.poll();
                if (poll.captial<=w){
                    w+=poll.pofit;
                    continue A;
                }else {
                    p2.add(poll.captial);
                    myMaps.add(poll);
                    System.out.println(myMaps.size());
                }
            }

        }
        return w;

    }

    class MyMap implements Comparable<MyMap>{

        int pofit;
        int captial;

        public MyMap(int pofit, int captial) {
            this.pofit = pofit;
            this.captial = captial;
        }


        @Override
        public boolean equals(Object o) {

            return this==o;
        }

        @Override
        public int hashCode() {
            return Objects.hash(pofit, captial);
        }

        @Override
        public int compareTo(MyMap o) {
            int i = o.pofit - this.pofit;
            return i==0?1:i;
        }
    }

}
