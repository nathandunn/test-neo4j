package test

import grails.gorm.services.Service

@Service(Cat)
interface CatService {

    Cat get(Serializable id)

    List<Cat> list(Map args)

    Long count()

    void delete(Serializable id)

    Cat save(Cat cat)

}