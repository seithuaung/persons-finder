package com.persons.finder.presentation

import com.persons.finder.domain.data.Person
import com.persons.finder.domain.services.PersonsService
import com.persons.finder.dto.PersonDto
import com.persons.finder.dto.PersonIdsDto
import com.persons.finder.usecases.RegisterPerson
import com.persons.finder.usecases.RetrievePerson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/v1/persons")
class PersonController @Autowired constructor(private val  registerPerson : RegisterPerson,
    private val retrievePerson: RetrievePerson) {

    /*
        TODO PUT API to update/create someone's location using latitude and longitude
        (JSON) Body
     */

    /*
        POST API to create a 'person'
        (JSON) Body and return the id of the created dto
    */
    @PostMapping
    fun createPerson(@RequestBody personDto: PersonDto) : ResponseEntity<PersonDto> {
        return ResponseEntity.ok(registerPerson.register(personDto))
    }

    /*
        TODO GET API to retrieve people around query location with a radius in KM, Use query param for radius.
        TODO API just return a list of persons ids (JSON)
        // Example
        // John wants to know who is around his location within a radius of 10km
        // API would be called using John's id and a radius 10km
     */

    /*
         GET API to retrieve a person or persons name using their ids
        // Example
        // John has the list of people around them, now they need to retrieve everybody's names to display in the app
        // API would be called using person or persons ids
     */
    @GetMapping
    fun getPersons(@RequestParam("id") ids : Set<Long>) : ResponseEntity<List<PersonDto>> {
        return ResponseEntity.ok( retrievePerson.retrievePersons(ids))
    }

}