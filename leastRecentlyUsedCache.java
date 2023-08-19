import java.util.Map;
import java.util.HashMap;

interface Queue<E>{
    public abstract E peekFront();
    public abstract E popFront();
    public abstract void pushBack(Node<E> node);
}

interface Stack<E>{
    public abstract E peekBack();
    public abstract E popBack();
    public abstract void pushBack(Node<E> node);
}

class Node<E>{
    protected int key;
    protected E data;
    protected Node<E> next;
    protected Node<E> prev;

    public Node(int key, E data){
        this.key = key;
        this.data = data;
    }
}

abstract class AbstractDoublyLinkedList<E> implements Stack<E>, Queue<E>{
    protected Node<E> head;
    protected Node<E> tail;

    public AbstractDoublyLinkedList(){}

    public abstract void deleteNode(Node<E> node);
}

class CacheList<E> extends AbstractDoublyLinkedList<E>{
    public CacheList(){
        super();
    }

    public E peekFront(){
        if (head == null) return null;
        return head.data;
    }

    public E popFront(){
        if (head == null) return null;
        Node<E> deleted = head;
        head = head.next;
        if (head == null) tail = null;
        else head.prev = null;

        return deleted.data;
    }

    public E peekBack(){
        if (tail == null) return null;
        return tail.data;
    }

    public E popBack(){
        if (tail == null) return null;
        Node<E> deleted = tail;
        tail = tail.prev;
        if (tail == null) head = null;
        else tail.next = null;

        return tail.data;
    }

    public void pushBack(Node<E> node){
        if (head == null){
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    public void deleteNode(Node<E> node){
        if (node == null) return;
        else if (node == head) popFront();
        else if (node == tail) popBack();
        else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    public String toString(){
        StringBuilder str = new StringBuilder("[");
        Node<E> iterator = head;
        while (iterator != null){
            str.append("{" + iterator.key + ", " + iterator.data + "} ");
            iterator = iterator.next;
        }

        str.append("]");
        return str.toString();
    }
}

abstract class AbstractLRUCache<E>{
    protected int capacity;
    protected Map<Integer, Node<E>> cacheMap;
    protected CacheList<E> cacheList;

    public AbstractLRUCache(int capacity){
        this.capacity = capacity;
        this.cacheMap = new HashMap<Integer, Node<E>>();
        this.cacheList = new CacheList<E>();   
    }

    public abstract E get(int key);
    public abstract void put(int key, E data);
}

class LRUCache<E> extends AbstractLRUCache<E>{
    public LRUCache(int capacity){
        super(capacity);
    }

    // キャッシュに該当するデータが存在しなければ null を返す。
    // データを取得した場合は、そのキーが最近使用されたことを示すためにキャッシュの状態を更新。
    public E get(int key){
        if (!cacheMap.containsKey(key)) return null;

        Node<E> temp = cacheMap.get(key);
        cacheList.deleteNode(temp);
        temp.prev = null;
        temp.next = null;
        cacheList.pushBack(temp);
        return temp.data;
    }

    public void put(int key, E data){
        // キャッシュ内にkeyが既に存在する場合は、valueの値を更新。
        if (cacheMap.containsKey(key)){
            Node<E> temp = cacheMap.get(key);
            cacheList.deleteNode(temp);
            temp.data = data;
            temp.prev = null;
            temp.next = null;
            cacheList.pushBack(temp);
        } 
        // キャッシュ内にkeyが存在しない場合は、キャッシュに新たなデータを追加。
        else {
            Node<E> temp = new Node<E>(key, data);
            if (capacity > cacheMap.size()){
                cacheMap.put(key, temp);
            } else {
                cacheMap.remove(cacheList.head.key);
                cacheList.popFront();
                cacheMap.put(key, temp);
            }
            cacheList.pushBack(temp);
        }
    }
}

class Main{
    public static void main(String[] args){
        LRUCache<String> cache = new LRUCache<String>(5);
        cache.put(2, "two");
        cache.put(5, "five");
        cache.put(1, "one");
        cache.put(5, "5");
        cache.put(3, "three");
        cache.put(4, "four");
        cache.put(6, "six");

        System.out.println(cache.cacheList);
        System.out.println(cache.get(5));
        System.out.println(cache.cacheList);
    }
}
