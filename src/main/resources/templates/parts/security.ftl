<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        email = user.getEmail()
        role = user.getRoles()
        isAdmin = user.isAdmin()
    >
<#else>
    <#assign
        name = "guest"
        email = "guest"
        isAdmin = false
        role = ["guest"]
    >
</#if>