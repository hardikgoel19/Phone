package xyz.phone.manager.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class GroupList<T> {

    public List<List<T>> groupBy(
            List<T> entities,
            Function<T, String> function,
            Comparator<T> comparator) {
        if (entities == null || entities.isEmpty()) return new ArrayList<>();
        Map<String, List<T>> messageMap = new HashMap<>();
        entities.forEach(entity -> {
            if (entity == null) return;
            putInMap(function.apply(entity), entity, messageMap);
        });
        List<List<T>> list = new ArrayList<>(messageMap.values());
        list.forEach(_list -> _list.sort(comparator));
        return list;
    }

    private void putInMap(
            String key,
            T entity,
            Map<String, List<T>> map
    ) {
        if (map.containsKey(key)) {
            List<T> temp = map.get(key);
            if (temp != null) temp.add(entity);
        } else {
            List<T> temp = new ArrayList<>();
            temp.add(entity);
            map.put(key, temp);
        }
    }
}
