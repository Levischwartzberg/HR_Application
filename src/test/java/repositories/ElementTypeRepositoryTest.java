package repositories;

import com.astontech.hr.configuration.RepositoryConfiguration;
import com.astontech.hr.domain.Element;
import com.astontech.hr.domain.ElementType;
import com.astontech.hr.repositories.ElementRepository;
import com.astontech.hr.repositories.ElementTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class ElementTypeRepositoryTest {

    @Autowired
    private ElementTypeRepository elementTypeRepository;

    @Test
    public void findByType() {
        ElementType elementType = new ElementType("Derp");

        elementTypeRepository.save(elementType);

        assertEquals(elementType.getElementType(), elementTypeRepository.findByElementType("Derp").getElementType());
    }

    @Test
    public void findById() {
        ElementType elementType = new ElementType("Whep");

        elementTypeRepository.save(elementType);

        assertEquals(elementType.getElementType(), elementTypeRepository.findOne(1).getElementType());
    }

    @Test
    public void FindTop3byVersion() {
        List<ElementType> elTyList = new ArrayList<>();
        elTyList.add(new ElementType("Yelk"));
        elTyList.add(new ElementType("Wulp"));
        elTyList.add(new ElementType("Gurn"));
        elTyList.add(new ElementType("Ault"));

        elementTypeRepository.save(elTyList);

        List<ElementType> foundList = elementTypeRepository.findTop3ByVersionOrderByElementTypeAsc(0);
        for (ElementType elementType : foundList) {
            System.out.println(elementType.getElementType());
        }

        assertEquals(3, foundList.size());
    }

    @Test
    public void OrderAlphabetically() {
        List<ElementType> foundList = elementTypeRepository.findByOrderByElementTypeDesc();
        for (ElementType elementType : foundList) {
            System.out.println(elementType.getElementType());
        }

        assertEquals(6, foundList.size());
    }
}
