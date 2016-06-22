package blog2

class BlogPost {

    String blogPostText
    String title
    String authorName
    Date dateCreated
    ArrayList<Comment> comments



    static constraints = {
        blogPostText(blank: false)
        title(blank: false)
        authorName(blank: false)
        dateCreated(editable: false)
    }

}
