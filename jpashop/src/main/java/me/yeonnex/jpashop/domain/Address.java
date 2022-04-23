package me.yeonnex.jpashop.domain;

import javax.persistence.Embeddable;

@Embeddable // 내장타입
public class Address {
    private String city;
    private String street;
    private String zipcode;

    // JPA 스펙상, 리플렉션이나 프록시를 위한 기본 생성자가 필요!
    protected Address(){
    }

    public Address(String city, String street, String zipcode){
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
