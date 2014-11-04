package ua.com.juja.study.sqlcmd.sql;

/**
 * Created by Nikolay on 04.11.2014.
 */
public class ArrayQueryHistory implements  QueryHistory{
    private String[] array = new String[QUERY_BUFFER_COUNT];
    private String PreviousQuery = "";
    private String NextQuery = "";
    private int QueryIndex = -1;

    public ArrayQueryHistory(String[] array) {
        this.array = array;
    }

    @Override
    public String getNextQuery() {
        if (QueryIndex == -1) {
            NextQuery = "";
        }
        if (QueryIndex == 0) {
            NextQuery = array[QueryIndex];
        } else {
            NextQuery = array[--QueryIndex];
        }
        return NextQuery;
    }

    @Override
    public String getPreviousQuery() {
        QueryIndex++;
        if (array[QueryIndex] == null) {
            PreviousQuery = array[QueryIndex - 1];
            QueryIndex--;
        }
        PreviousQuery = array[QueryIndex];
        return PreviousQuery;
    }

    @Override
    public void addQueryToTheHead(String query) {
        for (int i = QUERY_BUFFER_COUNT - 1; i > 0; i--) {
            if (i != QUERY_BUFFER_COUNT - 1) {
                array[i] = array[i - 1];
            }
        }
    }
}
