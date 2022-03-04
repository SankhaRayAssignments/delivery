package com.deliveryhero.round2;

import java.util.List;
import java.util.*;
import java.util.stream.*;

/**
 * Implement a simple search engine. Required functionality:
 * - stores documents in memory for efficient search,
 * - allows the user to query these documents and return them,
 * - optional: ignores special characters: , . : ; ' " ! ? ( ) / \
 * - optional: the search function returns results sorted by relevance,
 * if query matches title it should be ranked higher than if it only matches words in the description.
 * - optional: how would you implement a partial match (prefix, typo's)?
 */

/**
 * @author sanray on 3/4/2022
 */
public class SearchServ {
    private final char[] specialCharacters = "-_,.:;'\"!?()/\\".toCharArray();

    public static void main(String[] args) {
        SearchServ searchEngine = new SearchServ();

        documentsToIndex.forEach(searchEngine::index);

        assertExpectedResults(searchEngine.search("Cheese"), Arrays.asList(1, 2));
        assertExpectedResults(searchEngine.search("Spicy BBQ"), Arrays.asList(3));
        assertExpectedResults(searchEngine.search("Pizza Chicken Ham"), Arrays.asList(3, 1));

        System.out.println("Tests passed!");
    }

    Map<String, HashSet<Integer>> titleMap = new HashMap<>();
    Map<String, HashSet<Integer>> contentMap = new HashMap<>();
    Set<Character> special = new HashSet<>();

    public void index(Document document) {

        if(special.size() == 0) {
            for(Character ch : specialCharacters) {
                special.add(ch);
            }
        }

        populateMap(document.content, document.id, contentMap);
        populateMap(document.title, document.id, titleMap);
        System.out.println(contentMap);
        System.out.println(titleMap);
    }

    private void populateMap(String input, int index, Map<String, HashSet<Integer>> map) {
        if (input == null || input.length() == 0) {
            return;
        }
        String[] tokens = input.split(" ");
        for (String tok : tokens) {
            String tok1 = getMassaging(tok);
            if (!map.containsKey(tok1.toLowerCase())) {
                map.put(tok1.toLowerCase(), new HashSet<>());
            }
            HashSet<Integer> indices = map.get(tok1);
            indices.add(index);
        }
    }

    private String getMassaging(final String tok) {
        char[] inpChar = tok.toLowerCase().toCharArray();
        StringBuilder builder = new StringBuilder();
        for(char ch : inpChar) {
            if(!special.contains(ch)) {
                builder.append(ch);
            }
        }
        return builder.toString();
    }

    public List<Integer> search(String query) {

        query = query.toLowerCase();

        HashSet<Integer> title = titleMap.getOrDefault(query, new HashSet<>());
        HashSet<Integer> content = contentMap.getOrDefault(query, new HashSet<>());
        HashSet<Integer> allIds = new HashSet<>();
        allIds.addAll(title);
        allIds.addAll(content);
        List<Integer> result = allIds.stream()
                .sorted((id1, id2) ->
                        {
                            if (titleMap.containsKey(id1) == true) {
                                return 0;
                            }
                            if (titleMap.containsKey(id2) == true) {
                                return 1;
                            }
                            if (contentMap.containsKey(id1) == true) {
                                return 0;
                            }
                            if (contentMap.containsKey(id2) == true) {
                                return 1;
                            }
                            return 0;
                        }
                )
                .collect(Collectors.toList());

        System.out.println(result);

        return result;
    }

    // Boilerplate
    static class Document {
        final int id;
        final String title;
        final String content;

        Document(int id, String title, String content) {
            this.id = id;
            this.title = title;
            this.content = content;
        }
    }

    private static void assertExpectedResults(List<Integer> results, List<Integer> expectedIds) {
        boolean hasSameSize = results.size() == expectedIds.size();
        boolean hasAll = results.stream().allMatch(docId -> expectedIds.contains(docId));
        if (!hasSameSize || !hasAll) {
            throw new RuntimeException("Results are different from expected");
        }
    }

    private static final List<Document> documentsToIndex = Arrays.asList(
            new Document(1, "Domino's Pizza - Simply Cheese",
                    "100% mozzarella cheese, parmesan, oregano on Domino's signature sauce."),
            new Document(2, "McDonald's - Double Cheeseburger",
                    "For those who enjoy layers. (100% beef and melty cheese) x2."),
            new Document(3, "Pizza Hut - BBQ Chicken",
                    "Spicy chicken chunks, chicken ham, pineapple chunks, capsicums, baked on a smoky BBQ sauce.")
    );
}
