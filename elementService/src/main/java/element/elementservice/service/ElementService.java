package element.elementservice.service;

import element.elementservice.domain.Element;
import element.elementservice.repository.ElementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElementService implements IElementService{
    @Autowired
    private ElementRepo elementRepo;
    @Override
    public Element addElement(Element element) {
        if (element.getPrice()<10|| element.getPrice()>50){
            throw new IllegalArgumentException("price should be between 10 and 50");
        }
        return elementRepo.save(element);
    }

    @Override
    public boolean deleteElementById(String elementId) {
        try{
            elementRepo.deleteById(elementId);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public Element updateElement(String elementId, Element element) {
        Optional<Element> oldElement= elementRepo.findById(elementId);
        if (oldElement.isPresent()){
            Element updatedElement= elementRepo.save(element);
            return updatedElement;
        }
       else{
            throw new IllegalArgumentException("id is not found in the database");
        }
    }

    @Override
    public List<Element> getAllElements() {
        return elementRepo.findAll();
    }

    @Override
    public List<Element> getElementsByType(String type) {
        return elementRepo.findByType(type);
    }
}
