package archavexm.util.xml.document;

public class XMLAttribute extends XMLNode {
    private String value;

    public XMLAttribute(String name){
        this(name, "");
    }

    public XMLAttribute(String name, String value){
        super(name);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
