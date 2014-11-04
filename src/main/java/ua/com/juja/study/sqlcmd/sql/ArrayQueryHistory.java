package ua.com.juja.study.sqlcmd.sql;


public class ArrayQueryHistory implements QueryHistory {
    private int IndexCount = -1;
    private String[] ListOfCommands = new String[QUERY_BUFFER_COUNT];
    private String PreviousQuery = "";
    private String NextQuery = "";

    @Override
    public String getNextQuery() {

        if(IndexCount == -1){
            NextQuery = "";
        } if (IndexCount == 0){
            NextQuery = ListOfCommands[IndexCount];
        } else{
            NextQuery = ListOfCommands[--IndexCount];
        }
        return NextQuery;
    }

    @Override
    public String getPreviousQuery() {
        IndexCount++;
        if(ListOfCommands[IndexCount] == null){
            PreviousQuery = ListOfCommands[IndexCount - 1];
            IndexCount--;
        }
        PreviousQuery = ListOfCommands[IndexCount];
        return PreviousQuery;
    }

    @Override
    public void addQueryToTheHead(String query) {
        for (int i = QUERY_BUFFER_COUNT - 1; i > 0; i--){
            if(i != QUERY_BUFFER_COUNT - 1){
                ListOfCommands[i] = ListOfCommands[i - 1];
            }
        }
        ListOfCommands[0] = query;
    }
}