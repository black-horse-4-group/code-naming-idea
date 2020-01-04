package enums;

public enum NameTypeEnum {
    CLASS(0, "类"),
    INTERFACE(1, "接口"),
    METHOD(2, "方法"),
    ENUM(3, "枚举"),
    SUB_CLASS(4, "子类"),
    CONSTANT(5, "常量"),
    VARIABLE(6, "变量"),
    PACKAGE(7, "包");

    private int type;

    public int getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    private String value;

    private NameTypeEnum(int type, String value) {
        this.type = type;
        this.value = value;
    }
}
