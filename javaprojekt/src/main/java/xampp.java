import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class xampp {
    public static void main (String[] args){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url ="jdbc:mysql://localhost:3306/pharmacy?useUnicode=true&useJDBCCompilantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user ="root";
            String password ="";

            Connection con = DriverManager.getConnection(url,user,password);

            if(con != null){
                System.out.println("Successfull conection");
            }

            String query = "select * from Doktorzy";

            var statement = con.prepareStatement(query);
            ResultSet r = statement.executeQuery();

            while (r.next()){
                //int id = r.getInt("id_doktor");
                String name = r.getString("imie");

                System.out.println( name);
            }
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
}
