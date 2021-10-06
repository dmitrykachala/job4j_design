package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added;
        int changed = 0;
        int deleted = 0;

        Map<Integer, String> mapPrev = new HashMap<>();
        Map<Integer, String> mapCurr = new HashMap<>();

        for (User prev : previous) {
            mapPrev.put(prev.getId(), prev.getName());
        }

        for (User curr : current) {
            mapCurr.put(curr.getId(), curr.getName());
        }

        for (Integer keyPrev : mapPrev.keySet()) {
            if (mapCurr.containsKey(keyPrev)
                    && !mapPrev.get(keyPrev).equals(mapCurr.get(keyPrev))) {
                    changed++;
            }
            if (!mapCurr.containsKey(keyPrev)) {
                deleted++;
            }
        }
        added = mapCurr.size() - mapPrev.size() + deleted;
        return new Info(added, changed, deleted);
    }

}
