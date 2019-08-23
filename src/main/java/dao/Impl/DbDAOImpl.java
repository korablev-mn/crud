package dao.Impl;

import dao.DbDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbDAOImpl implements DbDAO {

    private Connection connection;
    public final static String DB = "dbuser";
    public final static String DB_TABLE = "user";

    public DbDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("CREATE TABLE if not exists " + DB + "." + DB_TABLE + " (id bigint auto_increment" +
                    ", name varchar(256) not null" +
                    ", password VARCHAR(256) not null" +
                    ", date DATE not null, PRIMARY KEY (id)" +
                    ", UNIQUE INDEX `id_UNIQUE` (name ASC) VISIBLE) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
