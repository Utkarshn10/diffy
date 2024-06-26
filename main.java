import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

class Diffy{
    private static String[][] dp;
    private static List<String> diffString;

    private static List<String> readContent(String path) throws Exception{
        String content = new String(Files.readAllBytes(Paths.get(path)));
        Scanner sc = new Scanner(content);
        List<String> contentArray = new ArrayList<>();
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            contentArray.add(line);
        }
        sc.close();
        return contentArray;
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
        for (String[] v:dp) {
            Arrays.fill(v,null);
        }
      
        String result = findLCSHelper(content1,content2,content1.length()-1,content2.length()-1);
        if(maxString[0].length() < result.length()){
            maxString[0] = result;
        }
    }

    public static void main(String[] args) throws Exception{
        List<String> content1Array = readContent(args[0]);
        List<String> content2Array = readContent(args[1]);
       
        diffString = new ArrayList<>();
        boolean[] vis = new boolean[content2Array.size()];
        // List<String> ans = new ArrayList<>();
        String ans = "";

        int last_matched_jindex = 0,j=0;
        for(int i=0;i<content1Array.size() && i<content2Array.size();i++){
            String[] maxString = new String[1];
            maxString[0] = "";
            LCSCompare(content1Array.get(i),content2Array.get(i),vis,maxString);
            if(maxString[0].length() == content1Array.get(i).length()){
               continue;
            }
            else {
                ans+="1. "+content1Array.get(i)+"\n";
                ans+="2. "+content2Array.get(i)+"\n";
            }
        }
        System.out.println(ans);
    }
}