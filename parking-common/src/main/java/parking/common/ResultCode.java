package parking.common;

/**
 * @author huang
 * @date 2020/1/13 14:55
 * @Disc
 **/
public enum ResultCode {
    UNKNOW_ERROR(0, "未知错误"),
    LOGIN_SUCCESS(1, "登录成功"),
    LOGIN_FAIL(-1, "登陆失败"),
    SELECT_SUCCESS(2, "查询成功"),
    SELECT_FAIL(-2, "查询失败"),
    EDIT_SUCCESS(3, "编辑成功"),
    EDIT_FAIL(-3, "编辑失败"),
    DELETE_SUCCESS(4, "删除成功"),
    DELETE_FAIL(-4, "删除失败"),
    SAVE_SUCCESS(5, "保存成功"),
    SAVE_FAIL(-5, "保存失败"),
    UPLOAD_SUCCESS(6, "上传成功"),
    UPLOAD_FAIL(-6, "上传失败");
    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String findMessageByCode(ResultCode target) {
        for (ResultCode resultCode : ResultCode.values()) {
            if (resultCode.code == target.code) {
                return resultCode.message;
            }
        }
        return UNKNOW_ERROR.message;
    }
}
