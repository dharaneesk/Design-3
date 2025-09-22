// Time Complexity : O(1) for both get and put
// Space Complexity : O(capacity)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
// For storage of cache we use a hashmap with key and nodes of a LL as value
// We maintain a double linked list so we can traverse in both direction from the reference we get from hashmap (If we use singly LL we cannot access the previous node values to perform delete operation in O(1) time)
//  Whenever a node is accessed we put at the head of the LL ( remove node if it already exist in the map)
// At full capacity we remove the tail node (least recently used node) and delete it from the map alsoo!!

class LRUCache {

    class Node{
        int key; int val;
        Node prev; Node next;

        public Node(int k, int v){
            this.key =k;
            this.val = v;
        }
    }

    Node head;
    Node tail;
    int size;
    HashMap<Integer,Node> map;

    public LRUCache(int capacity) {
        this.size = capacity;
        this.map = new HashMap();
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }

    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }

    private void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        } else{
            Node node = new Node(key,value);
            if(map.size() == size){
                Node lru = tail.prev;
                removeNode(lru);
                map.remove(lru.key);
            }
            map.put(key,node);
            addToHead(node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */