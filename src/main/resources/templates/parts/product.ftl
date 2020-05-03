<#import "/spring.ftl" as spring/>
<#macro product path>
    <form method="POST" action="${path}" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="hidden" name="id" value="${(currentProduct.id)!}">

        <div class="mb-3">
            <label><@spring.message "category"/></label>
            <select class="custom-select product-category-list" name="categoryName">
                <option selected value="${(currentProduct.categoryName)!}">${(currentProduct.categoryName)!}</option>
            </select>
        </div>

        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text"
                      id="product-addon"><@spring.message "productName"/> <@spring.message "ru"/>:</span>
            </div>
            <input type="text" class="form-control" name="productNameRu" value="${(currentProduct.productNameRu)!}">
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text"
                      id="product-addon"><@spring.message "productName"/> <@spring.message "en"/>:</span>
            </div>
            <input type="text" class="form-control" name="productNameEn" value="${(currentProduct.productNameEn)!}">
        </div>

        <div class="mb-3">
            <label for="validationTextarea"><@spring.message "description"/> <@spring.message "ru"/></label>
            <textarea name="descriptionRu" class="form-control" id="validationTextarea"
                      required>${(currentProduct.descriptionRu)!}</textarea>
            <div class="invalid-feedback">
                Please enter a message in the textarea.
            </div>
        </div>
        <div class="mb-3">
            <label for="validationTextarea"><@spring.message "description"/> <@spring.message "en"/></label>
            <textarea name="descriptionEn" class="form-control" id="validationTextarea"
                      required>${(currentProduct.descriptionEn)!}</textarea>
            <div class="invalid-feedback">
                Please enter a message in the textarea.
            </div>
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="quantity-addon"><@spring.message "quantity"/>:</span>
            </div>
            <input type="number" class="form-control" name="quantity" value="${(currentProduct.quantity)!}">
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="price-addon">Price:</span>
            </div>
            <input type="number" class="form-control" name="price" value="${(currentProduct.price)!}">
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text"><@spring.message "image"/></span>
            </div>
            <div class="custom-file">
                <input type="file" class="custom-file-input" id="inputGroupFile01" name="productImg">
                <label class="custom-file-label" for="inputGroupFile01"><@spring.message "chooseFile"/></label>
            </div>
        </div>

        <#if path=="/updateProduct">
            <img class="user-avatar" src="http://localhost:8080/img/${(currentProduct.productImg)!}">
            <input class="btn btn-primary" type="submit"
                   value="<@spring.message "update"/> <@spring.message "product"/>">
        <#else>
            <input class="btn btn-primary" type="submit"
                   value="<@spring.message "create"/> <@spring.message "product"/>">
        </#if>
    </form>
</#macro>