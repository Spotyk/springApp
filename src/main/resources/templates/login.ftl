<#import "parts/common.ftl" as common>

<@common.page>
    <div class="login-form-position no-selectable">
        <form action="/login" method="post">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="user-addon">User Name :</span>
                </div>
                <input type="text" class="form-control" aria-describedby="user-addon" name="username">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="password-addon">Password :</span>
                </div>
                <input type="password" name="password" class="form-control" aria-describedby="password-addon">
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div>
                <input class="btn btn-primary" type="submit" value="Sign In" />
                <a class="btn btn-primary" href="/registration">Add new user</a>
            </div>
        </form>
    </div>
</@common.page>

