package com.siad.gestao_imobiliaria.auditoria;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Getter
@Setter

public class Auditable {


    @CreatedDate
    @Column(updatable = false, nullable = false)
    private Instant createdAt;


    @CreatedBy
    @Column(updatable = false, nullable = false)
    private String createdBy;

    @LastModifiedDate
    private Instant updatedAt;


    @LastModifiedBy
    private String updatedBy;


}
