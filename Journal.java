public class Journal extends LibraryItem {
    private Integer volume;
    private Integer number;

    public Journal(String line){
        String[] part = line.split(",");
        super.LibraryItem(part[0], Integer.parseInt(part[1]), part[2], Integer.parseInt(part[3]));
        SetVolume(Integer.parseInt(part[4]));
        SetNumber(Integer.parseInt(part[5]));
        super.SetMaxDay(14);
    }

    public void PrintRecord(){
        super.PrintRecord();
        System.out.println("Volume: " + GetVolume());
        System.out.println("Number: " + GetNumber());
        System.out.println("Max number of days for borrowing: " + super.GetMaxDay());
        System.out.println("-------------------------------------");
    }

    public void SetVolume(Integer volume) { this.volume = volume;}
    public void SetNumber(Integer number) { this.number = number;}
    public Integer GetVolume() { return volume;}
    public Integer GetNumber() { return number;}
}
