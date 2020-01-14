<#macro registration path>

<form action="${path}" method="post" enctype="multipart/form-data" class="no-selectable">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />

    <div class="reg-form-position-left">
        <div>
            <span class="invalid-input">${usernameError!}</span>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="user-addon">User Name :</span>
                </div>
                <input type="text" class="form-control" aria-describedby="user-addon" name="username" value="${(user.username)!}">
            </div>
        </div>
        <div>
            <#if path=="/userUpdate">
                <div>
                    <span class="invalid-input">${oldPasswordError!}</span>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="user-addon">Old Password :</span>
                        </div>
                        <input type="text" class="form-control" aria-describedby="user-addon" name="oldPassword" value="${(user.oldPassword)!}">
                    </div>
                </div>
                <div>
            </#if>
            <span class="invalid-input">${passwordError!}</span>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="password-addon">Password :</span>
                </div>
                <input type="password" name="password" class="form-control" aria-describedby="password-addon" value="">
            </div>
            </div>
            <div>
                <span class="invalid-input">${password2Error!}</span>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="password-addon">Cofirm Password :</span>
                    </div>
                    <input type="password" name="password2" class="form-control" aria-describedby="password-addon">
                </div>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">Upload</span>
                </div>
                <div class="custom-file">
                    <input type="file" class="custom-file-input" id="inputGroupFile01" name="avatar">
                    <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
                </div>
            </div>

            <input class="btn btn-primary" type="submit" <#if path=="/userUpdate"> value="Update"
            <#else>
                value="Sign In"
                </#if>/>
        </div>

        <div class="reg-form-right">
            <div>
                <span class="invalid-input">${emailError!}</span>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="email-addon">Email :</span>
                    </div>
                    <input type="email" class="form-control" aria-describedby="email-addon" name="email" value="${(user.email)!}">
                </div>
            </div>
            <div>
                <span class="invalid-input">${birthDateError!}</span>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="birth-addon">Birth Date :</span>
                    </div>
                    <input type="date" name="birthDate" class="form-control" aria-describedby="birth-addon" value="${(user.birthDate)!}">
                </div>
            </div>
            <div>
                <span class="invalid-input">${countryError!}</span>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <label class="input-group-text" for="inputGroupSelect01">County</label>
                    </div>
                    <select class="custom-select" id="inputGroupSelect01" name="country" onchange="countryChange()">
            <option selected value="${(user.country)!}">${(user.country)!}</option>
            <option value="USA">USA</option>
            <option value="Canada">Canada</option>
          </select>
                </div>
            </div>
            <div>
                <span class="invalid-input">${stateError!}</span>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <label class="input-group-text" for="inputGroupSelect02">State</label>
                    </div>
                    <select class="custom-select" id="inputGroupSelect02" name="state" disabled>
            <option selected value="${(user.state)!}">${(user.state)!}</option>
          </select>
                </div>
            </div>
        </div>
</form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="static/js/changeOFCountry.js">
</#macro>