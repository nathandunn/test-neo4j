package test

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CatServiceSpec extends Specification {

    CatService catService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Cat(...).save(flush: true, failOnError: true)
        //new Cat(...).save(flush: true, failOnError: true)
        //Cat cat = new Cat(...).save(flush: true, failOnError: true)
        //new Cat(...).save(flush: true, failOnError: true)
        //new Cat(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //cat.id
    }

    void "test get"() {
        setupData()

        expect:
        catService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Cat> catList = catService.list(max: 2, offset: 2)

        then:
        catList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        catService.count() == 5
    }

    void "test delete"() {
        Long catId = setupData()

        expect:
        catService.count() == 5

        when:
        catService.delete(catId)
        sessionFactory.currentSession.flush()

        then:
        catService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Cat cat = new Cat()
        catService.save(cat)

        then:
        cat.id != null
    }
}
