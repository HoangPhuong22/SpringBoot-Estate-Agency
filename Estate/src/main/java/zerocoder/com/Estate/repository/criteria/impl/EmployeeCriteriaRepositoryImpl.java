package zerocoder.com.Estate.repository.criteria.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import zerocoder.com.Estate.dto.response.PageResponse;
import zerocoder.com.Estate.dto.search.EmployeeSearchDTO;
import zerocoder.com.Estate.model.Amenity;
import zerocoder.com.Estate.model.Employee;
import zerocoder.com.Estate.repository.criteria.EmployeeCriteriaRepository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeCriteriaRepositoryImpl implements EmployeeCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PageResponse<?> search(EmployeeSearchDTO searchDTO) {
        if(searchDTO.getPageNo() == null) {
            searchDTO.setPageNo(1);
        }
        searchDTO.setPageSize(1);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<Employee> root = query.from(Employee.class);
        List<Predicate> predicates = new ArrayList<>();

        for(Field field : searchDTO.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object data = field.get(searchDTO);
                if(data == null) continue;

                switch (field.getName()) {
                    case "fullName" ->
                            predicates.add(cb.like(cb.lower(root.get("fullName")), "%" + data.toString().toLowerCase() + "%"));
                    case "email" ->
                            predicates.add(cb.like(cb.lower(root.get("email")), "%" + data.toString().toLowerCase() + "%"));
                    case "phone" ->
                            predicates.add(cb.like(cb.lower(root.get("phone")), "%" + data.toString().toLowerCase() + "%"));
                    case "idNumber" ->
                            predicates.add(cb.like(cb.lower(root.get("idNumber")), "%" + data.toString().toLowerCase() + "%"));
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        query.select(root)
                .where(predicates.toArray(new Predicate[0]))
                .orderBy(
                        cb.desc(root.get("updatedAt")),
                        cb.desc(root.get("createdAt")),
                        cb.asc(root.get("fullName"))
                );
        Integer pageNo = searchDTO.getPageNo();
        Integer pageSize = searchDTO.getPageSize();

        int totalRecords = entityManager.createQuery(query).getResultList().size();
        int totalPages = (int)Math.ceil((double)totalRecords / pageSize);
        List<Employee>employees = entityManager.createQuery(query)
                .setFirstResult((pageNo - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        return PageResponse.builder()
                .content(employees)
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPages(totalPages)
                .totalElements(totalRecords)
                .build();
    }
}
