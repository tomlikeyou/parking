package parking.common;

import lombok.NoArgsConstructor;

/**
 * Author: huang
 * Date: created in 2020/3/13 15:06
 * Description:
 * @author 24626
 */
@NoArgsConstructor
public class AjaxResultBuilder {

    private static AjaxResult result;

    public static AjaxResult build(ResultCode resultCode, String message, Object data) {
        result = new AjaxResult();
        result.setCode(resultCode);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}
