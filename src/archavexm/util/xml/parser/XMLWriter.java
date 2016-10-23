package archavexm.util.xml.parser;

import archavexm.util.xml.parser.document.XMLAttribute;
import archavexm.util.xml.parser.document.XMLDocument;
import archavexm.util.xml.parser.document.XMLElement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Responsible for writing XMLDocument into an xml file on disk.
 * */
public class XMLWriter {
    /**
     * Will write the entire XMLDocument into the fle specified by the XMLDocument. This will not check if the file provided exists.
     *
     * @param document the XMLDocument instance
     * */
    public static void save(XMLDocument document) throws IOException, IncorrectXMLException{
        StringBuilder content = new StringBuilder();

        // Checks if the document has declaration and if it has it will add it to the content
        if (document.hasDeclaration())
            content.append(document.getDeclaration().getToken());

        XMLElement root = document.getRoot();
        if (root == null)
            throw new IncorrectXMLException("The xml document you are trying to parse has no root element which means it is not valid");
        else {
            if (root.getName().trim().isEmpty())
                throw new IncorrectXMLException("The xml root element must be named");
            else {
                // Element start
                content.append(XMLTokens.ELEMENT_BEGINNING_START);
                content.append(root.getName().trim());
                // Takes care of the attributes
                if (root.hasAttributes()){
                    content.append(XMLTokens.SPACE);
                    for (XMLAttribute attribute: root.getAttributes())
                        content.append(getAttribute(attribute.getName(), attribute.getValue()));
                }

                // Checks if the element has elements
                // If does not it will add the element end tag for the one line element
                if (root.hasElements()){
                    content.append(XMLTokens.ELEMENT_END);
                    if (root.hasValue())
                        content.append(root.getValue());
                }
                else {
                    content.append(XMLTokens.SPACE);
                    content.append(XMLTokens.ELEMENT_ONE_LINE_END);
                }

                // Adds the end of the root element to the content if it has elements
                if (root.hasElements()){
                    for (XMLElement element: root.getElements())
                        content.append(getElement(element));

                content.append(getElementEnding(root.getName()));
                }
            }
        }

        // Writes the document to the file
        if (new File(document.getFilePath()).exists()){
            try (FileWriter writer = new FileWriter(document.getFilePath())){
                writer.write(content.toString());
            }
        } else
            throw new IOException("The file provided in the document object does not exist.");
    }

    // This method will loop through every element and attribute in the xml document
    // It has the same behaviour as the save() method
    private static String getElement(XMLElement element) throws IncorrectXMLException{
        StringBuilder content = new StringBuilder();
        if (element.getName().trim().isEmpty())
            throw new IncorrectXMLException("One of the elements in the xml document does not have a name and must be named");
        else {
            content.append(XMLTokens.ELEMENT_BEGINNING_START);
            content.append(element.getName());
            if (element.hasAttributes()){
                content.append(XMLTokens.SPACE);
                for (XMLAttribute attribute: element.getAttributes())
                    content.append(getAttribute(attribute.getName(), attribute.getValue()));
            }

            if (element.hasElements() || element.hasValue())
                content.append(XMLTokens.ELEMENT_END);
            else {
                if (element.hasValue()){
                    content.append(element.getValue());
                }
                content.append(XMLTokens.SPACE);
                content.append(XMLTokens.ELEMENT_ONE_LINE_END);
            }

            // Same functionality as the save() method
            if (element.hasElements())
                for (XMLElement child: element.getElements())
                    content.append(getElement(child));

            // Adds the end tag
            if (element.hasElements() || element.hasValue())
                content.append(getElementEnding(element.getName()));
        }

        return content.toString();
    }

    // Takes care of placing attributes
    private static String getAttribute(String name, String value){
        StringBuilder content = new StringBuilder();
        content.append(name);
        content.append(XMLTokens.ATTRIBUTE_BEGINNING);
        content.append(value);
        content.append(XMLTokens.ATTRIBUTE_END);
        return content.toString();
    }

    // Takes care of ending the tag
    private static String getElementEnding(String name){
        StringBuilder content = new StringBuilder();
        content.append(XMLTokens.ELEMENT_ENDING_START);
        content.append(name);
        content.append(XMLTokens.ELEMENT_END);
        return content.toString();
    }
}