package archavexm.util.xml.parser.document;

import java.util.LinkedList;

/**
 * This class encapsulates an element with all of it's child elements, attributes and value. Should be used within the XMLDocument.
 * If you try to add value to the element and it has child elements it will not add the value.
 * */
public class XMLElement extends XMLNode {
    private LinkedList<XMLElement> elements = new LinkedList<>();
    private LinkedList<XMLAttribute> attributes = new LinkedList<>();

    public XMLElement(String name){
        this(name, new LinkedList<>(), new LinkedList<>());
    }

    public XMLElement(String name, LinkedList<XMLAttribute> attributes){
        this(name, new LinkedList<>(), attributes);
    }

    public XMLElement(String name, String value){
        super(name, value);
    }

    public XMLElement(String name, String value, LinkedList<XMLAttribute> attributes){
        super(name, value);
        this.attributes = attributes;
    }

    public XMLElement(String name, LinkedList<XMLElement> elements, LinkedList<XMLAttribute> attributes){
        super(name, null);
        this.elements = elements;
        this.attributes = attributes;
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
        return elements.getFirst();
    }

    public XMLElement getFirstElement(String name) {
        XMLElement element = null;
        for (XMLElement e: elements)
            if (e.getName().equals(name))
                element = e;

        return element;
    }

    public XMLElement getLastElement(){
        return elements.getLast();
    }

    public LinkedList<XMLElement> getElements() {
        return elements;
    }

    public LinkedList<XMLElement> getElements(String name) {
        LinkedList elements = new LinkedList();
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

    public void setElements(LinkedList<XMLElement> elements) {
        this.elements = elements;
    }

    public void addElement(XMLElement element){
        elements.add(element);
    }

    public void removeElements() {
        elements.clear();
    }

    public void removeElements(String name) {
        LinkedList<XMLElement> elements = new LinkedList<>();
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
        LinkedList<XMLAttribute> attributes = new LinkedList<>();
        for (XMLAttribute a: this.attributes)
            if (a != attribute)
                attributes.add(attribute);

        this.attributes = attributes;
    }

    public void removeAttributes(){
        attributes.clear();
    }

    public XMLAttribute getFirstAttribute(){
        return attributes.element();
    }

    public LinkedList<XMLAttribute> getAttributes(){
        return attributes;
    }

    public int getNumberOfAttributes(){
        return attributes.size();
    }

    public void setAttributes(LinkedList<XMLAttribute> attributes){
        this.attributes = attributes;
    }
}
