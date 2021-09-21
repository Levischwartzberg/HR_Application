package com.astontech.hr.services;

import com.astontech.hr.domain.Element;

import java.util.List;

public interface ElementService {

    Iterable<Element> listAllElements();

    Element getElementById(Integer id);

    Element saveElement(Element element);

    Iterable<Element> saveElementList(Iterable<Element> elementIterable);

    void deleteElement(Integer id);


    //custom
    Element findByElementName(String elementName);

    List<Element> findAllByElementName(String elementName);

    List<Element> findElementsByElementNameContains(String subStr);

    Element findFirstByOrderByElementNameAsc();
}
