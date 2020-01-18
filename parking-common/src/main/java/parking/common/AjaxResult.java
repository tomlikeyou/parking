package parking.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huang
 * @date 2020/1/13 14:51
 * @Disc
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AjaxResult<T> {

    private ResultCode code;

    private String message;

    private T data;

}
