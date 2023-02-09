package com.example.elementservice.service;

import com.example.elementservice.domain.Element;
import com.example.elementservice.repository.ElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElementService {
    @Autowired
    private ElementRepository elementRepository;

    public List<Element> findAll() {

        return elementRepository.findAll();
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
}