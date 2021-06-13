package com.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Store {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();

    public Store(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void addProduct(Product product) {
        this.products.add(product);
        product.updateStore(this);
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.updateStore(this);
    }

}
