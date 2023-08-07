import java.util.ArrayList;
import java.util.Arrays;
// import java.util.List;

abstract class AbstractListInteger{
    protected int[] initialList;

    public AbstractListInteger(){
        this.initialList = new int[0];
    }

    public AbstractListInteger(int[] arr){
        this.initialList = arr;
    }

    public int[] getOriginalList(){
        return initialList;
    }

    // AbstractListIntegerが実装しなければならないメソッド。抽象メソッド
    public abstract int get(int position);  // 任意の位置の要素取得
    public abstract void add(int element); // リストの最後に追加します。
    public abstract void add(int[] elements); // リストの最後の要素に追加します。
    public abstract int pop();// リストの最後から削除します。削除した要素を返します。
    public abstract void addAt(int position, int element);// 指定された位置に要素を追加します。
    public abstract void addAt(int position, int[] elements);// 指定された位置に複数の要素を追加します。
    public abstract int removeAt(int position);// 指定した位置にある要素を削除します。削除した要素を返します。
    public abstract void removeAllAt(int start);// 指定された位置から始まるすべての要素を削除します。
    public abstract void removeAllAt(int start, int end);// startからendまでの全ての要素を削除します。
    public abstract AbstractListInteger subList(int start); // AbstractListIntegerの部分リストを、指定された位置から最後まで返します。
    public abstract AbstractListInteger subList(int start, int end); // startからendまでのAbstractListIntegerの部分リストを返します。
    public abstract void printList();
}

class IntegerArrayList extends AbstractListInteger{
    protected ArrayList<Integer> arrayList;

    public IntegerArrayList(){
        super();
        this.arrayList = new ArrayList<Integer>();
    }

    public IntegerArrayList(int[] arr){
        super(arr);
        this.arrayList = IntegerArrayList.toArrayList(arr);
    }

    public int get(int position){
        return arrayList.get(position);
    }

    public void add(int element){
        arrayList.add(element);
    }

    public void add(int[] elements){
        ArrayList<Integer> dArr = toArrayList(elements);
        arrayList.addAll(dArr);
    }

    public int pop(){
        return arrayList.remove(arrayList.size() - 1);
    }

    public void addAt(int position, int element){
        arrayList.add(position, element);
    }
    
    public void addAt(int position, int[] elements){
        // List<Integer> list = Arrays.asList(arr);
        // arrayList.addAll(position, list);

        ArrayList<Integer> dArr = toArrayList(elements);
        arrayList.addAll(position, dArr);
    }

    public int removeAt(int position){
        return arrayList.remove(position);
    }

    public void removeAllAt(int start){
        arrayList.subList(start, arrayList.size()).clear();
    }

    public void removeAllAt(int start, int end){
        arrayList.subList(start, end + 1).clear();
    }

    public AbstractListInteger subList(int start){
        int[] sub = arrayList.subList(start, arrayList.size()).stream()
                .mapToInt(Integer::intValue)
                .toArray();

        return new IntegerArrayList(sub);
    }

    public AbstractListInteger subList(int start, int end){
        int[] sub = arrayList.subList(start, end + 1).stream()
                .mapToInt(Integer::intValue)
                .toArray();
                
        return new IntegerArrayList(sub);
    }

    public void printList(){
        System.out.print("[");
        for (int i = 0; i < arrayList.size(); i++){
            System.out.print(arrayList.get(i) + " ");
        }
        System.out.println("]");
    }

    public static ArrayList<Integer> toArrayList(int[] arr){        
        ArrayList<Integer> output = new ArrayList<>();
        for (int num: arr) output.add(num);

        return output;
    }
}

class Main{
    public static void printList(AbstractListInteger list){
        list.printList();
    }

    public static void main(String[] args){
        AbstractListInteger arrList1 = new IntegerArrayList(new int[]{1, 2, 3, 4, 5});
        printList(arrList1);
        System.out.println(arrList1.get(4));
        arrList1.add(7);
        arrList1.add(new int[]{8, 9});
        printList(arrList1);
        System.out.println(arrList1.pop());
        arrList1.addAt(5, 6);
        arrList1.addAt(5, new int[]{5, 5, 6, 6});
        printList(arrList1);
        System.out.println(arrList1.removeAt(4));
        arrList1.removeAllAt(7);
        printList(arrList1);
        arrList1.removeAllAt(1, 3);
        printList(arrList1);
        printList(arrList1.subList(2));
        printList(arrList1.subList(1, 3));

        // System.out.println(arrList1.initialList[5]);
    }
}
