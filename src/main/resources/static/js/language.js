var LANGUAGE_LIST_SELECTOR = ".language-list";
var LANGUAGES_SELECTOR = ".languages";

$(document).ready(function() {
if($(LANGUAGE_LIST_SELECTOR).children().length < 1){
        getByUrl("http://localhost:8080/getLanguages").statusCode({
            400: function() {
                console.log("success");
            },
            200: function(data) {
                $categoryList = data;
                for (index in data) {
                    $(LANGUAGE_LIST_SELECTOR).append(`<option value="${data[index].name}">${data[index].name}</option>`);
                }
            }
        });
}

if($(LANGUAGES_SELECTOR).children().length < 2){
        getByUrl("http://localhost:8080/getAllLanguages").statusCode({
            400: function() {
                console.log("success");
            },
            200: function(data) {
                $categoryList = data;
                for (index in data) {
                    $(LANGUAGES_SELECTOR).append(`<option value="${data[index].name}">${data[index].name}</option>`);
                }
            }
        });
}

});

function getByUrl(url) {
    return $.ajax({
        url: url,
        type: 'GET',
        headers: { 'X-XSRF-TOKEN': csrfToken },
    })
}

function changeLanguage(){
    var lang = $(LANGUAGES_SELECTOR).val();
    getByUrl(`/?language=${lang}` );
    document.location.reload(true);
}