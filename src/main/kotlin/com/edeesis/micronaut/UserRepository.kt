package com.edeesis.micronaut

import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.CrudRepository
import io.micronaut.data.repository.jpa.JpaSpecificationExecutor
import io.micronaut.data.repository.jpa.criteria.CriteriaQueryBuilder
import io.micronaut.data.repository.jpa.criteria.PredicateSpecification
import io.micronaut.data.repository.jpa.criteria.QuerySpecification

@R2dbcRepository(dialect = Dialect.H2)
interface UserRepository : CrudRepository<User, Int>, JpaSpecificationExecutor<User> {
    fun findDTO(specs: CriteriaQueryBuilder<UserDTO>): List<UserDTO>

    fun findDTO(specs: PredicateSpecification<User>): List<UserDTO>
}