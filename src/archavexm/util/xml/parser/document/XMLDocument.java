package archavexm.util.xml.parser.document;

/**
 * This class encapsulates an entire xml document in the disk.
 * */
public class XMLDocument {
    static final String ROOT_ELEMENT = "root";

    private String filePath;
    private XMLDeclaration declaration;
    private XMLElement root;

    public XMLDocument(String filePath){
        this(filePath, new XMLElement(ROOT_ELEMENT));
    }

    public XMLDocument(String filePath, XMLElement root){
        this(filePath, null, root);
    }

    public XMLDocument(String filePath, XMLDeclaration declaration, XMLElement root){
        this.filePath = filePath;
        this.declaration = declaration;
        this.root = root;
    }

    public String getFilePath(){
        return filePath;
    }

    public boolean hasDeclaration(){
        if (declaration == null)
            return false;

        return true;
    }

    public XMLDeclaration getDeclaration() {
        return declaration;
    }

    public void setDeclaration(XMLDeclaration declaration) {
        this.declaration = declaration;
    }

    public XMLElement getRoot(){
        return root;
    }

    public void setRoot(XMLElement root) {
        this.root = root;
    }
}