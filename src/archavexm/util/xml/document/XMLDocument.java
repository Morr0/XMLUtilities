package archavexm.util.xml.document;

// All the IElements methods work on the root element
// Should have a root element or it will not parse
// Will Always have a root element so there is no need to check it
/**
 * This class encapsulates an entire xml document in the disk.
 *  -Should be provided with a path to where th document will be written into when the user wants to save the document.
 *  -It should have at least a root element.
 *  -Declaration statement is not required.
 *  -Easy to use class and all elements parent elements should from the root element.
 * */
public class XMLDocument {
    private String filePath;
    private XMLDeclaration declaration;
    private XMLElement root;

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
