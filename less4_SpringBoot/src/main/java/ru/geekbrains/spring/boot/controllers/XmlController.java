package ru.geekbrains.spring.boot.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.spring.boot.services.ProductService;

@RestController
@RequestMapping(value = "/xml")
public class XmlController {
    private ProductService productService;

    @Autowired
    public XmlController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_XML_VALUE)
    public String showXmlProductRepository() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.writeValueAsString(productService.findAllProducts());
    }
}
