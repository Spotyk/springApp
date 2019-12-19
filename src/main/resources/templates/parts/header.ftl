<#import "logout.ftl" as logout>

<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light no-selectable">
        <a class="navbar-brand" href="/">
            <img src="static/image/brand/brand.svg" width="30" height="30" class="d-inline-block align-top" alt="brand">
        </a>
        <a class="navbar-brand" href="/">Company</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse " id="navbarNav">
            <div class="flex-container">
                <div class="left-flex-container">
                    <ul class="navbar-nav right-flex-container">
                        <li class="nav-item flex-item">
                            <a class="nav-link" href="/main">Main </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="left-flex-container">
            <div>Hello, ${name}</div>
            <#if name == "guest">
                <div class="nav-link"><a class="btn btn-primary" href="/registration">register</a></div>
                <div class="nav-link"><a class="btn btn-primary" href="/login">login</a></div>
                <#else>
                <div class="nav-link"><@logout.logout /></div>
                <div class="nav-link"><a class="btn btn-primary" href="/#">Cabinet</a></div>
            </#if>
        </div>
    </nav>
</header>