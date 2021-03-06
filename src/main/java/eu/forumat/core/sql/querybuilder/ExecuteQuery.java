package eu.forumat.core.sql.querybuilder;

import eu.forumat.core.sql.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExecuteQuery extends Query {

    public ExecuteQuery(MySQL sql, String query) {
        super(sql, query);
    }

    public void execute() {
        execute(0);
    }

    public Object execute(int autoGeneratedKeys) {
        this.endOptions.forEach((field, option)-> {
            this.query = query.substring(0,query.length()-1)+",";
            this.query += option + " ("+field+"))";
        });

        Connection connection = getConnection();
        try(final PreparedStatement preparedStatement = connection.prepareStatement(query, autoGeneratedKeys)) {
            int i = 1;
            for (Object object : values) {
                preparedStatement.setString(i, object.toString());
                i++;
            }
            preparedStatement.executeUpdate();
            if(autoGeneratedKeys != 0) {
                ResultSet result = preparedStatement.getGeneratedKeys();
                if(result != null){
                    if(result.next()) return result.getObject(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(sql != null) connection.close();
            } catch (SQLException ignored) {}
        }
        return null;
    }
}