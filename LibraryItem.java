public class LibraryItem {
    private String type;
    private Integer id;
    private String title;
    private Integer year;
    private Integer maxDay;
    private String dueDate = "";
    private Integer itemNumber = -2;

    protected Double averageRating = 0.0;
    protected Double totalRating = 0.0;
    protected Integer numberOfViews = 0;
    protected static String status = "available";
    // all the item available by default

    public void LibraryItem(){
        // constructor for invalid data in data source
        SetType("NO TYPE");
        SetId(000);
        SetTitle("UNKNOWN");
        SetYear(0000);
    }
    public void LibraryItem(String type, Integer id, String title, Integer year){
        SetType(type);
        SetId(id);
        SetTitle(title);
        SetYear(year);
    }



    public void PrintRecord(){
        System.out.println("Type: " + GetType());
        System.out.println("ID: " + GetId());
        System.out.println("Title: " + GetTitle());
        System.out.println("Year: " + GetYear());
        System.out.println("Average rating: " + GetAverageRating());
        System.out.println("Number of viewers: " + GetNumberOfViewer());
        System.out.println("Status: " + GetStatus());
        if (GetStatus().equals("on loan")) { System.out.println("Due date: " + GetDueDate());}
    }

    public void SetType(String type){ this.type = type; }
    public void SetId(Integer id){ this.id = id; }
    public void SetTitle(String title){ this.title = title; }
    public void SetYear(Integer year){ this.year = year;}
    public void SetMaxDay(Integer maxDay) {this.maxDay = maxDay;}
    public void SetStatus(String status) {this.status = status;}
    public void AddNumberOfViewer() {numberOfViews++;}
    public void SetRating(Double rating) {totalRating += rating; averageRating = totalRating / numberOfViews;}
    public void SetDueDate(String dueDate) {this.dueDate = dueDate;}
    public void SetItemNumber(Integer itemNumber) {this.itemNumber = itemNumber;}

    public String GetType(){ return type; }
    public Integer GetId(){ return id; }
    public String GetTitle(){ return title; }
    public Integer GetYear(){ return year;}
    public Integer GetMaxDay(){ return maxDay;}
    public Double GetAverageRating() { return averageRating;}
    public Integer GetNumberOfViewer() { return numberOfViews;}
    public String GetStatus() { return status;}
    public String GetDueDate() {return dueDate;}
    public Integer GetItemNumber() {return itemNumber;}
}

