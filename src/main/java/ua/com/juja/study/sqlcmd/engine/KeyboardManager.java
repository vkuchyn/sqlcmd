package ua.com.juja.study.sqlcmd.engine;

import ua.com.juja.study.sqlcmd.database.DatabaseExecutor;
import ua.com.juja.study.sqlcmd.database.Row;
import ua.com.juja.study.sqlcmd.sql.QueryHistory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeyboardManager{
    private QueryHistory history;
    private DatabaseExecutor databaseExecutor;

    public KeyboardManager(QueryHistory history, DatabaseExecutor databaseExecutor) {
        this.history = history;
        this.databaseExecutor = databaseExecutor;
    }

    public Row[] executeQuery(String query) {
        history.addQueryToTheHead(query);
        return databaseExecutor.executeSqlScript(query);
    }

    public void startListenUserKeyboard() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean quit = false;
        while (!quit){
            String query = reader.readLine();
            if ("\\q".equals((query))){
                quit = true;
            } else {
                executeQuery(query);
            }
        }
    }
}
