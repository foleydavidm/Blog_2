package blog2



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BlogPostController {
    //I actually added this, everything else, I don't really know about.
    def scaffold = true



    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BlogPost.list(params), model:[blogPostInstanceCount: BlogPost.count()]
    }

    def show(BlogPost blogPostInstance) {
        respond blogPostInstance
    }

    def create() {
        respond new BlogPost(params)
    }

    @Transactional
    def save(BlogPost blogPostInstance) {
        if (blogPostInstance == null) {
            notFound()
            return
        }

        if (blogPostInstance.hasErrors()) {
            respond blogPostInstance.errors, view:'create'
            return
        }

        blogPostInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'blogPost.label', default: 'BlogPost'), blogPostInstance.id])
                redirect blogPostInstance
            }
            '*' { respond blogPostInstance, [status: CREATED] }
        }
    }

    def edit(BlogPost blogPostInstance) {
        respond blogPostInstance
    }

    @Transactional
    def update(BlogPost blogPostInstance) {
        if (blogPostInstance == null) {
            notFound()
            return
        }

        if (blogPostInstance.hasErrors()) {
            respond blogPostInstance.errors, view:'edit'
            return
        }

        blogPostInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'BlogPost.label', default: 'BlogPost'), blogPostInstance.id])
                redirect blogPostInstance
            }
            '*'{ respond blogPostInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(BlogPost blogPostInstance) {

        if (blogPostInstance == null) {
            notFound()
            return
        }

        blogPostInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'BlogPost.label', default: 'BlogPost'), blogPostInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'blogPost.label', default: 'BlogPost'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
