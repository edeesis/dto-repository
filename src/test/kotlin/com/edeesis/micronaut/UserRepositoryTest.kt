package com.edeesis.micronaut

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.micronaut.data.runtime.criteria.get
import io.micronaut.data.runtime.criteria.query
import io.micronaut.data.runtime.criteria.where
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@MicronautTest(transactional = false)
class UserRepositoryTest(
    private val userRepository: UserRepository
) : DescribeSpec({
    it("querying dto") {
        withContext(Dispatchers.IO) {
            userRepository.save(User(11, "Name"))
            userRepository.save(User(13, "Other"))
        }
        val spec = where<User> {
            root[User::age] greaterThan 10
        }

        val results = userRepository.findDTO(query<User, UserDTO> {
            multiselect(root[User::name].alias(UserDTO::name.name))
            query.where(spec.toPredicate(root, criteriaBuilder))
        })

        results.size shouldBe 2
    }
})