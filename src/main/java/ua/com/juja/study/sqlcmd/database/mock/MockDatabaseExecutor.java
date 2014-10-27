package ua.com.juja.study.sqlcmd.database.mock;

import ua.com.juja.study.sqlcmd.config.SqlCmdConfig;
import ua.com.juja.study.sqlcmd.database.DatabaseExecutor;
import ua.com.juja.study.sqlcmd.database.Row;

/**
 * Created with IntelliJ IDEA.
 * User: viktor
 * Date: 10/15/14
 * Time: 11:42 PM
 */
public class MockDatabaseExecutor implements DatabaseExecutor {
    public static final String MOCK_DATABASES[] = {"Java 1.5", "Java 1.8"};
    private int currentDatabaseIndex = 0;

    @Override
    public boolean connectToDb(SqlCmdConfig config) {
        System.out.println("Database connection established with config " + config);
        return true;
    }

    @Override
    public Row[] executeSqlScript(String sqlScript) {
        return new Row[0];
    }

    @Override
    public String[] getDatabaseList() {
        return MOCK_DATABASES;
    }

    @Override
    public void changeDatabase(String databaseName) {
        boolean databaseFound = false;
        for (int i = 0; i < MOCK_DATABASES.length; i++) {
            if (MOCK_DATABASES[i].equals(databaseName)) {
                currentDatabaseIndex = i;
                databaseFound = true;
                break;
            }
        }
        if (!databaseFound) {
            System.out.println("Database not found: " + databaseName);
        }
    }

}
