package com.daa.algo;

public class AlgoMain {
	public static void main(String[] args) {
//		int res  = new Recurrsion().towerOfHanoi(3, "A", "B", "C");
//		System.out.println(res);
//		Integer[] arr = new Integer[] { 4, 3, 2, 10, 12, 1, 5, 6 };
//		Recurrsion rec = new Recurrsion();
//		System.out.println(rec.printFibonacci(10));
//		System.out.println(rec.sumFibonacci(10));
//
//		System.out.println(rec.fact(7));
//		SortUtil.bubbleSort(arr);
//		Arrays.stream(arr).forEach(System.out::print);
//		System.out.println();
//		SortUtil.selectionSort(arr);
//		SortUtil.insertionSort(arr);
//		Arrays.stream(arr).forEach(s->System.out.print(s+","));
//		System.out.println();
//		System.out.println("123".substring(0, 1/2)+"--"+"123".charAt(1/2)+"--"+"123".substring((1/2)+1, 1));
//		int[] a= new int[] {7,2,5,6,3,4,0};
//		SortUtil.mergeSort(a);
//		System.out.println(Arrays.toString(a));
//		int[] arr=new int[] {-5,15,25,71,63};
//		closestNumbers(arr);
//		System.out.println("\n"+Arrays.toString(arr));
		System.out.println(5%1);
	}
	
	static void closestNumbers(int[] arr) {

        mergeSort(arr,0,arr.length-1);
        int dif=arr[1]-arr[0];
        System.out.print(arr[0]+" "+arr[1]);
        for(int i=1;i<arr.length-1;i++) {
            if(arr[i+1]-arr[i]==dif) {
                System.out.print(" "+arr[i]+" "+arr[i+1]);
            }
        }
    }

    static void mergeSort(int[] arr,int start,int end) {
        if(start<end){
            int mid = (start+end)/2;
            mergeSort(arr,start,mid);
            mergeSort(arr,mid+1,end);
            merge(arr,start,end,mid);
        }
    }

    static void merge(int[] arr,int start,int end,int mid){
        	int[] temp=new int[end-start+1];
            int i=start;
            int j=mid+1;
            int k=0;
            while(i<=mid&&j<=end) {
                if(arr[i]<=arr[j]) {
                    temp[k++]=arr[i++];
                } else {
                    temp[k++]=arr[j++];
                }
            }

            while(i<=mid){
                temp[k++]=arr[i++];
            }
            while(j<=end){
                temp[k++]=arr[j++];
            }
            for(int p=start;p<=end;p++){
                arr[p]=temp[p-start];
            }
    }


}
