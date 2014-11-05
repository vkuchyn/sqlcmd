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

    public void startListenUser() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder query = new StringBuilder();
        boolean exit = false;
        int indexLastChar; // the last char in query

        while (!exit) {
            query.append(reader.readLine());

            if (query.length() > 0 ){
                indexLastChar = query.length()-1;

                if (query.substring(indexLastChar, query.length()).equals(";")){

                        if ("\\q;".equals(query.toString())){
                            exit = true;
                        } else {
                            System.out.print("!!!!! ");
                            executeQuery(query.toString());
                           // history.addQueryToTheHead(query.toString());
                        }

                    query = new StringBuilder();
                }
            }

        }
    }
}
