package zerocoder.com.Estate.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@Builder
public class PageResponse <T> implements Serializable {
    private T content;
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalPages;
    private Integer totalElements;
}
