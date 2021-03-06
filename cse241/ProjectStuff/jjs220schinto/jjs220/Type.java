import java.util.ArrayList;

public class Type {
    private Integer type_id;
    private String name;
    private ArrayList<Type> children;
    private Type superType;

    public Type(Integer type_id, String name, ArrayList<Type> children,
            Type superType) {
        this.setType_id(type_id);
        this.setName(name);
        this.children = children;
        this.setSuperType(superType);
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Type> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Type> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return name;
    }

    public Type getSuperType() {
        return superType;
    }

    public void setSuperType(Type superType) {
        this.superType = superType;
    }
}
