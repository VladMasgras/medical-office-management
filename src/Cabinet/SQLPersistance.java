package Cabinet;

import java.sql.*;
import java.util.List;

public class SQLPersistance {

    private static SQLPersistance instance;

    private SQLPersistance() {

    }

    public static SQLPersistance getDatabase() {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null)
                    instance = new SQLPersistance();
            }
        }
        return instance;
    }

    public void addAdult(Adult adult) {
        String sql = "INSERT INTO adulti VALUES (?, ?, ?, ?, null)";
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet", "root", "bazededate");
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, adult.getNume());
            statement.setInt(2, adult.getVarsta());
            statement.setBoolean(3, adult.isFumator());
            statement.setString(4, adult.getSedentarism());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addCopil(Copil copil){
        String sql = "INSERT INTO copii VALUES (?, ?, ?, null)";
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet", "root", "bazededate");
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, copil.getNume());
            statement.setInt(2, copil.getVarsta());
            statement.setFloat(3, copil.getProteinaC());
            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addBatran(Batran batran){
        String sql = "INSERT INTO batrani VALUES (?, ?, ?, null)";
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet", "root", "bazededate");
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, batran.getNume());
            statement.setInt(2, batran.getVarsta());
            statement.setInt(3, batran.getValTensiune());
            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addDoctor(Doctor doctor){
        String sql = "INSET INTO doctori VALUES (?, ?)";

        try{

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet", "root", "bazededate");
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, doctor.getNume());
            statement.setInt(2, doctor.getVarsta());

            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int stergeAdult(String nume){
        String sql = "DELETE FROM adulti WHERE NUME=?";
        int n = 0;
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet", "root", "bazededate");
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, nume);
            n = statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return n;
    }

    public int stergeCopil(String nume){
        String sql = "DELETE FROM copii WHERE NUME=?";
        int n = 0;
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet", "root", "bazededate");
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, nume);
            n = statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return n;
    }

    public int stergeBatran(String nume){
        String sql = "DELETE FROM batrani WHERE NUME=?";
        int n = 0;
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet", "root", "bazededate");
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, nume);
            n = statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return n;
    }

    public int stergeDoctor(String nume){

        String sql = "DELETE FROM doctori WHERE NUME=?";
        int n = 0;
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet", "root", "bazededate");
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, nume);
            n = statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return n;
    }

    public void listeazaBatrani(){

        String sql = "SELECT * FROM batrani";

        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet", "root", "bazededate");
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
                System.out.println(rs.getString("nume") + " " + rs.getInt("varsta") + " " + rs.getFloat("Tensiune"));

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void listeazaCopii(){

        String sql = "SELECT * FROM copii";

        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet", "root", "bazededate");
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
                System.out.println(rs.getString("nume") + " " + rs.getInt("varsta") + " " + rs.getFloat("proteinaC"));

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void listeazaAdulti(){
        String sql = "SELECT * FROM adulti";

        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet", "root", "bazededate");
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
                System.out.println(rs.getString("nume") + " " + rs.getInt("varsta") + " " + rs.getBoolean("fumator") + " " +
                        rs.getString("sedentarism"));

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void listeazaDoctori(){

        String sql = "SELECT * FROM doctori";

        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet", "root", "bazededate");
            PreparedStatement statement = con.prepareStatement(sql);

            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString("nume") + " " + rs.getInt("varsta"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateBatran(String nume, int valTensiune){

        String sql = "UPDATE batrani SET Tensiune=? WHERE nume=?";

        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet", "root", "bazededate");
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setFloat(1, valTensiune);
            statement.setString(2, nume);

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void updateAdult(String nume, boolean fumator, String sedentarism){

        String sql = "UPDATE adulti SET fumator=?, sedentarism=? WHERE nume=?";

        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet", "root", "bazededate");
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setBoolean(1, fumator);
            statement.setString(2, sedentarism);
            statement.setString(3, nume);

            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateCopil(String nume, float proteinaC){

        String sql = "UPDATE copii SET proteinaC=? WHERE nume=?";

        try{

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet", "root", "bazededate");
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setFloat(1, proteinaC);
            statement.setString(2, nume);

            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateDoctor(String nume, int varsta){

        String sql = "UPDATE doctori SET varsta=? WHERE nume=?";

        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet", "root", "bazededate");
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setInt(1, varsta);
            statement.setString(2, nume);

            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void asigneazaDoctorCopil(String numePac, String numeDoc){

        String sql1 = "UPDATE copii SET doctor = ? WHERE nume=?";

        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet", "root", "bazededate");
            PreparedStatement statement = con.prepareStatement(sql1);

            statement.setString(1, numeDoc);
            statement.setString(2, numePac);
            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void asigneazaDoctorAdult(String numePac, String numeDoc){

        String sql1 = "UPDATE adulti SET doctor = ? WHERE nume=?";

        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet", "root", "bazededate");
            PreparedStatement statement = con.prepareStatement(sql1);

            statement.setString(1, numeDoc);
            statement.setString(2, numePac);
            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void asigneazaDoctorBatran(String numePac, String numeDoc){

        String sql1 = "UPDATE batrani SET doctor = ? WHERE nume=?";

        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet", "root", "bazededate");
            PreparedStatement statement = con.prepareStatement(sql1);

            statement.setString(1, numeDoc);
            statement.setString(2, numePac);
            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ResultSet getCopii(){

        String sql = "SELECT * FROM copii";

        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet", "root", "bazededate");
            PreparedStatement statement = con.prepareStatement(sql);

            return statement.executeQuery();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getAdulti(){
        String sql = "SELECT * FROM adulti";

        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet", "root", "bazededate");
            PreparedStatement statement = con.prepareStatement(sql);

            return statement.executeQuery();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getBatrani(){

        String sql = "SELECT * FROM batrani";

        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet", "root", "bazededate");
            PreparedStatement statement = con.prepareStatement(sql);

            return statement.executeQuery();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getDoctori(){

        String sql = "SELECT * FROM doctori";

        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet", "root", "bazededate");
            PreparedStatement statement = con.prepareStatement(sql);

            return statement.executeQuery();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

}
