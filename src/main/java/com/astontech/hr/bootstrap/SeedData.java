package com.astontech.hr.bootstrap;

import com.astontech.hr.domain.Element;
import com.astontech.hr.domain.ElementType;
import com.astontech.hr.services.ElementService;
import com.astontech.hr.services.ElementTypeService;
import com.astontech.hr.services.impl.ElementTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeedData implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ElementService elementService;

    @Autowired
    private ElementTypeService elementTypeService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        generateElementAndElementTypes();
    }

    private void generateElementAndElementTypes() {
        ElementType laptopType = new ElementType("Laptop");
        List<Element> laptopList = new ArrayList<>();
        laptopList.add(new Element("Acer"));
        laptopList.add(new Element("Dell"));
        laptopList.add(new Element("Samsung"));
        laptopList.add(new Element("Apple"));
        laptopList.add(new Element("Asus"));

        laptopType.setElementList(laptopList);

        elementTypeService.saveElementType(laptopType);

        ElementType phoneType = new ElementType("Phone");
        List<Element> phoneList = new ArrayList<>();
        phoneList.add(new Element("Work"));
        phoneList.add(new Element("Home"));
        phoneList.add(new Element("Cell"));

        phoneType.setElementList((phoneList));

        elementTypeService.saveElementType(phoneType);
    }
}
