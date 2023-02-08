package element.elementservice.service;

import element.elementservice.domain.Element;

import java.util.List;

public interface IElementService {
    public Element addElement(Element element);
    public boolean deleteElementById(String elementId);
    public Element updateElement(String elementId,Element element);
    public List<Element> getAllElements();
    public List<Element> getElementsByType(String type);

}
