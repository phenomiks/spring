package ru.geekbrains.spring.mvc.contollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.spring.mvc.ProductRepository;

@RestController
@RequestMapping("/xml")
public class XmlController {
    private ProductRepository productRepository;

    @Autowired
    public XmlController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_XML_VALUE)
    public String getXmlProduct() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.writeValueAsString(productRepository.getAllProducts());
    }
}
