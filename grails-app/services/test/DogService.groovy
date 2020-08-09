package test

import grails.gorm.services.Service

@Service(Dog)
interface DogService {

    Dog get(Serializable id)

    List<Dog> list(Map args)

    Long count()

    void delete(Serializable id)

    Dog save(Dog dog)

}