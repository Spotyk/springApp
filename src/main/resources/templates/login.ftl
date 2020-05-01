<#import "parts/common.ftl" as common>
<#import "/spring.ftl" as spring/>

<@common.page>
    <div class="login-form-position no-selectable">
        <form action="/authenticate" method="post">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="user-addon"><@spring.message "email"/> :</span>
                </div>
                <input type="text" class="form-control" aria-describedby="user-addon" name="username">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="password-addon"><@spring.message "password"/> :</span>
                </div>
                <input type="password" name="password" class="form-control" aria-describedby="password-addon">
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div>
            <div>
            <img src="/static/image/robot.png">
            </div>
                <input class="btn btn-primary" type="submit" value="<@spring.message "logIn"/>" />
                <a class="btn btn-primary" href="/registration"><@spring.message "register"/></a>
            </div>
        </form>
    </div>
</@common.page>

