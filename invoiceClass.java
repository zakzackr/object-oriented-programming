class Product{
    public String title;
    public double price;

    public Product(String title, double price){
        this.title = title;
        this.price = price;
    }
}

class InvoiceItem{
    Product product;
    public int quantity;
    public InvoiceItem next;
    
    public InvoiceItem(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
        this.next = null;
    }

    public double getTotalPrice(){
        return this.product.price * this.quantity;
    }
}

class Invoice{
    public String invoiceNumber;  
    public String invoiceDate;
    public String company;
    public String companyAddress;
    public String billToName;
    public String billToAddress;
    public InvoiceItem invoiceItemHead;
    public final double TAX = 0.1;

    public Invoice(String invoiceNumber, String invoiceDate, String company, String companyAddress, String billToName, String billToAddress, InvoiceItem invoiceItemHead){
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.company = company;
        this.companyAddress = companyAddress;
        this.billToName = billToName;
        this.billToAddress = billToAddress;
        this.invoiceItemHead = invoiceItemHead;
    }

    public double amountDue(boolean taxes){
        double totalAmount = 0;

        InvoiceItem currentItem = this.invoiceItemHead;
        while (currentItem != null){
            totalAmount += currentItem.product.price * currentItem.quantity;
            currentItem = currentItem.next;
        }
        if (taxes) totalAmount *= (1 + TAX);

        return Math.floor(totalAmount * 10) / 10;
    }

    public void printBuyingItems(){
        System.out.println("Printing the Item List...");
        InvoiceItem currentItem = this.invoiceItemHead;
        while (currentItem != null){
            // item :shampoo, price :10, quantity:7
            System.out.println("item: " + currentItem.product.title + ", price: " + currentItem.product.price + ", quantity: " + currentItem.quantity);
            currentItem = currentItem.next;
        }
    }

    // 
    public void printInvoice(){
        System.out.println("/*");
        System.out.println("INVOICE");
        System.out.println("INVOICE NUMBER. : " + this.invoiceNumber);
        System.out.println("INVOICE DATE : " + this.invoiceDate);
        System.out.println("SHIP TO : " + this.company);
        System.out.println("COMPANY ADDRESS : " + this.companyAddress);
        System.out.println("BILL TO : " + this.billToName);
        System.out.println("BILL ADDRESS : " + this.billToAddress);
        
        InvoiceItem currentItem = this.invoiceItemHead;
        while (currentItem != null){
            System.out.println(currentItem.product.title + "($" + currentItem.product.price + ") --- " + currentItem.quantity + " pcs. --- AMOUNT: " + currentItem.getTotalPrice());
            currentItem = currentItem.next;
        }

        System.out.println("SUBTOTAL : " + this.amountDue(false));
        System.out.println("TAX : " + Math.floor(this.amountDue(false) * TAX * 10) / 10); 
        System.out.println("TOTAL : " + this.amountDue(true));
        System.out.println("*/");
    }
}

class Main{
    public static void main(String[] args){
        Product product1 = new Product("shampoo", 10);
        Product product2 = new Product ("perfume", 85);
        Product product3 = new Product ("tooth brush", 3);

        InvoiceItem firstItem = new InvoiceItem(product1, 7);
        InvoiceItem secondItem = new InvoiceItem(product2, 9);
        InvoiceItem thirdItem = new InvoiceItem(product3, 10);

        firstItem.next = secondItem;
        secondItem.next = thirdItem;
        Invoice invoice = new Invoice("UC1234567890", "2023/06/06", "UCLA", "Los Angles", "John", "Tokyo", firstItem);


        invoice.printBuyingItems();
        invoice.printInvoice();
    }
}
