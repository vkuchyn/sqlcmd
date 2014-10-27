package ua.com.juja.study.sqlcmd.sql;

import java.util.ArrayList;

/**
 * Created by Artem on 26.10.2014.
 */
public class ArrayQueryHistory implements QueryHistory {

    private ArrayList<String> queryList = new ArrayList<String>(QUERY_BUFFER_COUNT);
    public static final int QUERY_BUFFER_COUNT = 20;
    private int currentQueryIndex = -1;

    @Override
    public String getNextQuery() {
        if (!queryList.isEmpty() && queryList.size() - 1 > currentQueryIndex) {
            return queryList.get(++currentQueryIndex);
        }
        return null;
    }

    @Override
    public String getPreviousQuery() {
        if (!queryList.isEmpty() && currentQueryIndex > 0) {
            return queryList.get(--currentQueryIndex);
        }
        return null;
    }

    @Override
    public void addQueryToTheHead(String query) {
        // If query list is full remove last query
        if (queryList.size() + 1 >= QUERY_BUFFER_COUNT) {
            queryList.remove(QUERY_BUFFER_COUNT - 1);
        }
        // Add query to the head
        // TODO: ArrayList add to the head is low performance operation. Replace with Queue.
        queryList.add(0, query);

        // Reset query index
        currentQueryIndex = -1;
    }

    public static void main(String[] args) {
        ArrayQueryHistory queryhistory = new ArrayQueryHistory();
        queryhistory.addQueryToTheHead("Query 1");
        queryhistory.addQueryToTheHead("Query 2");
        queryhistory.addQueryToTheHead("Query 3");
        queryhistory.addQueryToTheHead("Query 4");
        System.out.println(queryhistory.getNextQuery());
        System.out.println(queryhistory.getNextQuery());
        System.out.println(queryhistory.getNextQuery());
        System.out.println(queryhistory.getNextQuery());
        System.out.println(queryhistory.getNextQuery());
        System.out.println(queryhistory.getNextQuery());
        queryhistory.addQueryToTheHead("Query 5");
        System.out.println(queryhistory.getNextQuery());
        System.out.println(queryhistory.getNextQuery());
        System.out.println(queryhistory.getNextQuery());
        System.out.println(queryhistory.getNextQuery());
        System.out.println(queryhistory.getNextQuery());
        System.out.println(queryhistory.getNextQuery());
        System.out.println(queryhistory.getPreviousQuery());
    }
}
