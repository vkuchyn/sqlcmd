package ua.com.juja.study.sqlcmd.sql;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bizon4ik on 29.10.2014.
 */
public class ArrayQueryHistory  implements QueryHistory {
    private static List<String> history = new ArrayList<String>();
    private int currentQuery = history.size();


    @Override
    public String getPreviousQuery() {
        String string = "";

        if (currentQuery+1 <= history.size()) {
            string = history.get(++currentQuery);
        }else {
            string = null;
        }

        return string;
    }

    @Override
    public String getNextQuery() {
        String string = "";

        if (currentQuery-1 >= 0) {
            string = history.get(--currentQuery);
        }else {
            string = null;
        }

        return string;
    }

    @Override
    public void addQueryToTheHead(String query) {
        if (history.size() >= QUERY_BUFFER_COUNT) history.remove(0);
        history.add(query);
        currentQuery = history.size();
    }

}
