package com.deanwangpro;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileKeywordLoopup {


    private static String[] keywords = new String[10];

    public static void main(String[] args) throws IOException {
        keywords[0] = "INFO";
        keywords[1] = "ERROR";
        File file = new File("/opt/log/linkflowtech/linkflow-all.log");
        ArrayList<Map.Entry<String, Integer>> list = sort(keywordLookup(file, keywords));
        list.forEach(o -> {
            System.out.println(o.getKey());
            System.out.println(o.getValue());
        });
    }


    private static ArrayList<Map.Entry<String, Integer>> sort(Map<String, Integer> container) {
        ArrayList<Map.Entry<String, Integer>> entryArrayList = new ArrayList<>(container.entrySet());
        entryArrayList.sort((o1, o2) -> (o2.getValue() - o1.getValue()));
        return entryArrayList;
    }

    private static Map<String, Integer> keywordLookup(File file, String[] keywords) throws IOException {
        Map<String, Integer> container = new HashMap<>(8);
        List<String> lines = Files.readAllLines(file.toPath());
        for (String line : lines) {
            for (String keyword : keywords) {
                if (keyword == null) {
                    continue;
                }
                int count = countMatches(line, keyword);
                if (container.containsKey(keyword)) {
                    count += container.get(keyword);
                }
                container.put(keyword, count);
            }
        }
        return container;
    }

    private static int countMatches(String str, String search) {
        if (str != null && search != null) {
            int count = 0;
            for (int idx = 0; (idx = str.indexOf(search, idx)) != -1; idx += search.length()) {
                ++count;
            }
            return count;
        } else {
            return 0;
        }
    }
}
