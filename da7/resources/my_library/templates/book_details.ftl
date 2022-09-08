<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bootstrap</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/pushbar.js@1.0.0/src/pushbar.min.css" />
    <link rel="stylesheet" href="/css/style.css" />
</head>
<body>
    <div data-pushbar-id="mobileMenu" data-pushbar-direction="left">
        <div class="text-end">
            <button class="mobile-menu-close" data-pushbar-close>
                <i class="fa-solid fa-rectangle-xmark"></i>
            </button>
        </div>
        <ul class="nav flex-column">
            <#list main_menu as item>
                <li class="nav-item">
                    <a class="nav-link" href="${item.path}">${item.title}</a>
                </li>
            </#list>
        </ul>
    </div>
    <div class="container">
        <header class="row align-items-center">
            <div class="col-auto">
                <span class="website-icon"><i class="fa-solid fa-book-open"></i></span>
            </div>
            <div class="col">
                <h1 class="website-title"><a href="#">Bookworm</a></h1>
                <h2 class="website-slogan">Only the best of literature craft</h2>
            </div>
        </header>
        <nav>
            <button class="mobile-menu-button" data-pushbar-target="mobileMenu">
                <i class="fa-solid fa-bars"></i>
            </button>
            <div class="nav desktop-nav">
               <#list main_menu as item>
                    <li class="nav-item">
                        <a class="nav-link" href="${item.path}">${item.title}</a>
                    </li>
                </#list>
            </div>
        </nav>

        <main>
            <h1>${book.title}</h1>
            <h2>
            <#list book.authors as author>
            <a href="#">${author.full_name}</a>
            </#list>
            </h2>
            <div>
                <img class="img-fluid" src="/images/covers/${book.cover}" />
            </div>
            <p><b>Release date:</b> ${book.release_date}</p>
            <h3>Synopsis</h3>
            <p>${book.synopsis}</p>
            <p><b>Publisher:</b> ${book.publisher.name}</p>
        </main>

    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/pushbar.js@1.0.0/src/pushbar.min.js"></script>
    <script>
        new Pushbar({ blur: true, overlay: true })
    </script>
</body>
</html>