package ua.com.juja.study.sqlcmd.database.mock;

import ua.com.juja.study.sqlcmd.config.SqlCmdConfig;
import ua.com.juja.study.sqlcmd.database.DatabaseExecutor;
import ua.com.juja.study.sqlcmd.database.Row;
import ua.com.juja.study.sqlcmd.util.Utils;

public class MockDatabaseExecutor implements DatabaseExecutor {

    private static final String [] MOCK_DATABASES = {"juja", "java", "database1"};
    private int currentDatabaseIndex = 0;

    public String getCurrentDatabase(){
        return  MOCK_DATABASES[currentDatabaseIndex];
    }

    @Override
    public boolean connectToDb(SqlCmdConfig config) {
        System.out.println("Database connection established with config " + config);
        return true;
    }

    @Override
    public Row[] executeSqlScript(String sqlScript) {
        System.out.println("Executed script " + sqlScript);
        return new Row[0];
    }

    @Override
    public String[] getDatabaseList() {
        return MOCK_DATABASES;
    }

    @Override
    public void changeDatabase(String databaseName) {
        boolean databaseWasFound = false;
        for (int i = 0; i < MOCK_DATABASES.length; i++){
            if(MOCK_DATABASES[i].equals(databaseName)){
                currentDatabaseIndex = i;
                databaseWasFound = true;
                break;
            }
        }
            if (!databaseWasFound){
                System.out.println("Wrong database name " + databaseName);
            }

    }
}
