package mc.open.leetcode;

/**
 * @author macheng
 * @date 2021/10/19 8:56
 */
public class WordDictionary_211 {

    public WordDictionary_211() {

    }

    Node[] head=new Node[26];


    class Node{
        int way;
        int end;
        Node[] next;
    }
    public void addWord(String word) {
        Node[] cur=head;
        for (int i = 0;  i< word.length()-1; i++) {
            int c = word.charAt(i)-'a';
            if (cur[c]==null){
                cur[c]=new Node();
                cur[c].next=new Node[26];
            }
            cur[c].way++;
            cur=cur[c].next;
        }
        int c = word.charAt(word.length()-1)-'a';
        if (cur[c]==null){
            cur[c]=new Node();
            cur[c].next=new Node[26];
        }
        cur[c].end++;
    }

    public boolean search(String word) {
        return search(head,word,0);
    }


    public boolean search(Node[] cur, String word , int index ){
        if (index==word.length()-1){
            if (word.charAt(index)=='.'){
                for (Node node : cur) {
                    if (node!=null&&node.end>0){
                        return true;
                    }
                }
                return false;
            }
            if (cur[word.charAt(index)-'a']!=null&&cur[word.charAt(index)-'a'].end>0){
                return true;
            }else {
                return false;
            }
        }

        if (word.charAt(index)=='.'){
            boolean search=false;
            for (Node node : cur) {
                if(node!=null)
                    search =  search||search(node.next,word,index+1) ;
            }
            return search;
        }
        if (cur[word.charAt(index)-'a']!=null&&cur[word.charAt(index)-'a'].way>0){
            return search(cur[word.charAt(index)-'a'].next,word,index+1);
        }else {
            return false;
        }

    }
}
