class Book{
    public static String author = "Stephen Hawkings";
    public String title;
    public String year;

    public Book(String title, String year){
        this.title = title;
        this.year = year;
    }

    public String toString(){
        return this.title + " written by " + this.author + " in " + this.year;
    }
}

class MyClass{
    public static void printBookArray(Book[] books){
        for (Book book: books){
            System.out.println(book.toString());
        }
    }

    public static void main(String[] args){
        Book[] books = new Book[3];
        books[0] = new Book("How Did It All Begin?", "2021");
        books[1] = new Book("The Theory of Everything", "2010");
        books[2] = new Book("God Created the Integers", "2006");

        printBookArray(books);
    }
}

