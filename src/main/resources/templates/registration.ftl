<#import "parts/common.ftl" as common>

<@common.page>
${message!}

        <form action="/registration" method="post" enctype="multipart/form-data" class="no-selectable">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />

            <div class="reg-form-position-left">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="user-addon">User Name :</span>
                    </div>
                    <input type="text" class="form-control" aria-describedby="user-addon" name="username">
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="password-addon">Password :</span>
                    </div>
                    <input type="password" name="password" class="form-control" aria-describedby="password-addon">
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="password-addon">Cofirm Password :</span>
                    </div>
                    <input type="password" name="passwordConfirmed" class="form-control" aria-describedby="password-addon">
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

                <input class="btn btn-primary" type="submit" value="Sign In" />
            </div>
            <div class="reg-form-right">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="email-addon">Email :</span>
                    </div>
                    <input type="email" class="form-control" aria-describedby="email-addon" name="email">
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="birth-addon">Birth Date :</span>
                    </div>
                    <input type="date" name="birthDate" class="form-control" aria-describedby="birth-addon">
                </div>
                <div class="input-group mb-3">
                  <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">County</label>
                  </div>
                  <select class="custom-select" id="inputGroupSelect01" name="country" onchange="countryChange()">
                    <option selected>Choose...</option>
                    <option value="USA">USA</option>
                    <option value="Canada">Canada</option>
                  </select>
                </div>
                <div class="input-group mb-3">
                  <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect02">State</label>
                  </div>
                  <select class="custom-select" id="inputGroupSelect02" name="state" disabled>
                    <option selected>Choose...</option>
                  </select>
                </div>
            </div>
        </form>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src = "static/js/changeOFCountry.js">
</@common.page>
