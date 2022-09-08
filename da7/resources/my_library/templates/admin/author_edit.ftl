<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/suneditor@latest/dist/css/suneditor.min.css" rel="stylesheet">
</head>
<body>
    <form id="new_author_form" action="/admin/author/edit/${author.authors_id}" method="post" enctype="multipart/form-data">
        <div>
            <label>Full name</label>
            <input type="text" name="full_name" value="${author.full_name}" />
        </div>
        <div>
            <label>Photo</label>
            <input type="file" name="photo" />
        </div>
        <div>
            <label>Biography</label>
            <textarea name="biography" id="author_bio">${author.biography!""}</textarea>
        </div>
        <div>
            <label>Born</label>
            <input type="date" name="born" value='${author.born!""}' />
        </div>
        <div>
            <label>Died</label>
            <input type="date" name="died" value='${author.died!""}' />
        </div>
        <div>
            <button type="submit">Update</button>
        </div>
    </form>

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
    </script>
</body>
</html>