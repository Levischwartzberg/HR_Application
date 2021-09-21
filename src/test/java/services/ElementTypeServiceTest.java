package services;

import com.astontech.hr.Application;
import com.astontech.hr.domain.ElementType;
import com.astontech.hr.services.ElementTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class ElementTypeServiceTest {

    @Autowired
    private ElementTypeService elementTypeService;


    @Test
    public void findByType() {
        ElementType elementType = new ElementType("Derp");

        elementTypeService.saveElementType(elementType);

        assertEquals(elementType.getElementType(), elementTypeService.findByElementType("Derp").getElementType());
    }

    @Test
    public void findById() {
        ElementType elementType = new ElementType("Whep");

        elementTypeService.saveElementType(elementType);

        assertEquals(elementType.getElementType(), elementTypeService.findOne(1).getElementType());
    }

    @Test
    public void FindTop3byVersion() {
        List<ElementType> elTyList = new ArrayList<>();
        elTyList.add(new ElementType("Yelk"));
        elTyList.add(new ElementType("Wulp"));
        elTyList.add(new ElementType("Gurn"));
        elTyList.add(new ElementType("Ault"));

        elementTypeService.saveElementTypeList(elTyList);

        List<ElementType> foundList = elementTypeService.findTop3ByVersionOrderByElementTypeAsc(0);
        for (ElementType elementType : foundList) {
            System.out.println(elementType.getElementType());
        }

        assertEquals(3, foundList.size());
    }

    @Test
    public void OrderAlphabetically() {
        List<ElementType> foundList = elementTypeService.findByOrderByElementTypeDesc();
        for (ElementType elementType : foundList) {
            System.out.println(elementType.getElementType());
        }

        assertEquals(6, foundList.size());
    }
}
