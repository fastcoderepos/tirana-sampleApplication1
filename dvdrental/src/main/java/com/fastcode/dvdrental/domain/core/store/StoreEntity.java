package com.fastcode.dvdrental.domain.core.store;

import com.fastcode.dvdrental.domain.core.abstractentity.AbstractEntity;
import com.fastcode.dvdrental.domain.core.address.AddressEntity;
import com.fastcode.dvdrental.domain.core.customer.CustomerEntity;
import com.fastcode.dvdrental.domain.core.inventory.InventoryEntity;
import com.fastcode.dvdrental.domain.core.staff.StaffEntity;
import java.time.*;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "store")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class StoreEntity extends AbstractEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id", nullable = false)
    private Integer storeId;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private Set<StaffEntity> staffsSet = new HashSet<StaffEntity>();

    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private Set<InventoryEntity> inventorysSet = new HashSet<InventoryEntity>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private Set<CustomerEntity> customersSet = new HashSet<CustomerEntity>();
}
