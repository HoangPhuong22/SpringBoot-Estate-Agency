package zerocoder.com.Estate.repository.criteria.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import zerocoder.com.Estate.dto.response.PageResponse;
import zerocoder.com.Estate.dto.search.CustomerSearchDTO;
import zerocoder.com.Estate.model.Customer;
import zerocoder.com.Estate.repository.criteria.CustomerCriteriaRepository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class CustomerCriteriaRepositoryImpl implements CustomerCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PageResponse<?> search(CustomerSearchDTO customerSearchDTO) {
        if(customerSearchDTO.getPageNo() == null) {
            customerSearchDTO.setPageNo(1);
        }
        customerSearchDTO.setPageSize(1);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
        Root<Customer> root = query.from(Customer.class);
        List<Predicate> predicates = new ArrayList<>();
        for(Field field : customerSearchDTO.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object data = field.get(customerSearchDTO);
                if(data == null) continue;
                switch (field.getName()) {
                    case "fullName" -> predicates.add(cb.like(cb.lower(root.get("fullName")), "%" + data.toString().toLowerCase() + "%"));
                    case "email" -> predicates.add(cb.like(cb.lower(root.get("email")), "%" + data.toString().toLowerCase() + "%"));
                    case "phone" -> predicates.add(cb.like(cb.lower(root.get("phone")), "%" + data.toString().toLowerCase() + "%"));
                    case "idNumber" -> predicates.add(cb.like(cb.lower(root.get("idNumber")), "%" + data.toString().toLowerCase() + "%"));
                    case "address" -> predicates.add(cb.like(cb.lower(root.get("address")), "%" + data.toString().toLowerCase() + "%"));
                }
            } catch (Exception e) {
                log.error("Error occurred while accessing field: {}", e.getMessage());
            }
        }
        query.select(root).where(predicates.toArray(new Predicate[0]));
        Integer pageNo = customerSearchDTO.getPageNo();
        Integer pageSize = customerSearchDTO.getPageSize();

        int totalRecords = entityManager.createQuery(query).getResultList().size();
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

        List<Customer> customers = entityManager.createQuery(query)
                .setFirstResult((pageNo - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();

        return PageResponse.builder()
                .content(customers)
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPages(totalPages)
                .totalElements(totalRecords)
                .build();
    }
}
