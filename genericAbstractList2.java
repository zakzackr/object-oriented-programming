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

class Main{
    public static void main(String[] args){
        GenericArrayList<Integer> arrList = new GenericArrayList<Integer>(new Integer[]{1, 2, 3, 4, 5});
        System.out.println(arrList);
        System.out.println(arrList.subList(3));

        GenericArrayList<String> arrList2 = new GenericArrayList<String>(new String[]{"apple", "banana", "cherry", "dragon fruit"});
        System.out.println(arrList2);
        arrList2.removeAllAt(1, 3);
        System.out.println(arrList2);
        arrList2.addAt(1, new String[]{"pineapple", "peach"});
        System.out.println(arrList2);
        System.out.println(arrList2.popBack());
        System.out.println(arrList2);
        arrList2.pushFront("melon");
        arrList2.pushBack("water melon");
        System.out.println(arrList2);
    }
}
