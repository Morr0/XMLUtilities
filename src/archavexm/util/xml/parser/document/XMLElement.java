package archavexm.util.xml.parser.document;

import java.util.ArrayList;

/**
 * This class encapsulates an element with all of it's child elements, attributes and value.
 * */
public class XMLElement extends XMLNode {
    private ArrayList<XMLElement> elements;
    private ArrayList<XMLAttribute> attributes;

    public XMLElement(String name){
        this(name, "");
    }

    public XMLElement(String name, String value){
        super(name, value);

        elements = new ArrayList<>();
        attributes = new ArrayList<>();
    }

    @Override
    /**
     * If the element does not have any child elements it will return true if the element has a value otherwise will return false.
     * */
    public boolean hasValue(){
        return elements.isEmpty() && super.hasValue()? true: false;
    }

    @Override
    /**
     * Sets the value of the element if it does not have any child elements
     * */
    public void setValue(String value){
        if (elements.isEmpty())
            super.setValue(value);
    }

    public boolean hasElements(){
        return elements.isEmpty()? false: true;
    }

    public boolean hasElement(String name) {
        boolean result = false;
        for (XMLElement element: elements)
            if (element.getName().equals(name))
                result = true;

        return result;
    }

    public XMLElement getFirstElement(){
        return elements.get(0);
    }

    public XMLElement getFirstElement(String name) {
        XMLElement element = null;
        for (XMLElement e: elements)
            if (e.getName().equals(name))
                element = e;

        return element;
    }


    public ArrayList<XMLElement> getElements() {
        return elements;
    }

    public ArrayList<XMLElement> getElements(String name) {
        ArrayList elements = new ArrayList();
        for(XMLElement element: this.elements)
            if (element.getName().equals(name))
                elements.add(element);

        return elements;
    }

    public int getNumberOfElements() {
        return elements.size();
    }

    public int getNumberOfElements(String name) {
        int number = 0;
        for(XMLElement element: this.elements)
            if (element.getName().equals(name))
                number++;

        return number;
    }

    public void setElements(ArrayList<XMLElement> elements) {
        this.elements = elements;
    }

    public void addElement(XMLElement element){
        elements.add(element);
    }

    public void removeElements() {
        elements.clear();
    }

    public void removeElements(String name) {
        ArrayList<XMLElement> elements = new ArrayList<>();
        for (XMLElement e: this.elements)
            if (!e.getName().equals(name))
                elements.add(e);

        this.elements = elements;
    }

    public void removeFirstElement(XMLElement element) {
        elements.remove(element);
    }

    public boolean hasAttribute(String name){
        boolean has = false;
        for (XMLAttribute a: attributes)
            if (a.getName().equals(name)){
                has = true;
                break;
        }

        return has;
    }

    public boolean hasAttributes(){
        return attributes.isEmpty()? false: true;
    }

    public void addAttribute(XMLAttribute attribute){
        attributes.add(attribute);
    }

    public void removeAttributes(XMLAttribute attribute){
        ArrayList<XMLAttribute> attributes = new ArrayList<>();
        for (XMLAttribute a: this.attributes)
            if (a != attribute)
                attributes.add(attribute);

        this.attributes = attributes;
    }

    public void removeAttributes(){
        attributes.clear();
    }

    public XMLAttribute getFirstAttribute(){
        return attributes.get(0);
    }

    public ArrayList<XMLAttribute> getAttributes(){
        return attributes;
    }

    public int getNumberOfAttributes(){
        return attributes.size();
    }

    public void setAttributes(ArrayList<XMLAttribute> attributes){
        this.attributes = attributes;
    }
}