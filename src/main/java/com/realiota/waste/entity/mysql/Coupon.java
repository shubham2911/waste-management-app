package com.realiota.waste.entity.mysql;

import com.realiota.waste.entity.mysql.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coupon")
public class Coupon extends BaseEntity {

    @Column(name = "issued_by_user_id")
    private Long issuedByUserId;

    @Column(name = "valid_from")
    private Long validFrom;

    @Column(name = "valid_to")
    private Long validTo;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "used_count")
    private Long usedCount;

    @Column(name = "max_count")
    private Long maxCount;

}
