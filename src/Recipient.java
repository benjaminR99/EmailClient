public class Recipient {
    private String email;
    private  String name;

    public Recipient(String emial, String name) {
        this.email = emial;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emial) {
        this.email = emial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
