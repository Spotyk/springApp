<#import "/spring.ftl" as spring/>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" class="btn btn-primary" value="<@spring.message "signOut"/>"/>
    </form>
</#macro>