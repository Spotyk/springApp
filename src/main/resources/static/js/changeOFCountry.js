var canadaCountries = {
    Cariboo : 'Cariboo',
    Nanaimo : 'Nanaimo',
    Stikine : 'Stikine'
};

var usaCountries = {
    Alaska : 'Alaska',
    Arizona : 'Arizona',
    Alabama : 'Alabama'
};

function countryChange(){
    var cityListBox = document.getElementById("inputGroupSelect02");
    var selectedCountry = document.getElementById("inputGroupSelect01").value;

    cityListBox.length = 0;
    if(selectedCountry == "USA"){
        disableElement(cityListBox, false);
        addOptions(cityListBox, usaCountries);
    }
    else if(selectedCountry == "Canada"){
        disableElement(cityListBox, false);
        addOptions(cityListBox, canadaCountries);
    }
    else{
        disableElement(cityListBox, true);
        cityListBox.value = 0;
     return;
    }
    $(cityListBox).parent().find(".changeable").text("Save")
}

function disableElement(element, bool){
    element.disabled = bool;
}

function addOptions(element, obj){
    for(index in obj) {
        element.options[element.options.length] = new Option(obj[index], index);
    }
}

