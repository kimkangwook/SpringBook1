package springbook.test;

import springbook.user.domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static java.lang.Class.forName;

class TrywithTest {

    public User getUser(String id) {

        User user = null;
        String sql = "SELECT * FROM users WHERE id = ?";

//        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/springbook3", "root", "1234");
//        PreparedStatement ps = conn.prepareStatement(sql);
//        try {
//            ps.setInt(1, roleId);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        ResultSet rs = ps.executeQuery();
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try (conn; ps; rs){
//            if (rs.next()) {
//                String description = rs.getString(1);
//                Long id = rs.getLong("id");
//                user = new User(id,"", Grade.VIP);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return user;

        try {
            forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException c) {

        }

        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/springbook3", "root", "1234");
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String description = rs.getString(1);
                    String userId = rs.getString("id");
                }
            } catch(Exception e){

                }
            }
        catch (Exception e) {


            }

            return user;
        }
    }
