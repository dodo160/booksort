package com.booksort.xml.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class XmlValidatorImpl implements XmlValidator {

    @Value("${xsd.schema.path}")
    private String xsdPath;

    @Override
    public void validateXml(final String path) {
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            final SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            final Schema schema = factory.newSchema(new File(xsdPath));
            final Validator validator = schema.newValidator();
            validator.validate(new StreamSource(fileInputStream));
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}