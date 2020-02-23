<#macro product path>
    <form method="POST" action="${path}" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="hidden" name="id" value="${(currentProduct.id)!}">

        <div class="mb-3">
            <label>Category</label>
            <select class="custom-select product-category-list" name="categoryName">
            <option selected value="${(currentProduct.category.name)!}">${(currentProduct.category.name)!}</option>
            </select>
        </div>

        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="product-addon">Product Name:</span>
            </div>
            <input type="text" class="form-control" name="productName" value="${(currentProduct.name)!}">
        </div>

  <div class="mb-3">
    <label for="validationTextarea">Description</label>
    <textarea name="description" class="form-control" id="validationTextarea" placeholder="Required example textarea" required>${(currentProduct.description)!}</textarea>
    <div class="invalid-feedback">
        Please enter a message in the textarea.
    </div>
  </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="quantity-addon">Quantity:</span>
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
                <span class="input-group-text">Product Image</span>
            </div>
            <div class="custom-file">
                <input type="file" class="custom-file-input" id="inputGroupFile01" name="productImg">
                <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
            </div>
        </div>

        <#if path=="/updateProduct">
        <img class = "user-avatar" src = "http://localhost:8080/img/${(currentProduct.imgPath)!}">
        <input class="btn btn-primary" type="submit" value="Update Product">
        <#else>
        <input class="btn btn-primary" type="submit" value="Create Product">
        </#if>
    </form>
</#macro>