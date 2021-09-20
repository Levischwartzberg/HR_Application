package com.astontech.hr.domain;

import javax.persistence.*;

@Entity
public class Element {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ElementId")
    private Integer Id;

    @Version
    private Integer version;

    private String elementName;

    //region Constructors
    public Element() {

    }

    public Element(String elementName) {
        this.elementName = elementName;
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

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }
    //endregion
}