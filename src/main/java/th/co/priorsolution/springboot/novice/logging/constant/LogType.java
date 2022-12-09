package th.co.priorsolution.springboot.novice.logging.constant;

public enum LogType {

    APP("APP"),
    SERVICE("SERVICE"),
    REPOSITORY("REPOSITORY"),
    ERROR("ERROR")
    ;

    private final String code;

    LogType(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
