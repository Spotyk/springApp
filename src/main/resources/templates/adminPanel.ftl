<#import "parts/common.ftl" as common>
<#import "parts/security.ftl" as sec>

<@common.page>
    <a class="btn btn-info" href="/users">Show users</a>
    <a class="btn btn-info" href="/showProducts">Show products</a>
    <a class="btn btn-info" href="/categoryCreationPage">Create Category</a>
    <a class="btn btn-info" href="/categoryUpdatePage">Update Category</a>
    <a class="btn btn-info" href="/productCreationPage">Create Product</a>
    <a class="btn btn-info" href="/showOrders">Show orders</a>

    <#if sec.role?? && sec.role?seq_contains("ADMIN")>
        <a class="btn btn-info" href="/addAdmin">addAdmin</a>
        <a class="btn btn-info" href="/addManager">addManager</a>
    </#if>
</@common.page>