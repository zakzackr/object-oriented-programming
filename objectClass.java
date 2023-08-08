// 汎用的なstackデータ構造を作成するために、Object classを使用してStackを実装する。
// そうすると、スタックは同一のデータ型から成る均一なリストである必要があるが、Integer, Double, Animalなど異なるデータ型が混在し、
// 演算等を行うと論理エラーや実行時エラーを引き起こす可能性がある。
// ジェネリクスを使用することでこの問題を解決することができる。

import java.lang.StringBuilder;

class Node{
    Object data;
    Node next;

    public Node(Object data){
        this.data = data;
    }
}

class Stack{
    Node head;

    public Object peek(){
        if (head == null) return null;
        return head.data;
    }

    public Object pop(){
        if (head == null) return null;

        Node temp = head;
        head = head.next;
        return temp.data;
    }

    public void push(Object data){
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public String toString(){
        if (head == null) return "[]";

        StringBuilder str = new StringBuilder("[");
        Node iterator = head;
        while (iterator != null && iterator.next != null){
            str.append(iterator.data + ", ");
            iterator = iterator.next;
        }
        
        str.append(iterator.data + ", ");
        return str.toString();
    }
}

class Animal{
    private String species;

    public Animal(String species){
        this.species = species;
    }
}

class Main{
    public static void main(String[] args){
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2.2);
        stack.push(3);
        stack.push(new Animal("Lion"));
        stack.push(4.4);

        // error
        // System.out.println(stack.pop() + stack.pop()); 
        // System.out.println(stack.pop() * stack.pop());
        System.out.println(stack);
    }
}


// class IntegerNode{
//     Integer data;
//     IntegerNode next;

//     public IntegerNode(Integer data){
//         this.data = data;
//     }
// }

// class StackInteger{
//     IntegerNode head;

//     public Integer peek(){
//         if (head == null) return null;
//         return head.data;
//     }

//     public Integer pop(){
//         if (head == null) return null;
//         IntegerNode temp = head;
//         head = head.next;

//         return temp.data;
//     }

//     public void push(Integer data){
//         IntegerNode newNode = new IntegerNode(data);
//         newNode.next = head;
//         head = newNode;
//     }

//     public String toString(){
//         if (head == null) return "null";

//         StringBuilder str = new StringBuilder("[");
//         IntegerNode iterator = head;
//         while (iterator != null && iterator.next != null){
//             str.append(iterator.data + ", ");
//             iterator = iterator.next;
//         }

//         str.append(iterator.data + "]");
//         return str.toString();
//     }
// }

// class DoubleNode{
//     Double data;
//     DoubleNode next;

//     public DoubleNode(Double data){
//         this.data = data;
//     }
// }

// class StackDouble{
//     DoubleNode head;

//     public Double peek(){
//         if (head == null) return null;
//         return head.data;
//     }

//     public Double pop(){
//         if (head == null) return null;

//         DoubleNode temp = head;
//         head = head.next;
//         return temp.data;
//     }

//     public void push(Double data){
//         DoubleNode newNode = new DoubleNode(data);
//         newNode.next = head;
//         head = newNode;
//     }

//     public String toString(){
//         if (head == null) return "null";

//         StringBuilder str = new StringBuilder("[");
//         DoubleNode iterator = head;
//         while (iterator != null && iterator.next != null){
//             str.append(iterator.data + ", ");
//             iterator = iterator.next;
//         }

//         str.append(iterator.data + "]");
//         return str.toString();
//     }
// }

// class Main{
//     public static void main(String[] args){
//         StackInteger intStack = new StackInteger();
//         intStack.push(1);
//         intStack.push(2);
//         intStack.push(3);
//         intStack.push(4);
//         intStack.push(5);
//         System.out.println(intStack);

//         StackDouble doubleStack = new StackDouble();
//         doubleStack.push(1.1);
//         doubleStack.push(2.2);
//         doubleStack.push(3.3);
//         doubleStack.push(4.4);
//         doubleStack.push(5.5);
//         System.out.println(doubleStack);
//     }
// }
