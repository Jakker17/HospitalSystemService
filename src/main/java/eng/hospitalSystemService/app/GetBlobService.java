package eng.hospitalSystemService.app;

import eng.hospitalSystemService.db.DbException;

import java.sql.*;

public class GetBlobService {
    Connection con;

    public byte[] getPasswordBlobFromDatabase(String whichBlob,int personalBirthNumber) throws SQLException {
        Blob blob = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/hospitalSystemService","admin","admin");
            PreparedStatement Statement = con.prepareStatement("SELECT * from Personal WHERE birthnumber="+personalBirthNumber);
            ResultSet rs = Statement.executeQuery();
            if (rs.next()) {
                blob = rs.getBlob(whichBlob);
            }

        } catch (SQLException throwables) {
            throw new DbException("problem with blob load.",throwables);
        }
        assert blob != null;
        long blobLength = blob.length();
        byte[] ret = blob.getBytes(1, (int) blobLength);
        blob.free();
        return ret;
    }
}
