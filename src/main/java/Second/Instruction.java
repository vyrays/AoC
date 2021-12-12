package Second;

public record Instruction(String action, Integer value) {

    public String getAction() {
        return action;
    }

    public Integer getValue() {
        return value;
    }
}
