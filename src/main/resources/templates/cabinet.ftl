<#import "parts/common.ftl" as common>
<#assign
    authorized = Session.SPRING_SECURITY_CONTEXT??
>

<#if authorized>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        userId = user.getId()
        name = user.getUsername()
        email = user.getEmail()
        country = user.getCountry()
        state = user.getState()
        birthDate = user.getBirthDate()
        isAdmin = user.isAdmin()
        isEnabled = user.isEnabled()
    >
<#else>
    <#assign
        name = "guest"
        isAdmin = false
    >
</#if>

        <#if user.getAvatarPath()??>
         <#assign
                    avatarPath = user.getAvatarPath()
                    >
                </#if>
<@common.page>
<div class="reg-form-position-left no-selectable" >
    <div>
    <img class = "user-avatar" src = "img/${avatarPath!}">
    </div>
    <form method="POST" enctype="multipart/form-data" id="fileUploadForm">
         <span class="invalid-input filePathError no-display">file name is not valid</span>

                <div class="input-group mb-3">
                    <input type="file" name="filePath" class="form-control" aria-describedby="pass-addon" disabled>
                <div class="input-group-append">
                        <button class="btn btn-outline-secondary changeable" type="button" onclick="makeAble(event)">Change</button>
                      </div>
                </div>
      </form>

    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="user-addon">User Status:</span>
        </div>
        <input type="text" class="form-control" aria-describedby="user-addon" disabled value="${isEnabled?string('Active', 'not Active')}">
    </div>
<span class="invalid-input usernameError no-display">User name is not valid</span>
    <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="active-addon">User Name :</span>
            </div>
            <input type="text" class="form-control" aria-describedby="active-addon" name="username" disabled value="${name}">
               <div class="input-group-append">
                       <button class="btn btn-outline-secondary changeable" type="button" onclick="makeAble(event)">Change</button>
                     </div>
    </div>

    <span class="invalid-input passwordError no-display">Password name is not valid</span>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="pass-addon">New Password :</span>
            </div>
            <input type="password" name="password" class="form-control" aria-describedby="pass-addon" disabled value="">
        <div class="input-group-append">
                <button class="btn btn-outline-secondary changeable" type="button" onclick="makeAble(event)">Change</button>
              </div>
        </div>
</div>
<div class="reg-form-right no-selectable">
<span class="invalid-input emailError no-display">Email name is not valid</span>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="email-addon">Email :</span>
        </div>
        <input type="email" class="form-control" aria-describedby="email-addon" name="email" disabled value="${email}">
    <div class="input-group-append">
            <button class="btn btn-outline-secondary changeable" type="button" onclick="makeAble(event)">Change</button>
          </div>
    </div>
    <span class="invalid-input birthDateError no-display">User name is not valid</span>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="birth-addon">Birth Date :</span>
        </div>
        <input type="date" name="birthDate" class="form-control" aria-describedby="birth-addon" disabled value="${birthDate}">
    <div class="input-group-append">
            <button class="btn btn-outline-secondary changeable" type="button" onclick="makeAble(event)">Change</button>
          </div>
    </div>
    <div class="input-group mb-3">
      <div class="input-group-prepend">
        <label class="input-group-text" for="inputGroupSelect01">County</label>
      </div>
      <select class="custom-select form-control" id="inputGroupSelect01" name="country" disabled onchange="countryChange()">
        <option selected value="${country}">${country}</option>
        <option value="USA">USA</option>
        <option value="Canada">Canada</option>
      </select>
      <div class="input-group-append">
              <button class="btn btn-outline-secondary changeable" type="button" onclick="makeAble(event)">Change</button>
            </div>
    </div>
    <div class="input-group mb-3">
      <div class="input-group-prepend">
        <label class="input-group-text" for="inputGroupSelect02">State</label>
      </div>
      <select class="custom-select form-control" id="inputGroupSelect02" name="state" disabled>
        <option selected value="${state}">${state}</option>
      </select>
      <div class="input-group-append">
              <button class="btn btn-outline-secondary changeable" type="button" onclick="makeAble(event)">Change</button>
      </div>
    </div>
</div>

    <script src="static/js/changeOFCountry.js"></script>
    <script src="static/js/changeOfInputField.js"></script>
</@common.page>