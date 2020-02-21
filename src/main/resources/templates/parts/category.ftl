<#macro category path>

 <span class="invalid-input"> ${categoryNameError!}</span>
    <form method="POST" action="${path}">
        <input type="hidden" name="_csrf" value="${_csrf.token}">

        <#if path == "/updateCategory">
                <div class="mb-3">
                    <label >Category</label>
                    <select class="custom-select product-category-list" name="categoryName">
                    </select>
                </div>
        </#if>

        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="category-addon">Category Name:</span>
            </div>
            <input type="text" class="form-control" name="updatedCategoryName">
        </div>
        <#if path == "/updateCategory">
            <input class="btn btn-primary" type="submit" value="Update Category">
        <#else>
            <input class="btn btn-primary" type="submit" value="Create Category">
        </#if>
    </form>
    </#macro>