<#import "/spring.ftl" as spring/>
<#macro category path>

 <span class="invalid-input"> ${categoryNameError!}</span>
    <form method="POST" action="${path}">
        <input type="hidden" name="_csrf" value="${_csrf.token}">

        <#if path == "/updateCategory">
                <div class="mb-3">
                    <label ><@spring.message "category"/></label>
                    <select class="custom-select product-category-list" name="categoryName">
                    </select>
                </div>
        </#if>

        <div class="input-group mb-3">
            <div class="input-group-prepend">

            </div>
            <#if path == "/updateCategory">
            <span class="input-group-text" id="category-addon"><@spring.message "categoryName"/>:</span>
                <input type="text" class="form-control" name="updatedCategoryName">
           <#else>
                  <span class="input-group-text" id="category-addon"><@spring.message "categoryName"/> <@spring.message "ru"/> :</span>
                  <input type="text" class="form-control" name="categoryNameRu">
                  <span class="input-group-text" id="category-addon"><@spring.message "categoryName"/> <@spring.message "en"/>:</span>
                  <input type="text" class="form-control" name="categoryNameEn">
            </#if>

        </div>
        <#if path == "/updateCategory">
            <input class="btn btn-primary" type="submit" value="<@spring.message "update"/> <@spring.message "category"/>">
        <#else>
            <input class="btn btn-primary" type="submit" value="<@spring.message "create"/> <@spring.message "category"/>">
        </#if>
    </form>
    </#macro>