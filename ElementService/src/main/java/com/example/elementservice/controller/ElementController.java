package com.example.elementservice.controller;

import com.example.elementservice.domain.Element;
import com.example.elementservice.domain.Elements;
import com.example.elementservice.service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/elements")
public class ElementController {
    @Autowired
    private ElementService elementService;

    @GetMapping
    public Elements findAll() {
        return elementService.findAll();
    }

    @GetMapping("/{type}")
    public ResponseEntity<?> findByType(@PathVariable String type) {
        Optional<Element> element = elementService.findByType(type);
        if (!element.isPresent()) {
            return new ResponseEntity<String>("No element found with type: " + type, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(element.get());
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Element element) {
        Optional<Element> element1 = elementService.findByType(element.getType());
        if (element1.isPresent()) {
            return new ResponseEntity<String>("Element with type: " + element.getType() + " already exists", HttpStatus.CONFLICT);
        } else {
            return ResponseEntity.ok(elementService.save(element));
        }
    }

    @DeleteMapping("/{type}")
    public ResponseEntity<?> delete(@PathVariable String type) {
        Optional<Element> element = elementService.findByType(type);
        if (!element.isPresent()) {
            return new ResponseEntity<String>("No element found with type: " + type, HttpStatus.NOT_FOUND);
        }
        elementService.delete(element.get());
         return new ResponseEntity<String>("Element with type: " + type + " deleted", HttpStatus.OK);
    }

    @PutMapping("/update/{type}")
    public ResponseEntity<?> update(@RequestBody Element element , @PathVariable String type) {
        Optional<Element> element1 = elementService.findByType(element.getType());
        if (!element1.isPresent()) {
            return new ResponseEntity<String>("No element found with type: " + element.getType(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(elementService.update(element , type));
    }


}
