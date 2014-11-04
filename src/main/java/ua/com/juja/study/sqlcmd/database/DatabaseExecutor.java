package ua.com.juja.study.sqlcmd.database;

import ua.com.juja.study.sqlcmd.config.SqlCmdConfig;

public interface DatabaseExecutor {

    public abstract boolean connectToDb(SqlCmdConfig config);

    public abstract Row[] executeSqlScript(String sqlScript);

    public abstract String[] getDatabaseList();

    public abstract void changeDatabase(String databaseName);
}
