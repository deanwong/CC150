package com.deanwangpro;


import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class GFG {
    //static
    public static void DFS(boolean visited[][], char arr[][], int i, int j, int n, int k) {
        if (arr[i][j] == 'X' && !visited[i][j]) {
            visited[i][j] = true;
            if (i + 1 < n) {
                DFS(visited, arr, i + 1, j, n, k);
            }
            if (i - 1 >= 0) {
                DFS(visited, arr, i - 1, j, n, k);
            }
            if (j + 1 < k) {
                DFS(visited, arr, i, j + 1, n, k);
            }
            if (j - 1 >= 0) {
                DFS(visited, arr, i, j - 1, n, k);
            }
        } else {
            return;
        }
    }

    public static void BFS(boolean visited[][], char arr[][], int i, int j, int n, int k) {
        LinkedList<Vertex> queue = new LinkedList<>();
        if (arr[i][j] == 'X' && !visited[i][j]) {
            Vertex vertex = new Vertex(i, j, arr[i][j]);
            queue.offer(vertex);
            while (queue.peek() != null) {
                vertex = queue.poll();
                visited[vertex.m][vertex.n] = true;
                if (vertex.m + 1 < n && arr[vertex.m + 1][vertex.n] == 'X' && !visited[vertex.m + 1][vertex.n]) {
                    queue.offer(new Vertex(vertex.m + 1, vertex.n, arr[vertex.m + 1][vertex.n]));
                }
                if (vertex.m - 1 >= 0 && arr[vertex.m - 1][vertex.n] == 'X' && !visited[vertex.m - 1][vertex.n]) {
                    queue.offer(new Vertex(vertex.m - 1, vertex.n, arr[vertex.m - 1][vertex.n]));
                }
                if (vertex.n + 1 < k && arr[vertex.m][vertex.n + 1] == 'X' && !visited[vertex.m][vertex.n + 1]) {
                    queue.offer(new Vertex(vertex.m, vertex.n + 1, arr[vertex.m][vertex.n + 1]));
                }
                if (vertex.n - 1 >= 0 && arr[vertex.m][vertex.n - 1] == 'X' && !visited[vertex.m][vertex.n - 1]) {
                    queue.offer(new Vertex(vertex.m, vertex.n - 1, arr[vertex.m][vertex.n - 1]));
                }
            }
        }
    }

    public static void DFSv2(boolean visited[][], char arr[][], int i, int j, int n, int k) {
        Stack<Vertex> stack = new Stack<>();
        if (arr[i][j] == 'X' && !visited[i][j]) {
            Vertex vertex = new Vertex(i, j, arr[i][j]);
            stack.push(vertex);
            while (!stack.empty()) {
                vertex = stack.pop();
                visited[vertex.m][vertex.n] = true;
                if (vertex.m + 1 < n && arr[vertex.m + 1][vertex.n] == 'X' && !visited[vertex.m + 1][vertex.n]) {
                    stack.push(new Vertex(vertex.m + 1, vertex.n, arr[vertex.m + 1][vertex.n]));
                }
                if (vertex.m - 1 >= 0 && arr[vertex.m - 1][vertex.n] == 'X' && !visited[vertex.m - 1][vertex.n]) {
                    stack.push(new Vertex(vertex.m - 1, vertex.n, arr[vertex.m - 1][vertex.n]));
                }
                if (vertex.n + 1 < k && arr[vertex.m][vertex.n + 1] == 'X' && !visited[vertex.m][vertex.n + 1]) {
                    stack.push(new Vertex(vertex.m, vertex.n + 1, arr[vertex.m][vertex.n + 1]));
                }
                if (vertex.n - 1 >= 0 && arr[vertex.m][vertex.n - 1] == 'X' && !visited[vertex.m][vertex.n - 1]) {
                    stack.push(new Vertex(vertex.m, vertex.n - 1, arr[vertex.m][vertex.n - 1]));
                }
            }
        }
    }

    static class Vertex {
        public int m;
        public int n;
        public char v;

        public Vertex(int m, int n, char v) {
            this.m = m;
            this.n = n;
            this.v = v;
        }
    }


    public static void main(String[] args) {
        Scanner ab = new Scanner(System.in);
        int l = ab.nextInt();
        while (l-- > 0) {
            int count = 0;
            int n = ab.nextInt();
            int k = ab.nextInt();
            char arr[][] = new char[n][k];
            for (int i = 0; i < n; i++) {
                String tm = new String(ab.next());
                for (int j = 0; j < k; j++) {
                    arr[i][j] = tm.charAt(j);
                }
            }
            boolean visited[][] = new boolean[n][k];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < k; j++) {
                    if (arr[i][j] == 'X' && !visited[i][j]) {
                        BFS(visited, arr, i, j, n, k);
                        count++;
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < k; j++) {
                    System.out.print(arr[i][j]);
                }
                System.out.println();
            }
            System.out.println(count);
        }

    }

}