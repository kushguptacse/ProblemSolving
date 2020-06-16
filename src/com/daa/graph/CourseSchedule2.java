package com.daa.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to
 * first take course 1, which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, return
 * the ordering of courses you should take to finish all courses.
 * 
 * There may be multiple correct orders, you just need to return one of them. If
 * it is impossible to finish all courses, return an empty array.
 * 
 * Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 * 
 * Output: [0,1,2,3] or [0,2,1,3]
 * 
 * Explanation: There are a total of 4 courses to take. To take course 3 you
 * should have finished both courses 1 and 2. Both courses 1 and 2 should be
 * taken after you finished course 0. So one correct course order is [0,1,2,3].
 * Another correct ordering is [0,2,1,3] .
 *
 */
class CourseSchedule {
	private List<Integer>[] adjArray;
	int count;

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		prepareGraph(numCourses, prerequisites);
		int[] recStack = new int[numCourses];
		int[] output = new int[numCourses];
		count = numCourses - 1;
		for (int i = 0; i < numCourses; i++) {
			if (cycleDfs(recStack, i, output)) {
				return new int[0];
			}
		}
		return output;
	}

	private boolean cycleDfs(int[] recStack, int i, int[] res) {
		if (recStack[i] == 1) {
			return true;
		}
		if (recStack[i] == 2) {
			return false;
		}
		recStack[i] = 1;
		for (int j : adjArray[i]) {
			if (cycleDfs(recStack, j, res)) {
				return true;
			}
		}
		res[count--] = i;
		recStack[i] = 2;
		return false;
	}

	private void prepareGraph(int n, int[][] edges) {
		adjArray = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adjArray[i] = new ArrayList<>();
		}
		for (int i = 0; i < edges.length; i++) {
			adjArray[edges[i][1]].add(edges[i][0]);
		}
	}

}
