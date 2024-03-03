package com.booksort.xml.marshaller;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;

@Component
public class XmlMarshallerImpl implements XmlMarshaller{

    public Object fromXmlFile(final Class entityClass, final String path) {
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            final JAXBContext jaxbContext = JAXBContext.newInstance(entityClass);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return entityClass.cast(unmarshaller.unmarshal(fileInputStream));
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
