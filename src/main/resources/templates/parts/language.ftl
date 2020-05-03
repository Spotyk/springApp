<#macro language path>

    <span class="invalid-input"> ${languageNameError!}</span>
    <form method="POST" action="${path}">
        <input type="hidden" name="_csrf" value="${_csrf.token}">

        <#if path == "/updateLanguage">
            <div class="mb-3">
                <label>Language</label>
                <select class="custom-select language-list" name="languageName">
                </select>
            </div>
        </#if>

        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="category-addon">Language Name:</span>
            </div>
            <#if path == "/updateLanguage">
                <input type="text" class="form-control" name="updatedLanguageName">
            <#else>
                <input type="text" class="form-control" name="languageName">

            </#if>

        </div>
        <#if path == "/updateLanguage">
            <input class="btn btn-primary" type="submit" value="Update Language">
        <#else>
            <input class="btn btn-primary" type="submit" value="Create Language">
        </#if>
    </form>
</#macro>