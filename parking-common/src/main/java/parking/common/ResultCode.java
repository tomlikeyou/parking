package parking.common;

/**
 * @author huang
 * @date 2020/1/13 14:55
 * @Disc
 **/
public enum ResultCode {
    SUCCESS(1),
    FAIL(0);
    private int code;

    ResultCode(int code) {
        this.code = code;
    }
}
