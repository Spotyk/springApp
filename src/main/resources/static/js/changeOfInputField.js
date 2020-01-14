var INPUT_SELECTOR = ".form-control";

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
    //if valid
    //if changed

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

    let csrfToken = $("meta[name='_csrf']").attr("content");

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