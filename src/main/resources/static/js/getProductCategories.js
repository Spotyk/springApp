var csrfToken = $("meta[name='_csrf']").attr("content");
var productPage = "productCreationPage";
var main = "";
var host = window.location.hostname;
var port = window.location.port;

$(document).ready(function () {
    if ($(".product-category-list").length == 1) {
        var $categoryList = $(".product-category-list");
        $categoryList.length = 0;
        getByUrl("http://localhost:8080/getCategories").statusCode({
            400: function () {
                console.log("success");
            },
            200: function (data) {
                $categoryList = data;
                for (index in data) {
                    $('.product-category-list').append(`<option value="${data[index].name}">${data[index].name}</option>`);
                }

            }
        });
    }
    if ($(".menu").length == 1) {

        getByUrl("getAllCategories").statusCode({
            400: function () {
                console.log("success");
            },
            200: function (data) {
                categoryList = data;
                for (index in data) {
                    $(".menu").append(`<button class="flex-inline-item btn btn-dark" onclick="getProductsByCategoryName(event)">${data[index].name}</button>`);
                }
            }
        });
    }

    if ($(".product-list").children().length < 1) {
        getByUrl("/getAllProducts").statusCode({
            400: function () {
                console.log("success");
            },
            200: function (data) {
                categoryList = data;
                if (window.location.href === "http://localhost:8080/showProducts") {
                    getByUrl("http://localhost:8080/getCurrentLanguage").statusCode({

                        200: function (lang) {
                            if (lang == "ru") {
                                for (index in data) {
                                    $(".product-list").append(`<div class="flex-item">
                                                                               <div class="card" style="width: 18rem;">
                                                                                 <img src="img/${data[index].imgPath}" class="card-img-top" alt="...">
                                                                                 <div class="card-body">
                                                                                   <h5 class="card-title">Имя: ${data[index].name}</h5>
                                                                                   <p class="card-text">Описание: ${data[index].description}</p>
                                                                                   <p class="card-text">Цена: ${data[index].price}</p>
                                                                                   <a href="#" class="btn btn-primary">Купить</a>
                                                                                   <a href="/updateProduct/${data[index].id}" class="btn btn-primary">Обновить</a>
                                                                                   <a href="/deleteProduct/${data[index].id}" class="btn btn-primary">Удалить</a>
                                                                                   
                                                                                 </div>
                                                                               </div>
                                                                           </div>`);
                                }
                            } else {
                                for (index in data) {
                                    $(".product-list").append(`<div class="flex-item">
                                                               <div class="card" style="width: 18rem;">
                                                                 <img src="img/${data[index].imgPath}" class="card-img-top" alt="...">
                                                                 <div class="card-body">
                                                                   <h5 class="card-title">Name: ${data[index].name}</h5>
                                                                   <p class="card-text">Description: ${data[index].description}</p>
                                                                   <p class="card-text">Price: ${data[index].price}</p>
                                                                   <a href="#" class="btn btn-primary">Buy</a>
                                                                   <a href="/updateProduct/${data[index].id}" class="btn btn-primary">Update</a>
                                                                   <a href="/deleteProduct/${data[index].id}" class="btn btn-primary">Delete</a>
                                                                 </div>
                                                               </div>
                                                           </div>`);
                                }
                            }
                        }
                    });

                } else {
                    getByUrl("http://localhost:8080/getCurrentLanguage").statusCode({
                        200: function (lang) {
                            if (lang == "ru") {
                                for (index in data) {
                                    $(".product-list").append(`<div class="flex-item">
                                               <div class="card" style="width: 18rem;">
                                                 <img src="img/${data[index].imgPath}" class="card-img-top" alt="...">
                                                 <div class="card-body">
                                                   <h5 class="card-title">Имя: ${data[index].name}</h5>
                                                   <p class="card-text">Описание: ${data[index].description}</p>
                                                   <p class="card-text">Цена: ${data[index].price}</p>
                                                   <a href="#" class="btn btn-primary" onclick="addToCart(${data[index].id})">Купить</a>
                                                 </div>
                                               </div>
                                           </div>`);
                                }
                            } else {
                                for (index in data) {
                                    $(".product-list").append(`<div class="flex-item">
                                                               <div class="card" style="width: 18rem;">
                                                                 <img src="img/${data[index].imgPath}" class="card-img-top" alt="...">
                                                                 <div class="card-body">
                                                                   <h5 class="card-title">Name: ${data[index].name}</h5>
                                                                   <p class="card-text">Description: ${data[index].description}</p>
                                                                   <p class="card-text">Price: ${data[index].price}</p>
                                                                   <a href="#" class="btn btn-primary" onclick="addToCart(${data[index].id})">Buy</a>
                                                                 </div>
                                                               </div>
                                                           </div>`);
                                }
                            }
                        }

                    });
                }
            }
        });

    }
});

function getByUrl(url) {
    return $.ajax({
        url: url,
        type: 'GET',
        headers: {'X-XSRF-TOKEN': csrfToken},
    });
}

function getProductsByCategoryName(event) {
    var categoryName = $(event.target).text();
    $(".product-list").empty();
    $.ajax({
        url: "/category",
        type: 'GET',
        headers: {'X-XSRF-TOKEN': csrfToken},
        data: {name: categoryName}
    }).statusCode({
        400: function () {
            console.log("success");
        },
        200: function (data) {
            getByUrl("http://localhost:8080/getCurrentLanguage").statusCode({
                200: function (lang) {
                    if (lang == "ru") {
                        for (index in data) {
                            $(".product-list").append(`<div class="flex-item">
                                               <div class="card" style="width: 18rem;">
                                                 <img src="img/${data[index].imgPath}" class="card-img-top" alt="...">
                                                 <div class="card-body">
                                                   <h5 class="card-title">Имя: ${data[index].name}</h5>
                                                   <p class="card-text">Описание: ${data[index].description}</p>
                                                   <p class="card-text">Цена: ${data[index].price}</p>
                                                   <a href="#" class="btn btn-primary">Купить</a>
                                                 </div>
                                               </div>
                                           </div>`);
                        }
                    } else {
                        for (index in data) {
                            $(".product-list").append(`<div class="flex-item">
                                                           <div class="card" style="width: 18rem;">
                                                             <img src="img/${data[index].imgPath}" class="card-img-top" alt="...">
                                                             <div class="card-body">
                                                               <h5 class="card-title">Name: ${data[index].name}</h5>
                                                               <p class="card-text">Description: ${data[index].description}</p>
                                                               <p class="card-text">Price: ${data[index].price}</p>
                                                               <a href="#" class="btn btn-primary">Buy</a>
                                                             </div>
                                                           </div>
                                                       </div>`);
                        }
                    }
                }
            });
        }
    });
}