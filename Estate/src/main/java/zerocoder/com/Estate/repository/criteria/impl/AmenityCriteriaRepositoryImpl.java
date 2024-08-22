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
import zerocoder.com.Estate.dto.search.AmenitySearchDTO;
import zerocoder.com.Estate.model.Amenity;
import zerocoder.com.Estate.repository.criteria.AmenityCriteriaRepository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class AmenityCriteriaRepositoryImpl implements AmenityCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PageResponse<?> findAmenitiesAndSearch(AmenitySearchDTO searchDTO) {
        searchDTO.setPageSize(5);
        if(searchDTO.getPageNo() == null) {
            searchDTO.setPageNo(1);
        }
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Amenity> query = cb.createQuery(Amenity.class);
        Root<Amenity> root = query.from(Amenity.class);
        List<Predicate> predicates = new ArrayList<>();
        for(Field field: searchDTO.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object data = field.get(searchDTO);
                if(data == null) continue;
                if(field.getName().equals("name")) {
                    predicates.add(cb.like(cb.lower(root.get("name")), "%" + data.toString().toLowerCase() + "%"));
                } else if(field.getName().equals("type")) {
                    predicates.add(cb.like(cb.lower(root.get("type").as(String.class)), "%" + data.toString().toLowerCase() + "%"));
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
        int totalPages = (int)Math.ceil((double)totalRecords / pageSize);

        List<Amenity> amenities = entityManager.createQuery(query)
                .setFirstResult((pageNo - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();

        return PageResponse.builder()
                .content(amenities)
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPages(totalPages)
                .totalElements(totalRecords)
                .build();
    }
}
