import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Scanner;

class Diffy{
    private static int[][] dp;

    private static String readContent(String path) throws Exception{
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    // move both i and j ptrs if characters match else
    // move 1 pointer at a time
    private static String findLCSHelper(String a,String b,int i,int j){
        if (i < 0 || j < 0) {
            return "";
        }

        if (dp[i][j] != -1) {
            return "";
        }

        if (a.charAt(i) == b.charAt(j)) {
            String result = findLCSHelper(a, b, i - 1, j - 1) + a.charAt(i);
            dp[i][j] = result.length();
            return result;
        } 
        String lcs1 = findLCSHelper(a, b, i - 1, j);
        String lcs2 = findLCSHelper(a, b, i, j - 1);
        String result = (lcs1.length() > lcs2.length()) ? lcs1 : lcs2;
        dp[i][j] = result.length();
        return result;
    }

    private static void LCSCompare(String content1,String content2){
        int m = content1.length();
        int n = content2.length();
        dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(findLCSHelper(content1,content2,content1.length()-1,content2.length()-1));
    }

    public static void main(String[] args) throws Exception{
        // String content1 = readContent(args[1]);
        // String content2 = readContent(args[2]);
        String content1 = "ABCDEF";
        String content2 = "CsdfdfDEF";
        LCSCompare(content1,content2);
    }
}