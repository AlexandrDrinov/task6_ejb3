package by.epamlab.model.impl;

import by.epamlab.exception.DAOException;
import by.epamlab.ifaces.IUserDAO;
import by.epamlab.model.beans.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserImplDB implements IUserDAO {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/webproject";
    private static final String USER = "root";
    private static final String PASS = "root";

    public final static String SELECT_LOGIN = "SELECT u.password FROM users AS u WHERE login = ?";

    public User getUser(String login, String password) throws DAOException {

        User user = null;
        DataSource ds = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        InitialContext ic;
        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:/UsersDB");
            conn = ds.getConnection();
            System.out.println("connection ok");
            pstmt = conn.prepareStatement(SELECT_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String passwordFromDB = rs.getString("password");
                if (passwordFromDB.equals(password)) {
                    user = new User(login, passwordFromDB);
                    return user;
                } else {
                    throw new DAOException("password invalid");
                }
            } else {
                throw new DAOException("user not found");
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } catch (NamingException ex) {
            throw new DAOException(ex);
        } finally {
            close(rs, pstmt, conn);
        }

    }

    private void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
