$( ".expand" ).click(function(event) {
    let $currentTarget = $(event.target);
    let $currentOrderDetails = $currentTarget.parent().find(".order-details");

    $currentOrderDetails.toggle();
});
function getOrderDetails(event,id){
    let $currentTarget = $(event.target);
    let $currentOrderDetails = $currentTarget.parent().find(".order-details");
    $(".order-details").children().empty()

    $.ajax({
                       url: "/getOrderDetailsByOrderId",
                       type: 'GET',
                       data:{id:id},
                       headers: { 'X-XSRF-TOKEN': csrfToken },
                   }).statusCode({
                                                   400: function() {
                                                       console.log("success");
                                                   },
                                                   200: function(data) {

                                                         var totalAmount = 0;
                                                         for (index in data) {
                                                             $.ajax({
                                                                                url: "/getProductNameById",
                                                                                type: 'GET',
                                                                                data:{productId:data[index].product.id},
                                                                                headers: { 'X-XSRF-TOKEN': csrfToken },
                                                                            }).statusCode({

                                                                            200:function(prod){
                                                                             $currentOrderDetails.append(`<tr>
                                                                                                         <td> ${prod.name}</td>
                                                                                                         <td>${data[index].quantity} </td>
                                                                                                         <td>${data[index].product.price}</td>
                                                                                                                 </tr>`);
                                                                            }
                                                                            });

                                                         }
                                                         }});
}

function changeStatus(event){
    let $currentTarget = $(event.target);
    let $currentForm = $currentTarget.parent();

    let $orderId = $currentForm.find(".order-id").val();
    var $orderStatus;
    if( $(".status-select").length >= 1){
         $orderStatus= $currentForm.find(".status-select").val();
    }
    if( $(".status").length >= 1){
        $orderStatus = "CANCELED";
    }



    $.ajax({
                       url: $currentForm.attr("action"),
                       type: 'POST',
                       data:{orderId:$orderId,
                             orderStatus:$orderStatus},
                       headers: { 'X-XSRF-TOKEN': csrfToken },
                   }).statusCode({
                       400: function() {
                           console.log("success");
                       },
                       200: function(data) {
                       window.location.reload(true);
                             }});
}

