package com.example.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StoreService {

    private StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    // Lazy Loading 발동을 위해 get필드 사용
    @Transactional(readOnly = true)
    public long find() {
        System.out.println("========================");
        List<Store> stores = storeRepository.findAllByFetchJoin();

        long totalPrice = 0;
        for(Store store:stores) {
            List<Product> products = store.getProducts();
            for(Product product:products) {
                totalPrice += product.getPrice();
            }

            List<Employee> employees = store.getEmployees();
            for(Employee employee:employees) {
                String name = employee.getName();
                System.out.println("employee: " + name);
            }
        }

        System.out.println("========================");
        return totalPrice;
    }

}
