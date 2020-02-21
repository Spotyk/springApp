let itemsArray = localStorage.getItem('items') ? JSON.parse(localStorage.getItem('items')) : []

localStorage.setItem('items', JSON.stringify(itemsArray));
var localStorageData = JSON.parse(localStorage.getItem('items'));
var cartSelector = ".cart";

if($(cartSelector).length == 1){
    showCartItems();
}

function addToCart(event){
    let product = event;

    itemsArray.push(product);
    localStorage.setItem('items', JSON.stringify(itemsArray));
    localStorageData = JSON.parse(localStorage.getItem('items'));


}

function getCartItems(){
    return $.ajax({
                   url: "/getItemsById",
                   type: 'GET',
                   data:{items:itemsArray},
                   headers: { 'X-XSRF-TOKEN': csrfToken },
               })
}

function showCartItems(){
    getCartItems().statusCode({
                              400: function() {
                                  console.log("success");
                              },
                              200: function(data) {

                                    var totalAmount = 0;
                                    for (index in data) {
                                    let quantity = countItemsInStorage(data[index].id);
                                    let sum = data[index].price * quantity;
                                        $(cartSelector).append(`<tr>
                                        <td> ${data[index].name}</td>
                                        <td>${data[index].price} </td>
                                        <td>${quantity}</td>
                                        <td>${sum} </td>
                                        <td><button type="button" class="btn btn-outline-danger" onclick="deleteItem(event,${data[index].id})">Delete</button> </td>
                                                </tr>`);
                                    totalAmount += sum;
                                    }
                                    $(cartSelector).append(`
                                                 <td></td>
                                                 <td></td>
                                                 <td></td>
                                                 <td>
                                                     ${totalAmount}
                                                     <button type="button" class="btn btn-outline-danger" onclick="createOrder()">Create Order</button>
                                                 </td>`)
                                    }});
}

function countItemsInStorage(itemId){
    let countCopies = 0;
    localStorageData.forEach(item => {
        if(item === itemId){
        countCopies++;
        }
    });
        return countCopies;
}

function createOrder(){
            $.ajax({
                   url: "/createOrder",
                   type: 'POST',
                   data:{items:itemsArray},
                   headers: { 'X-XSRF-TOKEN': csrfToken },
               }).statusCode({
                   400: function() {
                       console.log("success");
                   },
                   200: function(data) {
                   localStorage.clear()
                   window.location.replace("/order")
                         }});
}

function deleteItem(event,id){

    let newArr = itemsArray.filter(function(ele){
           return ele != id;
       });
       localStorage.setItem('items', JSON.stringify(newArr))
    window.location.reload(true);

}