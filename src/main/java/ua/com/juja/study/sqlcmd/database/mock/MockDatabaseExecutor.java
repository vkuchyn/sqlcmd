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

    private static final String [] MOCK_DATABASES = {"juja", "java", "database1"};
    private int currentDatabaseIndex = 0;

    @Override
    public boolean connectToDb(SqlCmdConfig config) {
        System.out.println("Database connection established with config " + config);
        return true;
    }

    public String getCurrentDatabase(){
        return  MOCK_DATABASES[currentDatabaseIndex];
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

    public static void main(String[] args) {
        MockDatabaseExecutor executor = new MockDatabaseExecutor();
        String databases = arrayToString(executor.getDatabaseList());
        System.out.println("Databases are " + databases);
        System.out.println("Current database is " + executor.getCurrentDatabase());
        executor.changeDatabase("java");
        System.out.println("Current database is " + executor.getCurrentDatabase());
        executor.changeDatabase("database1");
        System.out.println("Current database is " + executor.getCurrentDatabase());
        executor.changeDatabase("database2");
        System.out.println("Current database is " + executor.getCurrentDatabase());
    }

    private  static String arrayToString(String[] array){
        String res = "";
        for (int i = 0; i <array.length; i++){
            res += array[i] + " ";
        }
        return res;
    }

}
