package com.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate hireDate;

    @ManyToOne
    @JoinColumn(name = "store_id", foreignKey = @ForeignKey(name = "FK_EMPLOYEE_STORE"))
    private Store store;

    public Employee(String name, LocalDate hireDate) {
        this.name = name;
        this.hireDate = hireDate;
    }

    public void updateStore(Store store) {
        this.store = store;
    }

}
