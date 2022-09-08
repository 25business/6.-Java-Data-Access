<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/suneditor@latest/dist/css/suneditor.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css" />
</head>
<body>
    <#if saveAuthor?has_content>
        <#if saveAuthor == "true">
            <div class="success_modal">
                Author has been saved! 
                <button class="close_button"
                onclick="this.parentElement.remove()">&times;</button>
            </div>
        <#else>
            <div class="error_modal">
                There was an error saving author!
                <button class="close_button"
                onclick="this.parentElement.remove()">&times;</button>
            </div>
        </#if>
    </#if>

     <#if deleteAuthor?has_content>
        <#if deleteAuthor == "true">
            <div class="success_modal">
                Author has been deleted! 
                <button class="close_button"
                onclick="this.parentElement.remove()">&times;</button>
            </div>
        <#else>
            <div class="error_modal">
                There was an error deleting author!
                <button class="close_button"
                onclick="this.parentElement.remove()">&times;</button>
            </div>
        </#if>
    </#if>

    <#if updateAuthor?has_content>
        <#if updateAuthor == "true">
            <div class="success_modal">
                Author has been updated! 
                <button class="close_button"
                onclick="this.parentElement.remove()">&times;</button>
            </div>
        <#else>
            <div class="error_modal">
                There was an error updating author!
                <button class="close_button"
                onclick="this.parentElement.remove()">&times;</button>
            </div>
        </#if>
    </#if>


    <!-- CRUD - Create (Insert), Read (Select), Update, Delete -->
    <table class="author_list_table">
    <thead>
        <tr>
            <th>DB ID</th>    
            <th>Author</th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
    <#list authors as author>
        <tr>
            <td>${author.authors_id}</td>
            <td>${author.full_name}</td>
            <td><a href="#">View</a></td>
            <td><a href="/admin/author/edit/${author.authors_id}">Edit</a></td>
            <td><a class="delete_author_button" 
            data-author="${author.full_name}"
            data-authorid="${author.authors_id}" href="#">Delete</a></td>
        </tr>
    </#list>
    </tbody>
    </table>

    <form id="new_author_form" action="/admin/author/new" method="post" enctype="multipart/form-data">
        <div>
            <label>Full name</label>
            <input type="text" name="full_name" />
        </div>
        <div>
            <label>Photo</label>
            <input type="file" name="photo" />
        </div>
        <div>
            <label>Biography</label>
            <textarea name="biography" id="author_bio"></textarea>
        </div>
        <div>
            <label>Born</label>
            <input type="date" name="born" />
        </div>
        <div>
            <label>Died</label>
            <input type="date" name="died">
        </div>
        <div>
            <button type="submit">Save</button>
        </div>
    </form>


    <form action="/admin/book/new" method="post" enctype="multipart/form-data">
        <div>
            <label>Title</label>
            <input type="text" name="title" />
        </div>
        <div>
            <label>Book cover</label>
            <input type="file" name="cover" />
        </div>
        <div>
            <label>Synopsis</label>
            <textarea name="synopsis"></textarea>
        </div>
        <div>
            <label>Release date</label>
            <input type="date" name="release_date" />
        </div>
        <div>
            <#list authors as author>
                <p>
                    <input type="checkbox" name="book_author" value="${author.authors_id}">
                    ${author.full_name}
                </p>
            </#list>
        </div>
        <div>
            <select name="publisher">
            <option selected disabled value="-1">--Choose publisher--</option>
            <#list publishers as publisher>
                <option value="${publisher.publishers_id}">
                ${publisher.name}
                </option>
            </#list>
            </select>
        </div>
        <div>
            <button type="submit">Save</button>
        </div>
    </form>

    
    <div id="author_delete_modal">
        <p>Do you wish to delete <span id="span_fullname"></span> ?</p>
        <a id="link_author_delete" href="#">Yes</a>
        <a href="#" onclick="this.parentElement.style.display='none'">No</a>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/suneditor@latest/dist/suneditor.min.js"></script>
    <script>
        const editor = SUNEDITOR.create((document.getElementById('author_bio')),{
            buttonList: [
                ['bold', 'underline', 'italic'],
                ['link']
            ],
            minWidth: '500',
            height: '300'
        });
        const new_author_form = document.getElementById("new_author_form")
        new_author_form.onsubmit = () => {
            const html_content = editor.getContents(true);
            const txt_authorbio = document.getElementById('author_bio');
            txt_authorbio.innerHTML = html_content
        }

        const author_delete_modal = document.getElementById("author_delete_modal")
        const span_fullname = document.getElementById("span_fullname")
        const link_author_delete = document.getElementById("link_author_delete")
        const buttons_delete_author = document.getElementsByClassName("delete_author_button")

        for(let btn_delete of buttons_delete_author) {
            btn_delete.onclick = () => {
                const author_fullname = btn_delete.getAttribute("data-author")
                const author_id = btn_delete.getAttribute("data-authorid")
                author_delete_modal.style.display = "block"
                span_fullname.innerHTML = author_fullname
                link_author_delete.setAttribute("href", "/admin/author/delete/" + author_id)
            }
        }


    </script>

</body>
</html>