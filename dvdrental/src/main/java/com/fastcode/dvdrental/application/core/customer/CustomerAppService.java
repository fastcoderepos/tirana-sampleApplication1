package com.fastcode.dvdrental.application.core.customer;

import com.fastcode.dvdrental.application.core.customer.dto.*;
import com.fastcode.dvdrental.commons.logging.LoggingHelper;
import com.fastcode.dvdrental.commons.search.*;
import com.fastcode.dvdrental.domain.core.address.AddressEntity;
import com.fastcode.dvdrental.domain.core.address.IAddressRepository;
import com.fastcode.dvdrental.domain.core.customer.CustomerEntity;
import com.fastcode.dvdrental.domain.core.customer.ICustomerRepository;
import com.fastcode.dvdrental.domain.core.customer.QCustomerEntity;
import com.fastcode.dvdrental.domain.core.store.IStoreRepository;
import com.fastcode.dvdrental.domain.core.store.StoreEntity;
import com.querydsl.core.BooleanBuilder;
import java.time.*;
import java.util.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("customerAppService")
@RequiredArgsConstructor
public class CustomerAppService implements ICustomerAppService {

    @Qualifier("customerRepository")
    @NonNull
    protected final ICustomerRepository _customerRepository;

    @Qualifier("addressRepository")
    @NonNull
    protected final IAddressRepository _addressRepository;

    @Qualifier("storeRepository")
    @NonNull
    protected final IStoreRepository _storeRepository;

    @Qualifier("ICustomerMapperImpl")
    @NonNull
    protected final ICustomerMapper mapper;

    @NonNull
    protected final LoggingHelper logHelper;

    @Transactional(propagation = Propagation.REQUIRED)
    public CreateCustomerOutput create(CreateCustomerInput input) {
        CustomerEntity customer = mapper.createCustomerInputToCustomerEntity(input);
        AddressEntity foundAddress = null;
        StoreEntity foundStore = null;
        if (input.getAddressId() != null) {
            foundAddress = _addressRepository.findById(input.getAddressId()).orElse(null);

            if (foundAddress != null) {
                customer.setAddress(foundAddress);
            } else {
                return null;
            }
        } else {
            return null;
        }
        if (input.getStoreId() != null) {
            foundStore = _storeRepository.findById(input.getStoreId()).orElse(null);

            if (foundStore != null) {
                customer.setStore(foundStore);
            } else {
                return null;
            }
        } else {
            return null;
        }

        CustomerEntity createdCustomer = _customerRepository.save(customer);
        return mapper.customerEntityToCreateCustomerOutput(createdCustomer);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UpdateCustomerOutput update(Integer customerId, UpdateCustomerInput input) {
        CustomerEntity customer = mapper.updateCustomerInputToCustomerEntity(input);
        AddressEntity foundAddress = null;
        StoreEntity foundStore = null;

        if (input.getAddressId() != null) {
            foundAddress = _addressRepository.findById(input.getAddressId()).orElse(null);

            if (foundAddress != null) {
                customer.setAddress(foundAddress);
            } else {
                return null;
            }
        } else {
            return null;
        }

        if (input.getStoreId() != null) {
            foundStore = _storeRepository.findById(input.getStoreId()).orElse(null);

            if (foundStore != null) {
                customer.setStore(foundStore);
            } else {
                return null;
            }
        } else {
            return null;
        }

        CustomerEntity updatedCustomer = _customerRepository.save(customer);
        return mapper.customerEntityToUpdateCustomerOutput(updatedCustomer);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer customerId) {
        CustomerEntity existing = _customerRepository.findById(customerId).orElse(null);
        _customerRepository.delete(existing);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public FindCustomerByIdOutput findById(Integer customerId) {
        CustomerEntity foundCustomer = _customerRepository.findById(customerId).orElse(null);
        if (foundCustomer == null) return null;

        return mapper.customerEntityToFindCustomerByIdOutput(foundCustomer);
    }

    //Address
    // ReST API Call - GET /customer/1/address
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public GetAddressOutput getAddress(Integer customerId) {
        CustomerEntity foundCustomer = _customerRepository.findById(customerId).orElse(null);
        if (foundCustomer == null) {
            logHelper.getLogger().error("There does not exist a customer wth a id=%s", customerId);
            return null;
        }
        AddressEntity re = foundCustomer.getAddress();
        return mapper.addressEntityToGetAddressOutput(re, foundCustomer);
    }

    //Store
    // ReST API Call - GET /customer/1/store
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public GetStoreOutput getStore(Integer customerId) {
        CustomerEntity foundCustomer = _customerRepository.findById(customerId).orElse(null);
        if (foundCustomer == null) {
            logHelper.getLogger().error("There does not exist a customer wth a id=%s", customerId);
            return null;
        }
        StoreEntity re = foundCustomer.getStore();
        return mapper.storeEntityToGetStoreOutput(re, foundCustomer);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<FindCustomerByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception {
        Page<CustomerEntity> foundCustomer = _customerRepository.findAll(search(search), pageable);
        List<CustomerEntity> customerList = foundCustomer.getContent();
        Iterator<CustomerEntity> customerIterator = customerList.iterator();
        List<FindCustomerByIdOutput> output = new ArrayList<>();

        while (customerIterator.hasNext()) {
            CustomerEntity customer = customerIterator.next();
            output.add(mapper.customerEntityToFindCustomerByIdOutput(customer));
        }
        return output;
    }

    protected BooleanBuilder search(SearchCriteria search) throws Exception {
        QCustomerEntity customer = QCustomerEntity.customerEntity;
        if (search != null) {
            Map<String, SearchFields> map = new HashMap<>();
            for (SearchFields fieldDetails : search.getFields()) {
                map.put(fieldDetails.getFieldName(), fieldDetails);
            }
            List<String> keysList = new ArrayList<String>(map.keySet());
            checkProperties(keysList);
            return searchKeyValuePair(customer, map, search.getJoinColumns());
        }
        return null;
    }

    protected void checkProperties(List<String> list) throws Exception {
        for (int i = 0; i < list.size(); i++) {
            if (
                !(
                    list.get(i).replace("%20", "").trim().equals("addressId") ||
                    list.get(i).replace("%20", "").trim().equals("storeId") ||
                    list.get(i).replace("%20", "").trim().equals("active") ||
                    list.get(i).replace("%20", "").trim().equals("customerId") ||
                    list.get(i).replace("%20", "").trim().equals("email") ||
                    list.get(i).replace("%20", "").trim().equals("firstName") ||
                    list.get(i).replace("%20", "").trim().equals("lastName")
                )
            ) {
                throw new Exception("Wrong URL Format: Property " + list.get(i) + " not found!");
            }
        }
    }

    protected BooleanBuilder searchKeyValuePair(
        QCustomerEntity customer,
        Map<String, SearchFields> map,
        Map<String, String> joinColumns
    ) {
        BooleanBuilder builder = new BooleanBuilder();

        for (Map.Entry<String, SearchFields> details : map.entrySet()) {
            if (details.getKey().replace("%20", "").trim().equals("active")) {
                if (
                    details.getValue().getOperator().equals("equals") &&
                    (
                        details.getValue().getSearchValue().equalsIgnoreCase("true") ||
                        details.getValue().getSearchValue().equalsIgnoreCase("false")
                    )
                ) builder.and(customer.active.eq(Boolean.parseBoolean(details.getValue().getSearchValue()))); else if (
                    details.getValue().getOperator().equals("notEqual") &&
                    (
                        details.getValue().getSearchValue().equalsIgnoreCase("true") ||
                        details.getValue().getSearchValue().equalsIgnoreCase("false")
                    )
                ) builder.and(customer.active.ne(Boolean.parseBoolean(details.getValue().getSearchValue())));
            }
            if (details.getKey().replace("%20", "").trim().equals("customerId")) {
                if (
                    details.getValue().getOperator().equals("equals") &&
                    StringUtils.isNumeric(details.getValue().getSearchValue())
                ) builder.and(customer.customerId.eq(Integer.valueOf(details.getValue().getSearchValue()))); else if (
                    details.getValue().getOperator().equals("notEqual") &&
                    StringUtils.isNumeric(details.getValue().getSearchValue())
                ) builder.and(customer.customerId.ne(Integer.valueOf(details.getValue().getSearchValue()))); else if (
                    details.getValue().getOperator().equals("range")
                ) {
                    if (
                        StringUtils.isNumeric(details.getValue().getStartingValue()) &&
                        StringUtils.isNumeric(details.getValue().getEndingValue())
                    ) builder.and(
                        customer.customerId.between(
                            Integer.valueOf(details.getValue().getStartingValue()),
                            Integer.valueOf(details.getValue().getEndingValue())
                        )
                    ); else if (StringUtils.isNumeric(details.getValue().getStartingValue())) builder.and(
                        customer.customerId.goe(Integer.valueOf(details.getValue().getStartingValue()))
                    ); else if (StringUtils.isNumeric(details.getValue().getEndingValue())) builder.and(
                        customer.customerId.loe(Integer.valueOf(details.getValue().getEndingValue()))
                    );
                }
            }
            if (details.getKey().replace("%20", "").trim().equals("email")) {
                if (details.getValue().getOperator().equals("contains")) builder.and(
                    customer.email.likeIgnoreCase("%" + details.getValue().getSearchValue() + "%")
                ); else if (details.getValue().getOperator().equals("equals")) builder.and(
                    customer.email.eq(details.getValue().getSearchValue())
                ); else if (details.getValue().getOperator().equals("notEqual")) builder.and(
                    customer.email.ne(details.getValue().getSearchValue())
                );
            }
            if (details.getKey().replace("%20", "").trim().equals("firstName")) {
                if (details.getValue().getOperator().equals("contains")) builder.and(
                    customer.firstName.likeIgnoreCase("%" + details.getValue().getSearchValue() + "%")
                ); else if (details.getValue().getOperator().equals("equals")) builder.and(
                    customer.firstName.eq(details.getValue().getSearchValue())
                ); else if (details.getValue().getOperator().equals("notEqual")) builder.and(
                    customer.firstName.ne(details.getValue().getSearchValue())
                );
            }
            if (details.getKey().replace("%20", "").trim().equals("lastName")) {
                if (details.getValue().getOperator().equals("contains")) builder.and(
                    customer.lastName.likeIgnoreCase("%" + details.getValue().getSearchValue() + "%")
                ); else if (details.getValue().getOperator().equals("equals")) builder.and(
                    customer.lastName.eq(details.getValue().getSearchValue())
                ); else if (details.getValue().getOperator().equals("notEqual")) builder.and(
                    customer.lastName.ne(details.getValue().getSearchValue())
                );
            }
        }

        for (Map.Entry<String, String> joinCol : joinColumns.entrySet()) {
            if (joinCol != null && joinCol.getKey().equals("addressId")) {
                builder.and(customer.address.addressId.eq(Integer.parseInt(joinCol.getValue())));
            }
        }
        for (Map.Entry<String, String> joinCol : joinColumns.entrySet()) {
            if (joinCol != null && joinCol.getKey().equals("storeId")) {
                builder.and(customer.store.storeId.eq(Integer.parseInt(joinCol.getValue())));
            }
        }
        return builder;
    }

    public Map<String, String> parsePaymentsJoinColumn(String keysString) {
        Map<String, String> joinColumnMap = new HashMap<String, String>();
        joinColumnMap.put("customerId", keysString);

        return joinColumnMap;
    }

    public Map<String, String> parseRentalsJoinColumn(String keysString) {
        Map<String, String> joinColumnMap = new HashMap<String, String>();
        joinColumnMap.put("customerId", keysString);

        return joinColumnMap;
    }
}
