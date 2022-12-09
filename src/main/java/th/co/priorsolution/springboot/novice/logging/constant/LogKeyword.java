package th.co.priorsolution.springboot.novice.logging.constant;

public enum LogKeyword {

    REQUEST("REQUEST"),
    RESPONSE("RESPONSE")
    ;

    private final String code;

    LogKeyword(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

}
