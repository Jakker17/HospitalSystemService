package eng.hospitalSystemService.app;

public class Alert {
    public enum Type{
        success,
        danger
    }

    public Alert(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    public Type getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    private final Type type;
    private final String text;
}
