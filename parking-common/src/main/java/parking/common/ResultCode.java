package parking.common;

/**
 * @author huang
 * @date 2020/1/13 14:55
 * @Disc
 **/
public enum ResultCode {
    LOGIN_SUCCESS(1),
    LOGIN_FAIL(-1),
    SELECT_SUCCESS(2),
    SELECT_FAIL(-2),
    EDIT_SUCCESS(3),
    EDIT_FAIL(-3),
    DELETE_SUCCESS(4),
    DELETE_FAIL(-4),
    SAVE_SUCCESS(5),
    SAVE_FAIL(-5);
    private int code;

    ResultCode(int code) {
        this.code = code;
    }
}
