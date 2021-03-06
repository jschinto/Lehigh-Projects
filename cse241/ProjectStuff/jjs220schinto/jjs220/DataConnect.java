import java.io.*;
import java.sql.*;

public class DataConnect implements java.lang.AutoCloseable {
    private String pass;
    private Connection conn;
    private CallableStatement cs;
    private PreparedStatement ps;

    public void makeConnection(String inPass) throws SQLException {
        pass = inPass;
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@edgar0.cse.lehigh.edu:1521:cse241", "jjs220",
                pass);
        conn = con;
        cs = null;
        ps = null;
    }

    public Connection getConnection() {
        return conn;
    }

    public void close() {
        try {
            if (cs != null) {
                cs.close();
            }
            if (ps != null) {
                ps.close();
            }
            conn.close();
        }
        catch (SQLException se) {
        }
    }

    public void setCS(CallableStatement incs) {
        if (cs != null) {
            try {
                cs.close();
            }
            catch (SQLException se) {

            }
        }
        cs = incs;
    }

    public <T> void setCSParam(Integer inIndex, T inValue) throws SQLException {
        if (inValue == null) {
            cs.setNull(inIndex, java.sql.Types.NUMERIC);
        }
        else if (inValue instanceof String) {
            cs.setString(inIndex, (String) inValue);
        }
        else if (inValue instanceof Integer) {
            cs.setInt(inIndex, (Integer) inValue);
        }
        else if (inValue instanceof Long) {
            cs.setLong(inIndex, (Long) inValue);
        }
        else {
            System.out.println("Unknown type");
        }
    }

    public void setPS(PreparedStatement inps) {
        if (ps != null) {
            try {
                ps.close();
            }
            catch (SQLException se) {

            }
        }
        ps = inps;
    }

    public <T> void setPSParam(Integer inIndex, T inValue) throws SQLException {
        if (inValue == null) {
            ps.setNull(inIndex, java.sql.Types.NUMERIC);
        }
        else if (inValue instanceof String) {
            ps.setString(inIndex, (String) inValue);
        }
        else if (inValue instanceof Integer) {
            ps.setInt(inIndex, (Integer) inValue);
        }
        else if (inValue instanceof Long) {
            ps.setLong(inIndex, (Long) inValue);
        }
        else {
            System.out.println("Unknown type");
        }
    }
}
