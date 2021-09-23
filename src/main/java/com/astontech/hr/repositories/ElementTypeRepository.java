package com.astontech.hr.repositories;

import com.astontech.hr.domain.ElementType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.ldap.repository.Query;

import java.util.List;

public interface ElementTypeRepository extends CrudRepository<ElementType, Integer> {

    ElementType findByElementTypeName(String elementTypeName);

    ElementType findOne(Integer Id);

    List<ElementType> findTop3ByVersionOrderByElementTypeNameAsc(Integer version);

    List<ElementType> findByOrderByElementTypeNameDesc();
}
