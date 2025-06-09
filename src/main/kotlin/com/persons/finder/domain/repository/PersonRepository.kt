package com.persons.finder.domain.repository

import com.persons.finder.domain.data.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : JpaRepository<Person,Long>
