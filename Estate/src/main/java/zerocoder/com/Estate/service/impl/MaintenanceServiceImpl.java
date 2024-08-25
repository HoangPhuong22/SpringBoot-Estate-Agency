package zerocoder.com.Estate.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerocoder.com.Estate.dto.request.MaintenanceRequest;
import zerocoder.com.Estate.dto.response.MaintenanceResponse;
import zerocoder.com.Estate.mapper.MaintenanceMapper;
import zerocoder.com.Estate.model.Account;
import zerocoder.com.Estate.model.Maintenance;
import zerocoder.com.Estate.model.Property;
import zerocoder.com.Estate.repository.AccountRepository;
import zerocoder.com.Estate.repository.MaintenanceRepository;
import zerocoder.com.Estate.repository.PropertyRepository;
import zerocoder.com.Estate.service.MaintenanceService;
import zerocoder.com.Estate.utils.SecurityUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final MaintenanceMapper maintenanceMapper;
    private final SecurityUtils securityUtils;
    private final AccountRepository accountRepository;
    private final PropertyRepository propertyRepository;

    @Override
    public Long saveOrUpdate(MaintenanceRequest maintenanceRequest) {
        if (maintenanceRequest.getId() == null) {
            return save(maintenanceRequest);
        } else {
            return update(maintenanceRequest);
        }
    }

    private Long save(MaintenanceRequest maintenanceRequest) {
        Long propertyId = maintenanceRequest.getPropertyId();
        Long accountId = securityUtils.getPrincipal().getId();
        Maintenance maintenance = maintenanceMapper.toEntity(maintenanceRequest);
        Property property = propertyRepository.findById(propertyId).orElseThrow();
        Account account = accountRepository.findById(accountId).orElseThrow();
        maintenance.setProperty(property);
        maintenance.setAccount(account);
        return maintenanceRepository.save(maintenance).getId();
    }

    private Long update(MaintenanceRequest maintenanceRequest) {
        Long id = maintenanceRequest.getId();
        Maintenance maintenance = maintenanceRepository.findById(id).orElseThrow();
        maintenanceMapper.updateEntity(maintenance, maintenanceRequest);
        return maintenanceRepository.save(maintenance).getId();
    }

    @Override
    public Long delete(Long id) {
        maintenanceRepository.deleteById(id);
        return id;
    }

    @Override
    public List<MaintenanceResponse> getAll() {
        List<Maintenance> maintenanceList = maintenanceRepository.findAll();
        return maintenanceList.stream().map(maintenanceMapper::toResponse).toList();
    }

    @Override
    public List<MaintenanceResponse> findAllByPropertyId(Long propertyId) {
        List<Maintenance> maintenanceList;
        if(securityUtils.getRole().equals("CUSTOMER")) {
            Long accountId = securityUtils.getPrincipal().getId();
            maintenanceList = maintenanceRepository.findAllByPropertyIdAndAccountId(propertyId, accountId);
        } else {
            maintenanceList = maintenanceRepository.findAllByPropertyId(propertyId);
        }
        return maintenanceList.stream().map(maintenanceMapper::toResponse).toList();
    }
}
