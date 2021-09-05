<#import "parts/common.ftl" as common>
<#import "parts/security.ftl" as sec>
<#import "/spring.ftl" as spring/>

<@common.page>
    <a class="btn btn-info" href="/users"><@spring.message "show"/> <@spring.message "users"/></a>
    <a class="btn btn-info" href="/showProducts"><@spring.message "show"/> <@spring.message "products"/></a>
    <a class="btn btn-info" href="/categoryCreationPage"><@spring.message "create"/> <@spring.message "category"/></a>
    <a class="btn btn-info" href="/categoryUpdatePage"><@spring.message "update"/> <@spring.message "category"/></a>
    <a class="btn btn-info" href="/productCreationPage"><@spring.message "create"/> <@spring.message "product"/></a>
    <a class="btn btn-info" href="/showOrders"><@spring.message "show"/> <@spring.message "orders"/></a>
    <a class="btn btn-info" href="/languageCreationPage"><@spring.message "create"/> <@spring.message "language"/></a>
    <a class="btn btn-info" href="/languageUpdatePage"><@spring.message "update"/> <@spring.message "language"/></a>
    <a class="btn btn-info" href="/deleteCategoryPage"><@spring.message "delete"/> <@spring.message "category"/></a>
    <a class="btn btn-info" href="/managers"><@spring.message "show"/> <@spring.message "managers"/></a>
    <a class="btn btn-info" href="/adminProductsDemand"><@spring.message "show"/> <@spring.message "productsDemand"/></a>
    <a class="btn btn-info" href="/adminProductsAvailability"><@spring.message "show"/> <@spring.message "productsAvailability"/></a>
    <a class="btn btn-info" href="/adminGetStocks"><@spring.message "show"/> <@spring.message "stock"/></a>

    <#if sec.role?? && sec.role?seq_contains("ADMIN")>
        <a class="btn btn-info" href="/addAdmin"><@spring.message "create"/>Admin</a>
        <a class="btn btn-info" href="/addManager"><@spring.message "create"/>Manager</a>
    </#if>
</@common.page>