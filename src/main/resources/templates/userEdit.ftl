<#import "parts/common.ftl" as common>
<@common.page>
User editor
<form method="post" action="/user">
    <input type="text" name="username" value="${user.username}">
    <#list roles as role>
    <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked","")}/>${role}</label>
    </#list>
    <input type="hidden" name="userId" value="${user.id}">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button type="submit">Save</button>
</form>
</@common.page>
