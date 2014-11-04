package ua.com.juja.study.sqlcmd.sql;

public interface QueryHistory {

    public static final int QUERY_BUFFER_COUNT = 20;

    public abstract String getNextQuery();

    public abstract String getPreviousQuery();

    public abstract void addQueryToTheHead(String query);
}
