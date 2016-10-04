package archavexm.util.xml.document;

public abstract class XMLNode {
    private String name;

    public XMLNode(String name){
        this.name = name;
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
}
