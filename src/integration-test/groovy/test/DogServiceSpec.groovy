package test

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DogServiceSpec extends Specification {

    DogService dogService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Dog(...).save(flush: true, failOnError: true)
        //new Dog(...).save(flush: true, failOnError: true)
        //Dog dog = new Dog(...).save(flush: true, failOnError: true)
        //new Dog(...).save(flush: true, failOnError: true)
        //new Dog(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //dog.id
    }

    void "test get"() {
        setupData()

        expect:
        dogService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Dog> dogList = dogService.list(max: 2, offset: 2)

        then:
        dogList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        dogService.count() == 5
    }

    void "test delete"() {
        Long dogId = setupData()

        expect:
        dogService.count() == 5

        when:
        dogService.delete(dogId)
        sessionFactory.currentSession.flush()

        then:
        dogService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Dog dog = new Dog()
        dogService.save(dog)

        then:
        dog.id != null
    }
}
