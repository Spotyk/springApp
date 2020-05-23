<#import "parts/common.ftl" as common>
<#import "/spring.ftl" as spring/>

<@common.page>
    <form method="POST" action="/deleteCategory">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div class="mb-3">
            <label><@spring.message "category"/></label>
            <select class="custom-select product-category-list" name="categoryName">
            </select>
        </div>

        <input class="btn btn-primary" type="submit" value="<@spring.message "delete"/> <@spring.message "category"/>">

    </form>
</@common.page>