import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Scanner;

import javax.management.openmbean.ArrayType;

class Diffy{
    private static String[][] dp;
    private static List<String> diffString;

    private static String readContent(String path) throws Exception{
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    // move both i and j ptrs if characters match else
    // move 1 pointer at a time
    private static String findLCSHelper(String a,String b,int i,int j){
        if (i < 0 || j < 0) {
            return "";
        }

        if (dp[i][j] != null) {
            return dp[i][j];
        }

        if (a.charAt(i) == b.charAt(j)) {
            String result = findLCSHelper(a, b, i - 1, j - 1) + a.charAt(i);
            return dp[i][j] = result;
        } 
        String lcs1 = findLCSHelper(a, b, i - 1, j);
        String lcs2 = findLCSHelper(a, b, i, j - 1);
        String result = (lcs1.length() > lcs2.length()) ? lcs1 : lcs2;
        return dp[i][j] = result;
    }

    private static void LCSCompare(String content1,String content2,boolean[] vis,String[] maxString){
        int m = content1.length();
        int n = content2.length();

        dp = new String[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = null;
            }
        }
      
        String result = findLCSHelper(content1,content2,content1.length()-1,content2.length()-1);
        if(maxString[0].length() < result.length()){
            maxString[0] = result;
        }
    }

    public static void main(String[] args) throws Exception{
        // String content1 = readContent(args[1]);
        // String content2 = readContent(args[2]);
        String[] content1Array = new String[]
        {"This is a test which contains:", "this is the lcs"};
        String[] content2Array = new String[]{"this is the lcs", "we're testing"};
        diffString = new ArrayList<>();
        boolean[] vis = new boolean[content2Array.length];
        List<String> ans = new ArrayList<>();

        for(int i=0;i<content1Array.length;i++){
            String[] maxString = new String[1];
            maxString[0] = "";
            for(int j=0;j<content2Array.length;j++){
                if(!vis[j]) LCSCompare(content1Array[i],content2Array[j],vis,maxString);
            }
            if(maxString[0].length() == content1Array[i].length()) ans.add(maxString[0]);
        }
        System.out.println(ans);
    }
}