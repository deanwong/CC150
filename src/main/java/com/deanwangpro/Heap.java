package com.deanwangpro;

import java.util.PriorityQueue;

public class Heap {

    public static void main(String[] args) {
//        PriorityQueue<Integer> heap = new PriorityQueue<>(10, (a, b) -> a - b);
//        heap.add(1);
//        heap.add(1);
//        heap.add(10);
//        System.out.println(heap);
//        PriorityQueue<String> heapS = new PriorityQueue<>(10, (a, b) -> a.compareTo(b));
//        heapS.add("a");
//        heapS.add("ba");
//        System.out.println(heapS);

        PriorityQueue<Article> store = new PriorityQueue<>(10, (a, b) -> {
            if (a.getComments() == b.comments) {
                return a.getTitle().compareTo(b.getTitle());
            }
            return b.getComments() - a.getComments();
        });
        Article a = new Article("a", 2);
        Article b = new Article("b", 2);
        store.add(a);
        store.add(b);
        System.out.println(store.peek().getTitle());
    }

    private static class Article {
        private String title;
        private int comments;

        public Article(String title, int comments) {
            this.title = title;
            this.comments = comments;
        }

        public String getTitle() {
            return title;
        }

        public int getComments() {
            return comments;
        }

    }

}
