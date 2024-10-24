package cadastrobd.model.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceManager {
    public static long getValue(String sequenceName){
        long nextVal = -1;
        String sql = "Selecione o próximo valor" + sequenceName;
        
        try(Connection connection = ConectorBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()){
            
            if(resultSet.next()){
                nextVal = resultSet.getLong(1);
            }
        }
            catch(SQLException e){
                e.printStackTrace();
            }
            return nextVal;
    }
}
