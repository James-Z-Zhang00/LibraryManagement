public class Book extends LibraryItem {
    private String author;
    private Integer numberOfPages;

    public Book(String line){
        String[] part = line.split(",");
        super.LibraryItem(part[0], Integer.parseInt(part[1]), part[2], Integer.parseInt(part[3]));
        SetAuthor(part[4]);
        SetNumberOfPages(Integer.parseInt(part[5]));
        super.SetMaxDay(28);
    }

    public void PrintRecord(){
        super.PrintRecord();
        System.out.println("Author: " + GetAuthor());
        System.out.println("Number of Pages: " + GetNumberOfPages());
        System.out.println("Max number of days for borrowing: " + super.GetMaxDay());
        System.out.println("-------------------------------------");
    }

    public void SetAuthor(String author){ this.author = author; }
    public void SetNumberOfPages(Integer NumberOfPages){ this.numberOfPages = numberOfPages; }
    public String GetAuthor() {return author;}
    public Integer GetNumberOfPages() {return numberOfPages;}
}
