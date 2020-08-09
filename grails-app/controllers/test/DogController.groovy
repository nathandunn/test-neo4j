package test

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class DogController {

    DogService dogService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond dogService.list(params), model:[dogCount: dogService.count()]
    }

    def show(Long id) {
        respond dogService.get(id)
    }

    def create() {
        respond new Dog(params)
    }

    def save(Dog dog) {
        if (dog == null) {
            notFound()
            return
        }

        try {
            dogService.save(dog)
        } catch (ValidationException e) {
            respond dog.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dog.label', default: 'Dog'), dog.id])
                redirect dog
            }
            '*' { respond dog, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond dogService.get(id)
    }

    def update(Dog dog) {
        if (dog == null) {
            notFound()
            return
        }

        try {
            dogService.save(dog)
        } catch (ValidationException e) {
            respond dog.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dog.label', default: 'Dog'), dog.id])
                redirect dog
            }
            '*'{ respond dog, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        dogService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dog.label', default: 'Dog'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dog.label', default: 'Dog'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
