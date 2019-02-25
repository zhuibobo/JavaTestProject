package Base;

import java.util.Comparator;
import java.util.List;

public class ListSort {
    public static void SortInt(List<Integer> connCodeList) {
        connCodeList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1>o2){
                    return -1;
                }
                else if(o1==o2){
                    return 0;
                }
                else {
                    return 1;
                }
            }
        });
    }

    /*public static void SortString(List<String> connCodeList) {
        connCodeList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1>o2){
                    return -1;
                }
                else if(o1==o2){
                    return 0;
                }
                else {
                    return 1;
                }
            }
        });
    }*/
}
