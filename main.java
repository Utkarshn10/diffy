import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Scanner;

class Diffy{
    private static String diffString = "";

    private static String readContent(String path) throws Exception{
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    // move both i and j ptrs if characters match else
    // move 1 pointer at a time
    private static String find(String a,String b,int i,int j,String ans,int[][] dp){
        if(i<0 || j<0)return ans;
        if(dp[i][j]!=-1)return ans;

        if(a.charAt(i) == b.charAt(j)){
            ans += a.charAt(i);
            if(ans.length() > diffString.length())diffString = ans;
            dp[i][j] = Math.max(ans.length(),diffString.length());
            return find(a,b,i-1,j-1,ans,dp);
        }

        String s1 = find(a,b,i-1,j,ans,dp);
        String s2 = find(a,b,i,j-1,ans,dp);
        dp[i][j] = Math.max(s1.length(),s2.length());
        if(s1.length() > s2.length()){
            diffString = s1;
            return s1;
        }
        diffString = s2;
        return s2;
    }

    private static void LCSCompare(String content1,String content2){
        int[][] dp = new int[content1.length()+10][content2.length()+10];
        for(int[] v:dp){
            Arrays.fill(v,-1);
        }
        find(content1,content2,content1.length()-1,content2.length()-1,"",dp);
    }

    public static void main(String[] args) throws Exception{
        // String content1 = readContent(args[1]);
        // String content2 = readContent(args[2]);
        String content1 = "ABCDEF";
        String content2 = "CDGFEF";
        LCSCompare(content1,content2);
        StringBuilder se = new StringBuilder(diffString);
         
        System.out.println("Difference: "+se.reverse());        
    }
}