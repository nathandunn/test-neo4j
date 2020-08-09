package test

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CatController {

    CatService catService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond catService.list(params), model:[catCount: catService.count()]
    }

    def show(Long id) {
        respond catService.get(id)
    }

    def create() {
        respond new Cat(params)
    }

    def save(Cat cat) {
        if (cat == null) {
            notFound()
            return
        }

        try {
            catService.save(cat)
        } catch (ValidationException e) {
            respond cat.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cat.label', default: 'Cat'), cat.id])
                redirect cat
            }
            '*' { respond cat, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond catService.get(id)
    }

    def update(Cat cat) {
        if (cat == null) {
            notFound()
            return
        }

        try {
            catService.save(cat)
        } catch (ValidationException e) {
            respond cat.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'cat.label', default: 'Cat'), cat.id])
                redirect cat
            }
            '*'{ respond cat, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        catService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cat.label', default: 'Cat'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat.label', default: 'Cat'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
