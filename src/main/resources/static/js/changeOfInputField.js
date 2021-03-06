var INPUT_SELECTOR = ".form-control";
var ID_SELECTOR = ".id";
var EMAIL_SELECTOR = ".email";
var STATE_SELECTOR = ".state";
var COUNTRY_SELECTOR = ".country";
var USERNAME_SELECTOR = ".username";
var STATUS_SELECTOR = ".status";
var csrfToken = $("meta[name='_csrf']").attr("content");


function makeAble(event) {
    let $thisButton = $(event.target);

    let $inputField = $thisButton.parent().parent().find(INPUT_SELECTOR);

    if ($thisButton.text() == "Change" || $thisButton.text() == "Изменить") {
        if ($inputField.attr("name") === "state") {
            countryChange();
        }
        disableElementJQ($inputField, false);
        getByUrl("http://localhost:8080/getCurrentLanguage").statusCode({
            200: function (lang) {
                if (lang == "ru") {
                    changeButtonText($thisButton, 'Сохранить');
                } else {
                    changeButtonText($thisButton, 'Save');
                }
            }
        });
    } else {
        confirmValue($inputField, $thisButton);
    }
}

function confirmValue($inputField, $button) {
    sendUpdateRequest($inputField);

    getByUrl("http://localhost:8080/getCurrentLanguage").statusCode({
        200: function (lang) {
            if (lang == "ru") {
                changeButtonText($thisButton, 'Изменить');
            } else {
                changeButtonText($thisButton, 'Change');
            }
        }
    });
    disableElementJQ($inputField, true);
}

function changeButtonText($button, text) {
    $button.text(text);

}

function disableElementJQ($element, boolean) {
    $element.prop("disabled", boolean)
}

function sendUpdateRequest($inputField) {
    var paramName = $inputField.attr("name");

    if (paramName === "filePath") {
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
    } else {
        $.ajax({
            url: `/cabinet`,
            type: 'post',
            headers: {'X-XSRF-TOKEN': csrfToken},
            data: {
                inputName: paramName,
                inputFieldValue: $inputField.val()
            }
        }).statusCode({
            200: function () {
                console.log("success");
            },
            400: function (response) {
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

    arrOfSelectors.forEach(function (element) {
        $(element).show();
    });
}

function deleteUser(userId) {
    $.ajax({
        url: `/deleteManager`,
        type: 'post',
        headers: {'X-XSRF-TOKEN': csrfToken},
        data: {
            id: userId
        }
    }).statusCode({
        200: function () {
            console.log("success");
            window.location.reload(true);
        },
        400: function () {

        }
    });
}

function makeAbleFields(event) {
    event.preventDefault();
    let $thisButton = $(event.target);

    let $inputFields = $thisButton.parent().parent().find(INPUT_SELECTOR);

    if ($thisButton.text() == "Change" || $thisButton.text() == "Изменить") {
        disableElementJQ($inputFields, false);
        getByUrl("http://localhost:8080/getCurrentLanguage").statusCode({
            200: function (lang) {
                if (lang == "ru") {
                    changeButtonText($thisButton, 'Сохранить');
                } else {
                    changeButtonText($thisButton, 'Save');
                }
            }
        });

    } else {
        disableElementJQ($inputFields, true);

        getByUrl("http://localhost:8080/getCurrentLanguage").statusCode({
            200: function (lang) {
                if (lang == "ru") {
                    changeButtonText($thisButton, 'Изменить');
                } else {
                    changeButtonText($thisButton, 'Change');
                }
            }
        });

        let $grandParent = $thisButton.parent().parent();

        let $inputIdValue = $grandParent.find(INPUT_SELECTOR + ID_SELECTOR);
        let $inputUsernameValue = $grandParent.find(INPUT_SELECTOR + USERNAME_SELECTOR);
        let $inputEmailValue = $grandParent.find(INPUT_SELECTOR + EMAIL_SELECTOR);
        let $inputCountryValue = $grandParent.find(INPUT_SELECTOR + COUNTRY_SELECTOR);
        let $inputStateValue = $grandParent.find(INPUT_SELECTOR + STATE_SELECTOR);
        let $inputStatusValue = $grandParent.find(INPUT_SELECTOR + STATUS_SELECTOR);

        sendForm($inputIdValue, $inputUsernameValue, $inputEmailValue, $inputCountryValue, $inputStateValue, $inputStatusValue);
    }
}

function sendForm($inputIdValue, $inputUsernameValue, $inputEmailValue, $inputCountryValue, $inputStateValue, $inputStatusValue) {
    $.ajax({
        url: `/changeUserInfo`,
        type: 'post',
        headers: {'X-XSRF-TOKEN': csrfToken},
        data: {
            id: $inputIdValue.val(),
            username: $inputUsernameValue.val(),
            email: $inputEmailValue.val(),
            country: $inputCountryValue.val(),
            status: $inputStatusValue.val(),
            state: $inputStateValue.val()
        }
    }).statusCode({
        200: function () {
            console.log("success");
        },
        400: function (response) {
            console.log(response.responseText)
            var array = JSON.parse("[" + response.responseText + "]");
            showArrayFromString(array);
        }
    });
}