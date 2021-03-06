package ua.com.juja.study.sqlcmd.database;

import ua.com.juja.study.sqlcmd.SqlCmd;
import ua.com.juja.study.sqlcmd.di.ApplicationContext;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: viktor
 * Date: 11/27/14
 * Time: 12:17 PM
 */
public class AsyncDatabaseExecutor implements DatabaseExecutor {

    private DatabaseExecutor executor;

    public AsyncDatabaseExecutor(DatabaseExecutor executor) {
        this.executor = executor;
    }

    @Override
    public QueryResult executeSqlScript(final String sqlScript) throws DatabaseException {
        ApplicationContext applicationContext = SqlCmd.getApplicationContext();
        ExecutorService executorService = applicationContext.getExecutorService();
        Future<Row[]> queryResult = executorService.submit(new Callable<Row[]>() {
            @Override
            public Row[] call() throws Exception {
                return executor.executeSqlScript(sqlScript).getRowList();
            }
        });
        return new QueryResult(queryResult);
    }

    @Override
    public String[] getDatabaseList() throws DatabaseException {
        return executor.getDatabaseList();
    }

    @Override
    public void changeDatabase(String databaseName) throws DatabaseException {
        executor.changeDatabase(databaseName);
    }
}
