<#import "parts/common.ftl" as common>
<#assign
    authorized = Session.SPRING_SECURITY_CONTEXT??
>

<#if authorized>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        email = user.getEmail()
        country = user.getCountry()
        state = user.getState()
        birthDate = user.getBirthDate()
        isAdmin = user.isAdmin()
        isEnabled = user.isEnabled()
        avatarPath = user.getAvatarPath()
    >
<#else>
    <#assign
        name = "guest"
        isAdmin = false
    >
</#if>
<@common.page>
<div class="reg-form-position-left">
    <div><img class = "user-avatar" src = "img/${avatarPath}"></div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="user-addon">User Status:</span>
        </div>
        <input type="text" class="form-control" aria-describedby="user-addon" name="username" disabled value="${isEnabled?string('Active', 'not Active')}">
    </div>

    <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="active-addon">User Name :</span>
            </div>
            <input type="text" class="form-control" aria-describedby="active-addon" name="active" disabled value="${name}">
        </div>
    <a class="btn btn-primary" href="#">Edit User</a>
    <a class="btn btn-primary" href="#"/>Deactivate account</a>
</div>
<div class="reg-form-right">
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="email-addon">Email :</span>
        </div>
        <input type="email" class="form-control" aria-describedby="email-addon" name="email" disabled value="${email}">
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="birth-addon">Birth Date :</span>
        </div>
        <input type="text" name="birthDate" class="form-control" aria-describedby="birth-addon" disabled value="${birthDate}">
    </div>
    <div class="input-group mb-3">
      <div class="input-group-prepend">
        <label class="input-group-text" for="inputGroupSelect01">County</label>
      </div>
      <select class="custom-select" id="inputGroupSelect01" name="country" disabled>
        <option selected>${country}</option>
      </select>
    </div>
    <div class="input-group mb-3">
      <div class="input-group-prepend">
        <label class="input-group-text" for="inputGroupSelect02">State</label>
      </div>
      <select class="custom-select" id="inputGroupSelect02" name="state" disabled>
        <option selected>${state}</option>
      </select>
    </div>
</div>

</@common.page>