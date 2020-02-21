<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        role = user.getRoles()
        isAdmin = user.isAdmin()
    >
<#else>
    <#assign
        name = "guest"
        isAdmin = false
        role = ["guest"]
    >
</#if>