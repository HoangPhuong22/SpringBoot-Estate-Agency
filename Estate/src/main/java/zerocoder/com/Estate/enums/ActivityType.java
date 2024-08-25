package zerocoder.com.Estate.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@Getter
public enum ActivityType {
    MEETING("Gặp gỡ khách hàng"),
    CONTRACT("Thảo luận hợp đồng"),
    OTHER("Khác");

    private final String description;

    ActivityType(String description) {
        this.description = description;
    }
    public static Map<String, String> type()
    {
        Map<String, String> typeCodes = new LinkedHashMap<>();
        for(ActivityType it : ActivityType.values())
        {
            typeCodes.put(it.toString(), it.description);
        }
        return typeCodes;
    }
}
