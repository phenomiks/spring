package ru.geekbrains.spring.mvc.contollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.spring.mvc.ProductRepository;

@RestController
@RequestMapping("/json")
public class JsonController {
    private ProductRepository productRepository;

    @Autowired
    public JsonController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getJsonProduct() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(productRepository.getAllProducts());
    }
}
