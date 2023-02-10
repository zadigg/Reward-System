package com.example.elementservice.service;

import com.example.elementservice.domain.Element;
import com.example.elementservice.domain.Elements;
import com.example.elementservice.repository.ElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElementService {
    @Autowired
    private ElementRepository elementRepository;

    public Elements findAll() {
        Elements elements = new Elements();
        elements.setElements(elementRepository.findAll());
        return elements;
    }

    public Optional<Element> findById(String id) {
        return elementRepository.findById(id);
    }

    public Optional<Element> findByType(String type) {
        return elementRepository.findByType(type);
    }

    public Element save(Element element) {
        return elementRepository.save(element);
    }

    public void delete(Element element) {
        elementRepository.delete(element);
    }

    public Element update(Element element , String type) {
        Optional<Element> element1 = elementRepository.findByType(type);
        if (element1.isPresent()) {
            element1.get().setType(element.getType());
            element1.get().setPrice(element.getPrice());
            return elementRepository.save(element1.get());
        }
        return null;
    }
}