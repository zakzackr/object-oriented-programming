interface DequeInt extends StackInt, QueueInt{
    public abstract void addFirst(int element);
}

interface StackInt{
    public abstract Integer peekLast();
    public abstract Integer pop();
    public abstract void push(int element);
}

interface QueueInt{
    public abstract Integer peekFirst();
    public abstract Integer poll();
    public abstract void push(int element);
}

class Node{
    public int data;
    public Node next;
    public Node prev;

    public Node(int data){
        this.data = data;
    }
}

// 抽象クラスのインスタンスは作ることができない
abstract class AbstractListInteger implements DequeInt{
    protected Node head;
    protected Node tail;
    protected int[] initialList; 

    public AbstractListInteger(){};
    public AbstractListInteger(int[] arr){
        this.initialList = arr;
    }

    public int[] getOriginalList(){
        return initialList;
    }
}

class IntegerLinkedList extends AbstractListInteger{
    public IntegerLinkedList(){
        super();
    }

    public Integer peekLast(){
        if (tail == null) return null;
        return tail.data;
    }

    public Integer pop(){
        if (tail == null) return null;
        Node temp = tail;
        tail = tail.prev;
        if (tail == null) head = null;
        else tail.next = null;

        return temp.data;
    }

    public void push(int element){
        Node newNode = new Node(element);

        if (tail == null){
            tail = newNode;
            head = tail;
        } else{
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public Integer peekFirst(){
        if (head == null) return null;
        return head.data;
    }

    public Integer poll(){
        if (head == null) return null;

        Node temp = head;
        head = head.next;
        if (head == null) tail = null;
        else head.prev = null;

        return temp.data;
    }

    public void addFirst(int data){
        Node newNode = new Node(data);
        if (head == null){
            head = newNode;
            tail = head;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }
}

class Main{
    public static void queuePrint(QueueInt q){
        System.out.print("[");
        while(q.peekFirst() != null) System.out.print(q.poll() + " ");
        System.out.println("]");
    }

    public static void stackPrint(StackInt s){
        System.out.print("[");
        while(s.peekLast() != null) System.out.print(s.pop() + " ");
        System.out.println("]");
    }

    public static void dequePrint(DequeInt d){
        System.out.print("[");
        while (d.peekFirst() != null){
            System.out.print(d.poll() + " ");
            if (d.peekLast() != null) System.out.print(d.pop() + " ");
        }
        System.out.println("]");
    }

    public static void AbstractListIntegerPrint(AbstractListInteger l){
        Node iterator = l.head;

        System.out.print("[");
        while (iterator != null){
            System.out.print(iterator.data + " ");
            iterator = iterator.next;
        }
        System.out.println("]");
    }

    public static void main(String[] args){
        IntegerLinkedList linkedList = new IntegerLinkedList();
        linkedList.push(1);
        linkedList.push(2);
        linkedList.push(3);
        linkedList.push(4);
        AbstractListIntegerPrint(linkedList);
        stackPrint(linkedList);
        queuePrint(linkedList);
        dequePrint(linkedList);

        StackInt stackList = new IntegerLinkedList();
        stackList.push(10);
        stackList.push(20);
        stackList.push(30);
        stackList.push(40);
        stackPrint(stackList);

        AbstractListInteger abstractList = new IntegerLinkedList();
        abstractList.push(100);
        abstractList.push(200);
        abstractList.push(300);
        abstractList.push(400);
        AbstractListIntegerPrint(abstractList);
        queuePrint(abstractList);
        stackPrint(abstractList);
        dequePrint(abstractList);
    }
}

