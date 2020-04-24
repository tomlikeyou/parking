package parking.common;

/**
 * @author huang
 * @date 2020/1/13 14:55
 * @Disc
 **/
public enum ResultCode {
    LOGGIN_TIMEOUT(-8,"登录超时"),
    UNAUTH(401,"当前用户未拥有该权限"),
    UNKNOW_ERROR(0, "未知错误"),
    LOGIN_SUCCESS(1, "登录成功"),
    LOGIN_FAIL(-1, "登录失败"),
    LOGOUT(200,"退出成功"),
    SELECT_SUCCESS(2, "查询成功"),
    SELECT_FAIL(-2, "查询失败"),
    EDIT_SUCCESS(3, "编辑成功"),
    EDIT_FAIL(-3, "编辑失败"),
    DELETE_SUCCESS(4, "删除成功"),
    DELETE_FAIL(-4, "删除失败"),
    SAVE_SUCCESS(5, "保存成功"),
    SAVE_FAIL(-5, "保存失败"),
    UPLOAD_SUCCESS(6, "上传成功"),
    UPLOAD_FAIL(-6, "上传失败"),
    NOPERMS(7,"无操作权限");
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
