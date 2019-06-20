package com.daa.tree.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.daa.algo.Recurrsion;

public class SampleRun {
	public static void main(String[] args) {
		Recurrsion re=new Recurrsion();
		System.out.println(re.max(new int[] {22,4,1,5,3,0}));
//		re.fun(4);
	}
	
	static int fun1(int x, int y)  
	{ 
	  if(x == 0) 
	    return y; 
	  else
	    fun1(x - 1,  x + y);
	  return y;
	} 
	
	static int twoStacks(int x, int[] a, int[] b) {
        Stack<Integer> stackA = new Stack<>();
        Stack<Integer> stackB = new Stack<>();
        for(int i=a.length-1;i>=0;i--) {
            stackA.push(a[i]);
        }
        for(int i=b.length-1;i>=0;i--) {
            stackB.push(b[i]);
        }         
        int sum=0;
        int count=-1;
        while(count<(a.length+b.length)){
            if(sum>x || (stackA.empty() &&stackB.empty())){
                return count;
            } else {
                count++;
            }
            if(stackA.empty() && !stackB.empty() ){
               sum = sum +stackB.pop();
               
            }
            else if(stackB.empty()&& !stackA.empty()){
               sum = sum +stackA.pop();
               
            } else {
               int an=stackA.peek();
               int bn=stackB.peek();
               
               if(an<=bn) {
                   sum = sum +stackA.pop();
               } else  {
                   sum = sum +stackB.pop(); 
               } 
           }
        
        }
        
        return count;
   }
	
	static int commonChild(String s1, String s2) {
        int max = 0;
        for(int k=0;k<s1.length();k++) {
            int c = 0;
            int pos =0;
            for(int i=k;i<s1.length();i++) {
                for(int j=pos;j<s2.length();j++) {
                    if(s1.charAt(i)==s2.charAt(j)){
                        c++;
                        pos = j+1;
                        break;
                    }
                }
            }
            if(c>max) {
                max=c;
            }
        }
        return max;
    }
	
	static String[] weightedUniformStrings(String s, int[] queries) {

        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<s.length()-1;i++) {
            int fir = s.charAt(i);
            int sec = s.charAt(i+1);
            if(fir==sec) {
                int r = sec-96;
                if(map.containsKey(r)){
                    int val = map.get(r)+1;
                    map.put(r*val,1);
                    map.put(r, map.get(r)+1);
                }  else {
                	map.put(r, 2);
                	map.put(r*2, 1);
                }
                
            } else {
	            map.put(sec-96,1);
	            map.put(fir-96,1);
            }
        }
        String res[]=new String[queries.length];
        for(int j=0;j<queries.length;j++) {
            if(map.containsKey(queries[j])) {
                res[j]="Yes";
            } else {
                res[j]="No";
            }
        }
        return res;
    }
	 
}