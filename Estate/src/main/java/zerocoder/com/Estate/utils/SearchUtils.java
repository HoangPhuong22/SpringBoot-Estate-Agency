package zerocoder.com.Estate.utils;

import lombok.extern.slf4j.Slf4j;
import zerocoder.com.Estate.dto.search.AmenitySearchDTO;
import zerocoder.com.Estate.dto.search.CustomerSearchDTO;
import zerocoder.com.Estate.dto.search.EmployeeSearchDTO;
import zerocoder.com.Estate.dto.search.PropertySearchDTO;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
public class SearchUtils {
    public static AmenitySearchDTO amenitySearchDTO(Map<String, String> params) {
        AmenitySearchDTO searchDTO = new AmenitySearchDTO();
        Set<String> filedName = getFieldName(searchDTO);
        for(String key : params.keySet()) {
            if(filedName.contains(key)) {
                try {
                    Field field = searchDTO.getClass().getDeclaredField(key);
                    field.setAccessible(true);
                    if(field.getType().equals(Integer.class)) {
                        if(NumberUtils.isNumeric(params.get(key))) {
                            field.set(searchDTO, Integer.parseInt(params.get(key)));
                        }
                    } else {
                        field.set(searchDTO, params.get(key));
                    }
                } catch (Exception e) {
                    log.error("Error occurred while accessing field: {}", e.getMessage());
                }
            }
        }
        return searchDTO;
    }
    public static PropertySearchDTO propertySearchDTO(Map<String, String> params) {
        PropertySearchDTO searchDTO = new PropertySearchDTO();
        Set<String> filedName = getFieldName(searchDTO);
        for(String key : params.keySet()) {
            if(filedName.contains(key)) {
                try {
                    Field field = searchDTO.getClass().getDeclaredField(key);
                    field.setAccessible(true);
                    if(field.getType().equals(Integer.class)) {
                        if(NumberUtils.isNumeric(params.get(key))) {
                            field.set(searchDTO, Integer.parseInt(params.get(key)));
                        }
                    } else if(field.getType().equals(Double.class)) {
                        if(NumberUtils.isNumeric(params.get(key))) {
                            field.set(searchDTO, Double.parseDouble(params.get(key)));
                        }
                    } else if(field.getType().equals(Long.class)) {
                        if(NumberUtils.isNumeric(params.get(key))) {
                            field.set(searchDTO, Long.parseLong(params.get(key)));
                        }
                    } else {
                        field.set(searchDTO, params.get(key));
                    }
                } catch (Exception e) {
                    log.error("Error occurred while accessing field: {}", e.getMessage());
                }
            }
        }
        return searchDTO;
    }
    public static EmployeeSearchDTO employeeSearchDTO(Map<String, String> params) {
        EmployeeSearchDTO searchDTO = new EmployeeSearchDTO();
        Set<String> filedName = getFieldName(searchDTO);
        for(String key : params.keySet()) {
            if(filedName.contains(key)) {
                try {
                    Field field = searchDTO.getClass().getDeclaredField(key);
                    field.setAccessible(true);
                    if(field.getType().equals(Integer.class)) {
                        if(NumberUtils.isNumeric(params.get(key))) {
                            field.set(searchDTO, Integer.parseInt(params.get(key)));
                        }
                    } else {
                        field.set(searchDTO, params.get(key));
                    }
                } catch (Exception e) {
                    log.error("Error occurred while accessing field: {}", e.getMessage());
                }
            }
        }
        return searchDTO;
    }

    public static CustomerSearchDTO customerSearchDTO(Map<String, String> params) {
        CustomerSearchDTO searchDTO = new CustomerSearchDTO();
        Set<String> filedName = getFieldName(searchDTO);
        for(String key : params.keySet()) {
            if(filedName.contains(key)) {
                try {
                    Field field = searchDTO.getClass().getDeclaredField(key);
                    field.setAccessible(true);
                    if(field.getType().equals(Integer.class)) {
                        if(NumberUtils.isNumeric(params.get(key))) {
                            field.set(searchDTO, Integer.parseInt(params.get(key)));
                        }
                    } else {
                        field.set(searchDTO, params.get(key));
                    }
                } catch (Exception e) {
                    log.error("Error occurred while accessing field: {}", e.getMessage());
                }
            }
        }
        return searchDTO;
    }

    public static Set<String> getFieldName(Object obj) {
        Set<String> fieldNames = new HashSet<>();
        for(Field field : obj.getClass().getDeclaredFields()) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }
}
