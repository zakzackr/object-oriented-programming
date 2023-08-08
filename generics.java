import java.lang.StringBuilder;

class Node<E>{
    E data;
    Node<E> next;

    public Node(E data){
        this.data = data;
    }
}

class Stack<E>{
    Node<E> head;

    public E peek(){
        if (head == null) return null;
        return head.data;
    }

    public E pop(){
        if (head == null) return null;

        Node<E> temp = head;
        head = head.next;
        return temp.data;
    }

    public void push(E data){
        Node<E> newNode = new Node<E>(data);
        newNode.next = head;
        head = newNode;
    }

    public String toString(){
        if (head == null) return "null";

        StringBuilder str = new StringBuilder("[");
        Node<E> iterator = head;
        while (iterator != null && iterator.next != null){
            str.append(iterator.data + ", ");
            iterator = iterator.next;
        }

        str.append(iterator.data + "]");
        return str.toString();
    }
}

class Main{
    public static void main(String[] args){
        Stack<Integer> intStack = new Stack<Integer>();
        intStack.push(1);
        intStack.push(2);
        // intStack.push(3.3); Double型をstackにpushしようとするとerror
        intStack.push(3);
        intStack.push(4);
        intStack.push(5);
        System.out.println(intStack);
        System.out.println(intStack.pop() * intStack.pop());

        Stack<Character> charStack = new Stack<Character>();
        charStack.push('e');
        charStack.push('l');
        charStack.push('l');
        charStack.push('p');
        charStack.push('a');
        System.out.println(charStack);
    }
}
