package com.project.ewdj.util;

import java.util.*;

import com.project.ewdj.entity.Favorite;

public class FavoriteUtils {

    public static List<Object[]> countDuplicates(List<Favorite> favorites) {
        Map<Long, Integer> countMap = new HashMap<>();
        Map<Long, String> nameMap = new HashMap<>();

        for (Favorite favorite : favorites) {
            Long bookId = favorite.getBook().getId();
            String bookName = favorite.getBook().getBookName();

            countMap.put(bookId, countMap.getOrDefault(bookId, 0) + 1);
            nameMap.put(bookId, bookName);
        }

        List<Object[]> result = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : countMap.entrySet()) {
            Long bookId = entry.getKey();
            Integer count = entry.getValue();
            String bookName = nameMap.get(bookId);

            result.add(new Object[] { bookId, bookName, count });
        }

        result.sort((a, b) -> (int) b[2] - (int) a[2]);

        return result;
    }

}
