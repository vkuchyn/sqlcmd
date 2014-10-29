package ua.com.juja.study.sqlcmd.sql;

import java.util.ArrayList;

/**
 * Created by Bizon4ik on 29.10.2014.
 */
public class ArrayQueryHistory  implements QueryHistory {
    private static ArrayList<String> history = new ArrayList<String>();
    private static final byte historySize = 100;
    private int currentQuery = history.size();


    @Override
    public String getNextQuery() {
        String string = "";

        if (currentQuery+1 <= history.size()) {
            string = history.get(++currentQuery);
        }else {
            string = "there isn't next Query";
        }

        return string;
    }

    @Override
    public String getPreviousQuery() {
        String string = "";

        if (currentQuery-1 >= 0) {
            string = history.get(--currentQuery);
        }else {
            string = "there isn't more Queries in history";
        }

        return string;
    }

    @Override
    public void addQueryToTheHead(String query) {
        if (history.size() >= historySize) history.remove(0);
            history.add(query);
            currentQuery++;

    }

}
