package com.booksort.xml.marshaller;

public interface XmlMarshaller {

    Object fromXmlFile(final Class entityClass, final String path);
}
