package com.daa.array;

public class Array2D {

	public static void main(String[] args) {
		printData(matrixProduct(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }, new int[][] { { 2, 3, 4 }, { 5, 6, 7 }, { 8, 9, 1 } }, 3, 3));
		System.out.println("---");
		printData(matrixProduct(new int[][] { { 1, 2 }, { 3, 4 } }, new int[][] { { 1, 1 }, { 1, 1 } }, 2, 2));
		int mat1[][] = { { 1, 1, 1, 1 }, { 2, 2, 2, 2 }, { 3, 3, 3, 3 }, { 4, 4, 4, 4 } };
		int mat2[][] = { { 1, 1, 1, 1 }, { 2, 2, 2, 2 }, { 3, 3, 3, 3 }, { 4, 4, 4, 4 } };
		printData(matrixProduct(mat1, mat2, 4, 4));
		System.out.println("-------------------");
		int[][] firstMatrix = { { 3, -2, 5 }, { 3, 0, 4 } };
		int[][] secondMatrix = { { 2, 3 }, { -9, 0 }, { 0, 4 } };
		printData(matrixProduct(firstMatrix, secondMatrix, 3, 2));
	}

	/***
	 * Given a m * n matrix grid which is sorted in non-increasing order both
	 * row-wise and column-wise.
	 * 
	 * Return the number of negative numbers in grid.
	 * 
	 * Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]] 
	 * 
	 * Output: 8
	 * 
	 * @param grid
	 * @return count
	 */
	public int countNegatives(int[][] grid) {
        int c=0;
        for(int i=0;i<grid.length;i++){    
            int start=0;
            int end=grid[i].length-1;
            while(start<=end){
                int mid = (start+end)/2;
                if(grid[i][mid]<0){
                    c=c+end-mid+1;
                    end=mid-1;
                } else {
                    start=mid+1;
                }
            }
        }
        return c;
    }
	
	/**
	 * print 2D array data.
	 * 
	 * @param array
	 */
	public static void printData(int[][] c) {
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[i].length; j++) {
				System.out.print(c[i][j] + " ");
			}
			System.out.println();
		}

	}

	/**
	 * o(n3)
	 * 
	 * @param arr
	 * @param brr
	 * @param arrColumn
	 * @param brrColumn
	 * @return result of two matrix
	 */
	public static int[][] matrixProduct(int[][] arr, int[][] brr, int arrColumn, int brrColumn) {
		int[][] res = new int[arr.length][brrColumn];
		if (arrColumn != brr.length) {
			return res;
		}

		for (int i = 0; i < arr.length; i++) {
			for (int k = 0; k < brrColumn; k++) {
				for (int j = 0; j < brr.length; j++) {
					res[i][k] = res[i][k] + arr[i][j] * brr[j][k];
				}
			}
		}
		return res;
	}

}
