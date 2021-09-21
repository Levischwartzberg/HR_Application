package com.astontech.hr.repositories;

import com.astontech.hr.domain.ElementType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ElementTypeRepository extends CrudRepository<ElementType, Integer> {

    ElementType findByElementType(String elementType);

    ElementType findOne(Integer Id);

    List<ElementType> findTop3ByVersionOrderByElementTypeAsc(Integer version);

    List<ElementType> findByOrderByElementTypeDesc();
}
