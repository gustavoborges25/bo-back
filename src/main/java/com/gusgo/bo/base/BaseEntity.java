package com.gusgo.bo.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@EqualsAndHashCode(of = {"id"})
@Getter
@SuperBuilder
@NoArgsConstructor
public abstract class BaseEntity {
    @Id
    private UUID id;
}
