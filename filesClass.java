class Files{
    public String fileName;
    public String fileExtension;
    public String content;
    public String parentFolder;
    
    public Files(String fileName, String fileExtension, String content, String parentFolder){
        this.fileName = fileName;
        this.fileExtension = (fileExtension == ".word" || fileExtension == ".png" || fileExtension == "mp4" || fileExtension == ".pdf")? fileExtension: ".txt";
        this.content = content;
        this.parentFolder = parentFolder;
    }

    public String getLifetimeBandwidthSize(){
        int sizeMb = this.content.length() * 25;

        return sizeMb < 1000? sizeMb + "MB": sizeMb / 1000d + "GB";
    }

    public String prependContent(String data){
        this.content = data + this.content;
        return this.content;
    }

    public String addContent(String data, int position){
        this.content = this.content.substring(0, position) + data + this.content.substring(position);

        return this.content;
    }

    public String showFileLocation(){
        return this.parentFolder + " > " + this.fileName + this.fileExtension;
    }
}


class MyClass{
    public static void main(String[] args){
        Files assignment = new Files("assignment", ".word", "ABCDEFGH", "homework");
        System.out.println(assignment.getLifetimeBandwidthSize());
        System.out.println(assignment.prependContent("MMM"));
        System.out.println(assignment.addContent("hello world", 5));
        System.out.println(assignment.showFileLocation());

        Files blade = new Files("blade", ".php", "bg-primary text-white m-0 p-0 d-flex justify-content-center", "view");
        System.out.println(blade.getLifetimeBandwidthSize());
        System.out.println(blade.addContent("font-weight-bold ", 11));
        System.out.println(blade.showFileLocation());
    }
}
