package services;

import com.astontech.hr.Application;
import com.astontech.hr.domain.Element;
import com.astontech.hr.services.ElementService;
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
public class ElementServiceTest {

    @Autowired
    private ElementService elementService;

    @Test
    public void testSaveElement() {
        //setup element
        Element element = new Element();
        element.setElementName("Phone");

        //save element, verify it has id
        assertNull(element.getId());
        elementService.saveElement(element);
        assertNotNull(element.getId());

        //fetch
        Element fetchedElement = elementService.getElementById(element.getId());
        assertNotNull(fetchedElement);
        assertEquals(element.getId(), fetchedElement.getId());

        //update
        fetchedElement.setElementName("Email");
        elementService.saveElement(fetchedElement);

        Element updatedElement = elementService.getElementById(fetchedElement.getId());
        assertEquals(updatedElement.getElementName(), "Email");
    }

    @Test
    public void testSaveElementList() {
        List<Element> elementList = new ArrayList<>();

        elementList.add(new Element("Phone"));
        elementList.add(new Element("Email"));
        elementList.add(new Element("Laptop"));
        elementList.add(new Element("PayRate"));

        elementService.saveElementList(elementList);

        Iterable<Element> fetchedElementList = elementService.listAllElements();

        int count = 0;
        for(Element element : fetchedElementList) {
            count = count + 1;
        }

        assertEquals(13, count);
    }

    @Test
    public void testFindByElementName() {
        Element element = new Element("FindTest");
        elementService.saveElement(element);

        Element element1 = elementService.findByElementName("FindTest");

        assertEquals(element.getId(), element1.getId());
    }

    @Test
    public void testFindAllByName() {
        Element element1 = new Element("FindListTest");
        elementService.saveElement(element1);
        Element element2 = new Element("FindListTest");
        elementService.saveElement(element2);
        Element element3 = new Element("FindListTest");
        elementService.saveElement(element3);

        List<Element> foundElList = elementService.findAllByElementName("FindListTest");

        assertEquals(3, foundElList.size());
    }

    @Test
    public void findSubStr() {
        List<Element> elementList = new ArrayList<>();
        elementList.add(new Element("Phone"));
        elementList.add(new Element("Email"));
        elementList.add(new Element("Laptop"));
        elementList.add(new Element("PayRate"));
        elementService.saveElementList(elementList);
        List<Element> foundElList = elementService.findElementsByElementNameContains("a");

        System.out.println(foundElList.size());
        for (Element element : foundElList) {
            System.out.println(element.getElementName() + " " + element.getId());
        }
    }

    @Test
    public void findAndOrder() {
        Element element = elementService.findFirstByOrderByElementNameAsc();

        System.out.println(element.getElementName());
    }
}
