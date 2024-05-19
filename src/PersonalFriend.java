public class PersonalFriend extends Recipient implements Sendable{


    private String  bdate ;
    public PersonalFriend(String emial, String name,  String Bdate) {
        super(emial, name);

        this.bdate = Bdate ;
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
