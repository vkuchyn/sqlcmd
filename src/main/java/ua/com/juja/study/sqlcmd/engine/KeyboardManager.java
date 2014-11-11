package ua.com.juja.study.sqlcmd.engine;

import ua.com.juja.study.sqlcmd.database.DatabaseExecutor;
import ua.com.juja.study.sqlcmd.database.Row;
import ua.com.juja.study.sqlcmd.sql.QueryHistory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: viktor
 * Date: 10/22/14
 * Time: 11:28 PM
 */
public class KeyboardManager {
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

        StringBuilder query = new StringBuilder();
        boolean exit = false;
        int indexLastChar; // the last char in query

        while (!exit) {

            if (query.length() > 0 ) {
                query.append(" " + reader.readLine());
            }else {
                query.append(reader.readLine());
            }

            if ("\\q".equals(query.toString())){
                    exit = true;
            }else if(query.length() > 0) {
                    indexLastChar = query.length()-1;
                    if (query.substring(indexLastChar, query.length()).equals(";")) {
                        executeQuery(query.toString());
                        query = new StringBuilder();
                    }

            }


        }
    }
}
