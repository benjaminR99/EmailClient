public class OfficeFriend extends Official implements Sendable{
    private String bdate;
    public OfficeFriend(String emial, String name, String designation, String birthday) {
        super(emial, name, designation);
        this.bdate= birthday ;
    }

    @Override
    public String getbday() {
        return bdate;
    }

    @Override
    public String getBdayMsg() {
        return "Wish you a happy birthday "+ this.getName() + ".May this year bring you great success";
    }

}
