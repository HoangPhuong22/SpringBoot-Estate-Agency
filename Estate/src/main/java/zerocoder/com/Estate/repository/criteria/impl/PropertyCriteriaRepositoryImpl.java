package zerocoder.com.Estate.repository.criteria.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import zerocoder.com.Estate.dto.response.PageResponse;
import zerocoder.com.Estate.dto.search.PropertySearchDTO;
import zerocoder.com.Estate.model.Contract;
import zerocoder.com.Estate.model.Customer;
import zerocoder.com.Estate.model.Property;
import zerocoder.com.Estate.repository.criteria.PropertyCriteriaRepository;
import zerocoder.com.Estate.utils.SearchUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class PropertyCriteriaRepositoryImpl implements PropertyCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PageResponse<?> findPropertiesAndSearch(PropertySearchDTO searchDTO) {
        if(searchDTO.getPageNo() == null) {
            searchDTO.setPageNo(1);
        }
        searchDTO.setPageSize(6);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Property> query = cb.createQuery(Property.class);
        Root<Property> root = query.from(Property.class);
        Join<Property, Contract> contractJoin = root.join("contracts", JoinType.LEFT);
        Join<Contract, Customer> customerJoin = contractJoin.join("customer", JoinType.LEFT);
        List<Predicate> predicates = new ArrayList<>();

        for(Field field : searchDTO.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object data = field.get(searchDTO);
                if(data == null) continue;
                switch (field.getName()) {
                    case "name" ->
                            predicates.add(cb.like(cb.lower(root.get("name")), "%" + data.toString().toLowerCase() + "%"));
                    case "type" ->
                            predicates.add(cb.like(cb.lower(root.get("type").as(String.class)), "%" + data.toString().toLowerCase() + "%"));
                    case "status" ->
                            predicates.add(cb.like(cb.lower(root.get("status").as(String.class)), "%" + data.toString().toLowerCase() + "%"));
                    case "city" ->
                            predicates.add(cb.like(cb.lower(root.get("city")), "%" + data.toString().toLowerCase() + "%"));
                    case "district" ->
                            predicates.add(cb.like(cb.lower(root.get("district")), "%" + data.toString().toLowerCase() + "%"));
                    case "ward" ->
                            predicates.add(cb.like(cb.lower(root.get("ward")), "%" + data.toString().toLowerCase() + "%"));
                    case "address" ->
                            predicates.add(cb.like(cb.lower(root.get("address")), "%" + data.toString().toLowerCase() + "%"));
                    case "bedroom" -> predicates.add(cb.equal(root.get("bedRooms"), data));
                    case "bathroom" -> predicates.add(cb.equal(root.get("bathRooms"), data));
                    case "floor" -> predicates.add(cb.equal(root.get("floor"), data));
                    case "area" -> predicates.add(cb.equal(root.get("area"), data));
                    case "builtYear" -> predicates.add(cb.equal(root.get("builtYear"), data));
                    case "salePrice" -> predicates.add(cb.greaterThan(root.get("salePrice"), Long.parseLong((String) data)));
                    case "rentPrice" -> predicates.add(cb.greaterThan(root.get("rentPrice"), Long.parseLong((String) data)));
                    case "customerId" -> predicates.add(cb.equal(customerJoin.get("id"), data));
                    case "isDeleted" -> predicates.add(cb.equal(root.get("isDeleted"), false));
                }
            } catch (Exception e) {
                log.error("Error occurred while accessing field: {}", e.getMessage());
            }
        }

        query.select(root)
                .where(predicates.toArray(new Predicate[0]))
                .distinct(true);

        if (searchDTO.getSort() != null && !searchDTO.getSort().isEmpty()) {
            String[] sortParams = searchDTO.getSort().split("\\:");
            String sortField = sortParams[0];
            String sortDirection = sortParams.length > 1 ? sortParams[1] : "asc";
            if(SearchUtils.getFieldName(Property.class).contains(sortField)) {
                if ("desc".equalsIgnoreCase(sortDirection)) {
                    query.orderBy(cb.desc(root.get(sortField)));
                } else {
                    query.orderBy(cb.asc(root.get(sortField)));
                }
            }
        } else {
            query.orderBy(cb.desc(root.get("updatedAt")),
                    cb.desc(root.get("createdAt")),
                    cb.asc(root.get("name")));
        }

        Integer pageNo = searchDTO.getPageNo();
        Integer pageSize = searchDTO.getPageSize();

        int totalRecords = entityManager.createQuery(query).getResultList().size();
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

        List<Property> properties = entityManager.createQuery(query)
                .setFirstResult((pageNo - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();

        return PageResponse.builder()
                .content(properties)
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPages(totalPages)
                .totalElements(totalRecords)
                .build();
    }
}
