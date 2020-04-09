var INPUT_SELECTOR = ".form-control";
var ID_SELECTOR = ".id";
var EMAIL_SELECTOR = ".email";
var STATE_SELECTOR = ".state";
var COUNTRY_SELECTOR = ".country";
var USERNAME_SELECTOR = ".username";
var csrfToken = $("meta[name='_csrf']").attr("content");


function makeAble(event){
    let $thisButton = $(event.target);

    let $inputField = $thisButton.parent().parent().find(INPUT_SELECTOR);

    if($thisButton.text() == "Change"){
        if($inputField.attr("name") === "state"){
            countryChange();
        }
        disableElementJQ($inputField, false);
        changeButtonText($thisButton, 'Save');
    }else{
        confirmValue($inputField, $thisButton);
    }
}

function confirmValue($inputField, $button){
sendUpdateRequest($inputField);

    changeButtonText($button, 'Change');
    disableElementJQ($inputField, true);
    }

function changeButtonText($button, text){
    $button.text(text);

}

function disableElementJQ($element, boolean){
    $element.prop("disabled", boolean)
}

function sendUpdateRequest($inputField){
    var paramName = $inputField.attr("name");

    if(paramName === "filePath"){
        var form = $('#fileUploadForm')[0];

        var data = new FormData(form);

        $.ajax({
            type: "POST",
            headers: {'X-XSRF-TOKEN': csrfToken},
            enctype: 'multipart/form-data',
            url: "/updateFilePath",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            success: function (data) {
                $(".user-avatar").attr("src", `img/${data}`);
                console.log("SUCCESS : ", data);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
    }else{
        $.ajax({
                url: `/cabinet`,
                type: 'post',
                headers: {'X-XSRF-TOKEN': csrfToken},
                data: { inputName:paramName,
                inputFieldValue:$inputField.val()}
            }).statusCode({
                200: function() {
                    console.log("success");
                },
                400: function(response) {
                console.log(response.responseText)
                var array = JSON.parse("[" + response.responseText + "]");
                showArrayFromString(array);
                }
            });
        }
}

function showArrayFromString(array) {
    var stringValueOfElem = new String(array);
    var arrOfSelectors = stringValueOfElem.split(/(\s+)/);

    arrOfSelectors.forEach(function(element) {
        $(element).show();
    });
}
function makeAbleFields(event){
    event.preventDefault();
    let $thisButton = $(event.target);

    let $inputFields = $thisButton.parent().parent().find(INPUT_SELECTOR);

    if($thisButton.text() == "Change"){
        disableElementJQ($inputFields, false);
        changeButtonText($thisButton, 'Save');
    }else{
        disableElementJQ($inputFields, true);
        changeButtonText($thisButton, 'Change');

        let $grandParent = $thisButton.parent().parent();

        let $inputIdValue = $grandParent.find(INPUT_SELECTOR + ID_SELECTOR);
        let $inputUsernameValue = $grandParent.find(INPUT_SELECTOR + USERNAME_SELECTOR);
        let $inputEmailValue = $grandParent.find(INPUT_SELECTOR + EMAIL_SELECTOR);
        let $inputCountryValue = $grandParent.find(INPUT_SELECTOR + COUNTRY_SELECTOR);
        let $inputStateValue = $grandParent.find(INPUT_SELECTOR + STATE_SELECTOR);

        sendForm($inputIdValue, $inputUsernameValue, $inputEmailValue, $inputCountryValue, $inputStateValue);
    }
}

function sendForm($inputIdValue, $inputUsernameValue, $inputEmailValue, $inputCountryValue, $inputStateValue){
    $.ajax({
            url: `/changeUserInfo`,
            type: 'post',
            headers: {'X-XSRF-TOKEN': csrfToken},
            data: { id:$inputIdValue.val(),
            username:$inputUsernameValue.val(),
            email:$inputEmailValue.val(),
            country:$inputCountryValue.val(),
            state:$inputStateValue.val() }
        }).statusCode({
            200: function() {
                console.log("success");
            },
            400: function(response) {
            console.log(response.responseText)
            var array = JSON.parse("[" + response.responseText + "]");
            showArrayFromString(array);
            }
        });
}