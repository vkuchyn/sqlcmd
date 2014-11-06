package ua.com.juja.study.sqlcmd.engine;

import ua.com.juja.study.sqlcmd.database.DatabaseExecutor;
import ua.com.juja.study.sqlcmd.database.Row;
import ua.com.juja.study.sqlcmd.sql.QueryHistory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created with IntelliJ IDEA.
 * User: viktor
 * Date: 10/22/14
 * Time: 11:28 PM
 */
public class KeyboardManager {
    private QueryHistory history;
    private DatabaseExecutor databaseExecutor;

    private class QueryReader {
        private static final String QUERY_NEWLINE_SEPARATOR = " ";
        private final BufferedReader reader;

        public QueryReader(Reader in) {
            reader = new BufferedReader(in);
        }

        public String read() throws IOException {
            boolean exit = false;
            StringBuilder query = new StringBuilder();
            while (!exit) {
                String line = reader.readLine();
                if ("\\q".equals(line)) {
                    query = new StringBuilder();
                    exit = true;
                } else if (line != null && line.length() > 0) {
                    if (query.length() > 0) {
                        query.append(QUERY_NEWLINE_SEPARATOR);
                    }
                    query.append(line);
                    if (isQueryFinalized(query.toString())) {
                        exit = true;
                    }
                }
            }
            return query.toString();
        }

        private boolean isQueryFinalized(String query) {
            boolean result = false;
            if (null != query
                    && query.length() > 0
                    && query.charAt(query.length() - 1) == ';') {
                result = true;
            }
            return result;
        }
    }

    public KeyboardManager(QueryHistory queryHistory, DatabaseExecutor databaseExecutor) {
        this.history = queryHistory;
        this.databaseExecutor = databaseExecutor;
    }

    public void startListenUserKeyboard() throws IOException {
        QueryReader queryReader = new QueryReader(new InputStreamReader(System.in));
        String query = queryReader.read();
        while (query.length() > 0) {
            executeQuery(query);
            query = queryReader.read();
        }
    }

    public Row[] executeQuery(String query) {
        System.out.println("Executing: " + query);
        history.addQueryToTheHead(query);
        return databaseExecutor.executeSqlScript(query);
    }
}
