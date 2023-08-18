import java.util.Arrays;
import java.util.ArrayList;

interface Queue<E>{
    public abstract E peekFront();
    public abstract E poll();
    public abstract void push(E element);
}

abstract class GenericAbstractList<E> implements Queue<E>{
    private E[] initialList;

    public GenericAbstractList(E[] arr){
        this.initialList = arr;
    }
    
    public E[] getOriginalList(){
        return initialList;
    }

    public abstract E get(int position);
    public abstract void add(E element);
    public abstract void add(E[] elements);
    public abstract E pop();
    public abstract void addAt(int position, E element);
    public abstract void addAt(int position, E[] elements);
    public abstract E removeAt(int position);
    public abstract void removeAllAt(int start);
    public abstract void removeAllAt(int start, int end);
}

class GenericArrayList<E> extends GenericAbstractList<E>{
    private ArrayList<E> arrayList;

    public GenericArrayList(E[] arr){
        super(arr);
        arrayList = new ArrayList<E>(Arrays.asList(arr));
    }

    public E get(int position){
        return arrayList.get(position);
    }

    public void add(E element){
        arrayList.add(element);
    }

    public void add(E[] elements){
        ArrayList<E> dArr = new ArrayList<>(Arrays.asList(elements));
        arrayList.addAll(dArr);
    }

    public E pop(){
        return arrayList.remove(arrayList.size() - 1);
    }

    public void addAt(int position, E element){
        arrayList.add(position, element);
    }

    public void addAt(int position, E[] elements){
        ArrayList<E> dArr = new ArrayList<>(Arrays.asList(elements));
        arrayList.addAll(position, dArr);
    }

    public E removeAt(int position){
        return arrayList.remove(position);
    }

    public void removeAllAt(int start){
        arrayList.subList(start, arrayList.size()).clear();
    }

    public void removeAllAt(int start, int end){
        arrayList.subList(start, end + 1).clear();
    }

    public E peekFront(){
        if (arrayList.size() == 0) return null;
        return arrayList.get(0);
    }

    public E poll(){
        if (peekFront() == null) return null; 
        return arrayList.remove(0);
    }

    public void push(E element){
        arrayList.add(element);
    }
}

class Main{
    public static <E> void printArrList(GenericArrayList<E> arrList){
        System.out.print("[");
        while(arrList.peekFront() != null){
            System.out.print(arrList.poll() + " ");
        }
        System.out.println("]");
    }

    public static void main(String[] args){
        GenericArrayList<Integer> arrList = new GenericArrayList<Integer>(new Integer[]{1, 2, 3, 4, 5});
        printArrList(arrList);
        arrList.add(new Integer[]{6, 7, 8});
        arrList.addAt(0, new Integer[]{1, 2, 3, 4, 5});
        printArrList(arrList);


        GenericArrayList<String> arrList2 = new GenericArrayList<String>(new String[]{"apple", "banana", "cherry"});
        arrList2.removeAllAt(1);
        System.out.println(arrList2.pop());
        printArrList(arrList2);
    }
}
