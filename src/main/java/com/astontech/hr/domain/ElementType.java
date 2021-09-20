package com.astontech.hr.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class ElementType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ElementTypeId")
    private Integer Id;

    @Version
    private Integer version;

    private String elementType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Element> elementList;

    //region Constructors
    public ElementType() {

    }
    public ElementType(String elementType) {
        this.elementType = elementType;
    }

    public ElementType(String elementType, List<Element> elementList) {
        this.elementType = elementType;
        this.elementList = elementList;
    }
    //endregion

    //region Getters and Setters
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public List<Element> getElementList() {
        return elementList;
    }

    public void setElementList(List<Element> elementList) {
        this.elementList = elementList;
    }
    //endregion
}