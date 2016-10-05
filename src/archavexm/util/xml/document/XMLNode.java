package archavexm.util.xml.document;

// Should not touch the class
abstract class XMLNode {
    protected String name;
    protected String value;

    public XMLNode(String name, String value){
        this.name = name;
        this.value = value;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }

    public boolean hasValue(){
        return value.isEmpty() || value == null? false: true;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
