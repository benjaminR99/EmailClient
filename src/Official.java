public class Official extends Recipient{
    private String designation;

    public Official(String emial, String name, String designation) {
        super(emial, name);
        this.designation = designation;
    }

    public String getDesignation(){
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
