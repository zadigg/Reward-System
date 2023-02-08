package element.elementservice.controller;

import element.elementservice.domain.Element;
import element.elementservice.service.IElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataLocationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elements")
public class ElementController {
    @Autowired
    private IElementService iElementService;

    @PostMapping("/add")
    public ResponseEntity<Element> createElement(@RequestBody Element element){
        Element createElement = iElementService.addElement(element);
        return ResponseEntity.ok(createElement);
    }
    @GetMapping
    public ResponseEntity<?> getAllElement(){
        List<Element> elements= iElementService.getAllElements();
        if (elements.size()==0){
            return new ResponseEntity<String>("There is no data registered on the database",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Element>>(elements, HttpStatus.OK);
    }
    @GetMapping("/type/{type}")
    public ResponseEntity<?> getAllElementBYType(@PathVariable String type){
        List<Element> elements= iElementService.getElementsByType(type);
        if (elements.size()==0){
            return new ResponseEntity<String>(""+type+" is not found in the database", HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<List<Element>>(elements, HttpStatus.OK);
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeElement(@PathVariable String id) {
        try {
            iElementService.deleteElementById(id);
            //return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            return ResponseEntity.ok().body("deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PutMapping("/{elementId}")
    public ResponseEntity<Element> updateElementById(@PathVariable String elementId, @RequestBody Element element){
        Element elements = iElementService.updateElement(elementId,element);

        return ResponseEntity.ok(elements);
    }
}
