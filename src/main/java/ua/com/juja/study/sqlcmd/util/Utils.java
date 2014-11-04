package ua.com.juja.study.sqlcmd.util;

public class Utils {

    public  static String arrayToString(String[] array){
    String res = "";
    for (int i = 0; i <array.length; i++){
        res += array[i] + " ";
    }
    return res;
    }
}
