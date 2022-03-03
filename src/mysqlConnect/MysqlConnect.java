package mysqlConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import myObject.Customer;


public class MysqlConnect {
    //	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/cms_hisoft?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    // ���ݿ���û��������룬��Ҫ�����Լ�������
    static final String USER = "root";
    static final String PASSWORD = "123";

    public boolean add(Customer customer) throws SQLException {
        String sql = "INSERT INTO users (name,gender,age,phone,email) VALUES ('"
                + customer.name + "'" + "," + "'" + customer.gender + "'" + "," + customer.age + ","
                + "'" + customer.phone+ "'" + "," + "'" + customer.email + "');";
        return this.connect(sql);
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = " + id;
        return this.connect(sql);
    }

    public boolean update(int id,String field, String value) throws SQLException {

        String sql = "UPDATE users SET " +  field  + "=" + "'" +value + "'" + " WHERE id = " + id + ";";
        return this.connect(sql);
    }

    public boolean seek(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = " + id + ";";
        Customer customer = connectQuery(sql,true);
        if(customer != null) {
            return true;
        }else {
            return false;
        }
    }

    public Customer seekToGetCustomer(int id) {
        String sql = "SELECT * FROM users WHERE id = " + id + ";";
        Customer customer = null;
        try {
            customer = connectQuery(sql,false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
//-------------------------------------------------------------------

    //����ִ��sql���
    public boolean connect(String sql) throws SQLException{
        //��ȡ���Ӷ���
        Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

        Statement statement = connection.createStatement();
        boolean flag;
        if(statement.executeUpdate(sql) >= 1) {
            flag = true;
        }else {
            flag = false;
        }
        connection.close();
        statement.close();
        return flag;
    }

    public Customer connectQuery(String sql,boolean isprint) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        Customer customer = null;
        while(result.next()) {
            String id = result.getString("id");
            String name = result.getString("name");
            String gender = result.getString("gender");
            String age = result.getString("age");
            String phone = result.getString("phone");
            String email = result.getString("email");
            customer = new Customer(name, gender, age, phone, email);
            if(isprint) {
                System.out.print(String.format("%-5s\t", id));
                System.out.print(String.format("%-10s\t", name));
                System.out.print(String.format("%-5s\t", gender));
                System.out.print(String.format("%-5s\t", age));
                System.out.print(String.format("%-15s\t", phone));
                System.out.print(String.format("%-20s\t", email));
                System.out.println();
            }
        }
        connection.close();
        statement.close();
        return customer;
    }
}
