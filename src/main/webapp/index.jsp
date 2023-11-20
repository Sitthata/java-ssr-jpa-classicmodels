<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.6.1/font/bootstrap-icons.css">
    <script src="https://kit.fontawesome.com/07b04e1182.js" crossorigin="anonymous"></script>
    <script>
        const testAjax = () => {
            let xhr = new XMLHttpRequest();
            xhr.onload = () => {
                if (xhr.status === 200) {
                    alert(xhr.responseText);
                } else {
                    alert("Error");
                }
            };
            xhr.open("GET", "office-list");
            xhr.send();
        }

        const loadProduct = (page, pageSize = $("#itemsPage").val()) => {
            setLoading(true);
            $.ajax({
                url: "product-list?page=" + page + "&pageSize=" + pageSize,
                type: "GET",
                success: function (response) {
                    setLoading(false);
                    $("#body-content").html(response);
                },
                error: function (xhr) {
                    alert("Error");
                }
            });
        }

        const setLoading = (isLoading) => {
            let loading = $("#loading");
            if (isLoading) {
                loading.removeClass("d-none");
            } else {
                loading.addClass("d-none");
            }
        }

        const addToCart = (productCode) => {
            setLoading(true);
            const xhttp = new XMLHttpRequest();
            xhttp.onload = () => {
                setLoading(false);
                const cartInfo = $("#noOfItemInCart");
                const noOfItem = parseInt(xhttp.responseText);
                if (noOfItem > 0) {
                    cartInfo.css("display", "inline");
                    cartInfo.html(noOfItem);
                } else {
                    cartInfo.css("display", "none")
                }
            }
            xhttp.open("GET", "add-to-cart?productCode=" + productCode);
            xhttp.send();
        }

        const viewCart = () => {
            setLoading(true);
            const xhttp = new XMLHttpRequest();
            xhttp.onload = () => {
                setLoading(false);
                $("#view-cart").html(xhttp.responseText);
                $("#viewCartModal").modal("show");
            }
            xhttp.open("GET", "view-cart.jsp");
            xhttp.send();
        }
    </script>
    <style>
        .div-link {
            cursor: pointer;
            border: 1px burlywood solid;
        }

        .div-link:hover {
            background-color: bisque;
        }

        .cart-info {
            margin-left: -1em;
            border-radius: 12px;
            background-color: bisque;
            position: absolute;
            z-index: 100;
            border: none;
            padding-left: 5px;
            padding-right: 5px;
            display: none;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <div class="container-fluid"><a class="navbar-brand text-warning" href="javascript:void(0)">Classic Model</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"><span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="mynavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item"><a class="nav-link" href="javascript:loadOffice('')">Office</a></li>
                <li class="nav-item"><a class="nav-link" href="javascript:loadProduct(1,15)">Product</a></li>
                <li class="nav-item">
                    <a class="nav-link" href="javascript:void(0)">Order History</a>
                </li>
                <li class="nav-item ml-4">
                    <a class="nav-link text-light" href="#"><i class="bi bi-box-arrow-in-right"></i> Login</a>
                </li>
            </ul>
            <div style="margin-right: 20px">
                <img src="images/cart-shopping-solid.svg" width="42" onclick="viewCart()"/>
                <button id="noOfItemInCart" class="cart-info" onclick="viewCart()">1</button>
            </div>
            <form class="d-flex">
                <input id="searchBox" class="form-control me-2" type="text" placeholder="Search">
                <button class="btn btn-primary" type="button" onclick="search(thisContent)">Search</button>
            </form>
        </div>
    </div>
</nav>
<div class="container" id="body-content">
    <h3>SIT-202: Classic Model Online</h3>
    <p>SIT-202 is a global online marketplace. <br>
    <p>SIT-202 is a young and dynamic company specialized in the online
        sale of static collectible models and related accessories. Our location are distributed around the world. <br> …
        they are popular collectibles for every automobile fan.rs! .. </p>
</div>
<div class="d-flex justify-content-center modal d-none" id="loading">
    <div class="spinner-border text-primary"
         style="margin-top: 10%; width: 6rem; height: 6rem;"></div>
</div>

<div class="modal" tabindex="-1" id="viewCartModal">
    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header"><h5 class="modal-title">Your Cart</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"
                        onclick="$('#viewCartModal').modal('hide')"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body" id="view-cart"><p>Modal body text goes here.</p></div>
        </div>
    </div>
</div>
</body>
</html>