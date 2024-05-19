import java.util.ArrayList ;
import java.sql.*;
public class DBconnection {

    public static Connection getCon() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/emailclient","root","Rbenj@19");
    }
    public static ArrayList<Sendable> getTodayFriends() {
        ArrayList<Sendable> sendableArrayList = new ArrayList<>();

        try (Connection con = getCon();
             PreparedStatement pst = con.prepareStatement("SELECT * FROM clients WHERE clienttype IN ('friend', 'office-friend') AND DATE_FORMAT(birthdate, '%m-%d') = DATE_FORMAT(NOW(), '%m-%d')")) {

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                String name = rs.getString("clientname");
                String designation = rs.getString("designation");
                String birthday = rs.getDate("birthdate").toString();
                System.out.println(name + " is having birthday today" );
                if (rs.getString("clienttype").equals("friend")) {
                    PersonalFriend pf = new PersonalFriend(email,name,birthday) ;
                    sendableArrayList.add(pf);
                } else {
                    OfficeFriend officeFriend = new OfficeFriend(email, name, designation, birthday);
                    sendableArrayList.add(officeFriend);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sendableArrayList;
    }

    public static void addOffice(String clientname, String clienttype, String email, String designation) {
        try (Connection con = getCon();
             PreparedStatement pst = con.prepareStatement("INSERT INTO clients (clientname, clienttype, email, designation) VALUES (?, ?, ?, ?)")) {

            pst.setString(1, clientname);
            pst.setString(2, clienttype);
            pst.setString(3, email);
            pst.setString(4, designation);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Office client added successfully.");
            } else {
                System.out.println("Failed to add office client.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addFriend(String clientname, String clienttype, String email, Date birthdate) {
        try (Connection con = getCon();
             PreparedStatement pst = con.prepareStatement("INSERT INTO clients (clientname, clienttype, email, birthdate) VALUES (?, ?, ?, ?)")) {

            pst.setString(1, clientname);
            pst.setString(2, clienttype);
            pst.setString(3, email);
            pst.setDate(4, birthdate);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Friend added successfully.");
            } else {
                System.out.println("Failed to add friend.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addOfficeFriend(String clientname, String clienttype, String email, String designation, Date birthdate) {
        try (Connection con = getCon();
             PreparedStatement pst = con.prepareStatement("INSERT INTO clients (clientname, clienttype, email, designation, birthdate) VALUES (?, ?, ?, ?, ?)")) {

            pst.setString(1, clientname);
            pst.setString(2, clienttype);
            pst.setString(3, email);
            pst.setString(4, designation);
            pst.setDate(5, birthdate);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Office friend added successfully.");
            } else {
                System.out.println("Failed to add office friend.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
