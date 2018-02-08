package in.tech.blogger.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    public static <T> List<T> intersect(List<T> list1, List<T> list2) {
        ArrayList<T> arr = new ArrayList<T>();
        for (T element : list1) {
            if (list2.contains(element)) {
                arr.add(element);
            }
        }
        return arr;
    }

    public static <T> List<T> minus(List<T> from, List<T> list) {
        ArrayList<T> arr = new ArrayList<T>();
        for (T element : from) {
            if (!list.contains(element)) {
                arr.add(element);
            }
        }
        return arr;
    }


}
