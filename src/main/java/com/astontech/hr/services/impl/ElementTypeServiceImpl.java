package com.astontech.hr.services.impl;

import com.astontech.hr.domain.ElementType;
import com.astontech.hr.repositories.ElementTypeRepository;
import com.astontech.hr.services.ElementTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementTypeServiceImpl implements ElementTypeService {

    @Autowired
    private ElementTypeRepository elementTypeRepository;


    @Override
    public Iterable<ElementType> listAllElementTypes() {
        return elementTypeRepository.findAll();
    }

    @Override
    public ElementType getElementTypeById(Integer id) {
        return elementTypeRepository.findOne(id);
    }

    @Override
    public ElementType saveElementType(ElementType elementType) {
        return elementTypeRepository.save(elementType);
    }

    @Override
    public Iterable<ElementType> saveElementTypeList(Iterable<ElementType> elementTypeIterable) {
        return elementTypeRepository.save(elementTypeIterable);
    }

    @Override
    public void deleteElementType(Integer id) {
        elementTypeRepository.delete(id);
    }

    @Override
    public ElementType findByElementTypeName(String elementTypeName) {
        return elementTypeRepository.findByElementTypeName(elementTypeName);
    }

    @Override
    public ElementType findOne(Integer Id) {
        return elementTypeRepository.findOne(Id);
    }

    @Override
    public List<ElementType> findTop3ByVersionOrderByElementTypeAsc(Integer version) {
        return elementTypeRepository.findTop3ByVersionOrderByElementTypeNameAsc(version);
    }

    @Override
    public List<ElementType> findByOrderByElementTypeDesc() {
        return elementTypeRepository.findByOrderByElementTypeNameDesc();
    }
}
