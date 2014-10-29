package ua.com.juja.study.sqlcmd.database.mock;

import ua.com.juja.study.sqlcmd.config.SqlCmdConfig;
import ua.com.juja.study.sqlcmd.database.DatabaseExecutor;

/**
 * Created with IntelliJ IDEA.
 * User: viktor
 * Date: 10/15/14
 * Time: 11:42 PM
 */
public class MockDatabaseExecutor implements DatabaseExecutor {
    private static final String [] MOCK_DB = {"my_DB", "your_DB", "his_DB"};
    private int             currentDBIndex = 0;

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
        return MOCK_DB;
    }

    @Override
    public void changeDatabase(String databaseName) {
        boolean dbWasFound = false;
        for (int i = 0; i < MOCK_DB.length; i++) {
            if (MOCK_DB[i].equals(databaseName)) {
                currentDBIndex = i;
                dbWasFound = true;
                System.out.println("Datebase has been changed successfully");
                break;
            }
        }

        if (!dbWasFound) {
            System.out.println("Unknown datebase name " + databaseName );
        }



    }

}
