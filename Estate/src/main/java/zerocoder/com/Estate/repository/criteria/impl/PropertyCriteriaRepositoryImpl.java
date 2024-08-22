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
import zerocoder.com.Estate.dto.search.PropertySearchDTO;
import zerocoder.com.Estate.model.Property;
import zerocoder.com.Estate.repository.criteria.PropertyCriteriaRepository;

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
        searchDTO.setPageSize(1);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Property> query = cb.createQuery(Property.class);
        Root<Property> root = query.from(Property.class);
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
                }
            } catch (Exception e) {
                log.error("Error occurred while accessing field: {}", e.getMessage());
            }
        }
        query.select(root)
                .where(predicates.toArray(new Predicate[0]))
                .orderBy(cb.desc(
                        root.get("updatedAt")),
                        cb.desc(root.get("createdAt")),
                        cb.asc(root.get("name"))
                );
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
