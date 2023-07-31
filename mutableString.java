import java.util.ArrayList;

class MutableString {
    private ArrayList<Character> string;  // 文字の動的配列コンテナ

    public MutableString(ArrayList<Character> string){
        this.string = string;
    }

    public MutableString(String string){
        this.string = new ArrayList<>();
        for (int i = 0; i < string.length(); i++){
            this.string.add(string.charAt(i));
        }
    }

    public String getString(){
        String string = "";
        for (int i = 0; i < this.length(); i++){
            string += this.string.get(i);
        } 

        return string;
    }

    public void setString(String string){
        this.string = new ArrayList<>();
        for (int i = 0; i < string.length(); i++){
            this.string.add(string.charAt(i));
        }
    }

    public void append(char c){
        this.string.add(c);
    }

    public MutableString substring(int start){
        ArrayList<Character> newString = new ArrayList<>(this.string.subList(start, this.length() - 1));
        return new MutableString(newString);
    }

    public MutableString substring(int start, int end){
        ArrayList<Character> newString = new ArrayList<>(this.string.subList(start, end));
        return new MutableString(newString);
    }

    public void concat(char[] cArr){
        for (char c: cArr){
            this.string.add(c);
        }
    }

    public void concat(String stringInput){
        for (int i = 0; i < stringInput.length(); i++){
            this.string.add(stringInput.charAt(i));
        }
    }

    public void concat(MutableString stringInput){
        for (int i = 0; i < stringInput.string.size(); i++){
            this.string.add(stringInput.string.get(i));
        }
    }

    public int length(){
        return this.string.size();
    }
}

class Main{
    public static void main(String[] args){
        MutableString mutableStr = new MutableString("hello");
        mutableStr.setString("Hello");
        System.out.println(mutableStr.getString());  // Hello

        mutableStr.concat(" World");
        System.out.println(mutableStr.getString());  // Hello World
        mutableStr.append('!');
        System.out.println(mutableStr.getString());  // Hello World!

        MutableString mutableStr2 = mutableStr.substring(6, 11);
        System.out.println(mutableStr2.getString());  // World
        System.out.println(mutableStr2.length());  // 5
    }
}
