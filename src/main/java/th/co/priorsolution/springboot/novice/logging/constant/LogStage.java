package th.co.priorsolution.springboot.novice.logging.constant;

public enum LogStage {

    START("START")
    , CALL_SERVICE("CALL_SERVICE")
    , DO_DB("DO_DB")
    , END("END")
    ;

    private final String code;

    LogStage(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
