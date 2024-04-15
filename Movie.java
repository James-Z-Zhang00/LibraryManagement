public class Movie extends LibraryItem {
    private String director;

    public Movie(String line){
        String[] part = line.split(",");
        super.LibraryItem(part[0], Integer.parseInt(part[1]), part[2], Integer.parseInt(part[3]));
        SetDirector(part[4]);
        super.SetMaxDay(7);
    }

    public void PrintRecord(){
        super.PrintRecord();
        System.out.println("Director: " + GetDirector());
        System.out.println("Max number of days for borrowing: " + super.GetMaxDay());
        System.out.println("-------------------------------------");
    }

    public void SetDirector(String director) {this.director = director;}
    public String GetDirector() {return director;}
}
