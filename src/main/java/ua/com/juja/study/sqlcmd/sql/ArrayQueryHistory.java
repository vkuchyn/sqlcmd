package ua.com.juja.study.sqlcmd.sql;

/**
 * Created by Nikolay on 04.11.2014.
 */
public class ArrayQueryHistory implements  QueryHistory{


    private String[] listOfRequests = new String[QUERY_BUFFER_COUNT];
    private int queryIndex = -1;
    private int queryCount = 0;

    @Override
    public String getNextQuery() {
        if(queryCount > 0 && queryIndex > 0){
            return listOfRequests[--queryIndex];
        }
        return null;
    }

    @Override
    public String getPreviousQuery() {
        if(queryCount > 0 && queryCount - 1 > queryIndex){
            return listOfRequests[++queryIndex];
        }
        return null;
    }

    @Override
    public void addQueryToTheHead(String query) {
        for(int i = listOfRequests.length - 2; i >= 0; i--){
            listOfRequests[i + 1] = listOfRequests[i];
        }
        listOfRequests[0] = query;
        if(queryCount < QUERY_BUFFER_COUNT){
            queryCount++;
        }
        queryIndex = -1;
    }
}
