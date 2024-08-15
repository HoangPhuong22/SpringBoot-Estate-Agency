package zerocoder.com.Estate.utils;

import java.util.ArrayList;
import java.util.List;

public class PageUtils {
    public static List<Integer> getPageNumbers(int totalPages, int pageSize, int pageNo) {
        int start = pageNo - pageSize / 2;
        int end = pageNo + pageSize / 2;
        if(start < 1) {
            start = 1;
            end = Math.min(pageSize, totalPages);
        }
        if(end > totalPages) {
            end = totalPages;
            start = Math.max(1, totalPages - pageSize + 1);
        }
        List<Integer> pageRange = new ArrayList<>();
        for(int i = start; i <= end; i++) {
            pageRange.add(i);
        }
        return pageRange;
    }
}
