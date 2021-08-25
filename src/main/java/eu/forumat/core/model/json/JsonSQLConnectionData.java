package eu.forumat.core.model.json;

import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

import java.sql.Connection;

@AllArgsConstructor
@Getter
public class JsonSQLConnectionData {

    private final String host;
    private final String username;
    private final String password;
    private final String database;
    private final int port;

    @SneakyThrows
    private Connection getConnection() {
        MysqlDataSource dataSource = new MysqlDataSource();

        dataSource.setServerName(host);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setDatabaseName(database);
        dataSource.setPort(port);

        return dataSource.getConnection();
    }

}
