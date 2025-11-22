package compiler.semantic;

public class Type {
    public static final Type INT = new Type("int");
    public static final Type FLOAT = new Type("float");
    public static final Type VOID = new Type("void");
    public static final Type ERROR = new Type("error");

    private String name;

    public Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Type type = (Type) obj;
        return name.equals(type.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
