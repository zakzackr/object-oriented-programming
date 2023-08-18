import java.util.Arrays;
import java.util.ArrayList;

interface Queue<E>{
    public abstract E peekFront();
    public abstract E popFront();
    public abstract void pushBack(E element);
}

interface Stack<E>{
    public abstract E peekBack();
    public abstract E popBack();
    public abstract void pushBack(E element);
}

interface Deque<E> extends Stack<E>, Queue<E>{
    public abstract void pushFront(E element);
}

abstract class GenericAbstractList<E> implements Deque<E>{
    protected E[] initialList;

    public GenericAbstractList(){};  
    
    public GenericAbstractList(E[] arr){
        this.initialList = arr;
    }

    public E[] getInitialList(){
        return initialList;
    }

    public abstract E get(int index);  
    public abstract void add(E element); 
    public abstract void add(E[] elements); 
    public abstract E pop();
    public abstract void addAt(int index, E element);
    public abstract void addAt(int index, E[] elements);
    public abstract E removeAt(int index);
    public abstract void removeAllAt(int start);
    public abstract void removeAllAt(int start, int end);
    public abstract GenericAbstractList<E> subList(int start); 
    public abstract GenericAbstractList<E> subList(int start, int end); 
}

class GenericArrayList<E> extends GenericAbstractList<E>{
    private ArrayList<E> arrayList;

    public GenericArrayList(){
        super();
        this.arrayList = new ArrayList<E>();
    }

    public GenericArrayList(E[] arr){
        super(arr);
        this.arrayList = new ArrayList<E>(Arrays.asList(arr));
    }

    public E peekFront(){
        if (arrayList.size() == 0) return null;
        return arrayList.get(0);
    }

    public E popFront(){
        if (peekFront() == null) return null;
        return arrayList.remove(0);
    }

    public E peekBack(){
        if (peekFront() == null) return null;
        return arrayList.get(arrayList.size() - 1);
    }

    public E popBack(){
        if (peekBack() == null) return null;
        return arrayList.remove(arrayList.size() - 1);
    }

    public void pushFront(E element){
        arrayList.add(0, element);
    }

    public void pushBack(E element){
        arrayList.add(element);
    }

    public E get(int index){
        return arrayList.get(index);
    }

    public void add(E element){
        arrayList.add(element);
    }

    public void add(E[] elements){
        arrayList.addAll(Arrays.asList(elements));
    }

    public E pop(){
        return popBack();
    }

    public void addAt(int index, E element){
        arrayList.add(index, element);
    }

    public void addAt(int index, E[] elements){
        arrayList.addAll(index, Arrays.asList(elements));
    }

    public E removeAt(int index){
        return arrayList.remove(index);
    }

    public void removeAllAt(int start){
        arrayList.subList(start, arrayList.size()).clear();
    }

    public void removeAllAt(int start, int end){
        arrayList.subList(start, end).clear();
    }

    public GenericAbstractList<E> subList(int start){
        GenericAbstractList<E> list = new GenericArrayList<E>();
        for (int i = start; i < arrayList.size(); i++){
            list.add(arrayList.get(i));
        }

        return list;
    }

    public GenericAbstractList<E> subList(int start, int end){
        GenericAbstractList<E> list = new GenericArrayList<E>();
        for (int i = start; i < end; i++){
            list.add(arrayList.get(i));
        }

        return list;
    }

    public String toString(){
        StringBuilder str = new StringBuilder("[");
        for (int i = 0; i < arrayList.size(); i++){
            str.append(arrayList.get(i) + " ");
        }
        str.append("]");
        return str.toString();
    }
}

class Node<E>{
    public E data;
    public Node<E> next;
    public Node<E> prev;

    public Node(E data){
        this.data = data;
    }
}

class GenericLinkedList<E> extends GenericAbstractList<E>{
    private Node<E> head;
    private Node<E> tail;

    public GenericLinkedList(){
        super();
    }

    public E peekFront(){
        if (head == null) return null;
        return head.data;
    }

    public E popFront(){
        if (head == null) return null;
        Node<E> temp = head;
        head = head.next;
        if (head == null) tail = null;
        else head.prev = null;

        return temp.data;
    }

    public E peekBack(){
        if (tail == null) return null;
        return tail.data;
    }

    public E popBack(){
        if (tail == null) return null;
        Node<E> temp = tail;
        tail = tail.prev;
        if (tail == null) head = null;
        else tail.next = null;

        return temp.data;
    }
    
    public void pushFront(E element){
        Node<E> newNode = new Node<E>(element) ;
        if (head == null){
            head = newNode;
            tail = head;
        } else {
            head.prev = newNode;
            newNode.next = head;
            // newNode.prev = null;
            head = newNode;
        }
    }

    public void pushBack(E element){
        Node<E> newNode = new Node<E>(element);
        if (tail == null){
            tail = newNode;
            head = tail;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            // newNode.next = null;
            tail = newNode;
        }
    }

    public Node<E> at(int index){
        if (index < 0 || head == null) return null;
        Node<E> iterator = head;
        for (int i = 0; i < index; i++){
            iterator = iterator.next;
            if (iterator == null) return null;
        }

        return iterator;
    }

    public E get(int index){
        if (at(index) == null) return null;
        else return at(index).data;
    }  

    public void add(E element){
        pushBack(element);
    }

    public void add(E[] elements){
        for (E elem: elements) pushBack(elem);
    }

    public E pop(){
        return popBack();
    }

    public void addAt(int index, E element){
        if (index < 0) return;
        else if (index == 0) pushFront(element);
        else if (at(index - 1) == tail){
            pushBack(element);
        }
        else {
            Node<E> target = at(index - 1);
            Node<E> temp = target.next;
            Node<E> newNode= new Node<E>(element);
            target.next = newNode;
            newNode.prev = target;
            newNode.next = temp;
            temp.prev = newNode;
        }
    }

    public void addAt(int index, E[] elements){
        if (index < 0) return;
        for (int i = elements.length - 1; i >= 0; i--){
            addAt(index, elements[i]);
        }
    }

    public E removeAt(int index){
        Node<E> deleted = at(index);
        if (deleted == null) return null;
        else if (deleted == head) popFront();
        else if (deleted == tail) popBack();
        else {
            deleted.prev.next = deleted.next;
            deleted.next.prev = deleted.prev;
        }

        return deleted.data;
    }

    public void removeAllAt(int start){
        Node<E> startNode = at(start);
        if (startNode != null){
            tail = startNode.prev;
            if (tail != null) tail.next = null;
            else head = tail;
        }
    }

    public void removeAllAt(int start, int end){
        Node<E> startNode = at(start);
        Node<E> endNode = at(end);

        if (startNode == null) return;
        else if (endNode == null) removeAllAt(start);
        else {
            startNode.prev.next = endNode;
            endNode.prev = startNode.prev;
        }
    }

    public GenericAbstractList<E> subList(int start){
        GenericAbstractList<E> list = new GenericLinkedList<E>();
        Node<E> startNode = at(start);
        while (startNode != null){
            list.pushBack(startNode.data);
            startNode = startNode.next;
        }

        return list;
    }

    public GenericAbstractList<E> subList(int start, int end){
        GenericAbstractList<E> list = new GenericLinkedList<E>();
        Node<E> startNode = at(start);
        Node<E> endNode = at(end);
        while (startNode != null && startNode != endNode){
            list.pushBack(startNode.data);
            startNode = startNode.next;
        }

        return list;
    }

    public String toString(){
        StringBuilder str = new StringBuilder("[");
        Node<E> iterator = head;
        while (iterator != null){
            str.append(iterator.data + " ");
            iterator = iterator.next;
        }

        str.append("]");
        return str.toString();
    }
}

class Main{
    public static void main(String[] args){
        GenericAbstractList<Integer> arrList = new GenericArrayList<Integer>(new Integer[]{1, 2, 3, 4, 5});
        System.out.println(arrList);

        GenericAbstractList<Integer> linkedList = new GenericLinkedList<Integer>();
        System.out.println(linkedList);
        linkedList.add(new Integer[]{10, 20, 30, 40, 50});
        System.out.println(linkedList);
        linkedList.removeAllAt(1, 3);
        System.out.println(linkedList);
        System.out.println(linkedList.pop());
    }
}
