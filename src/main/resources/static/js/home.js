$(document).ready(function() {
    /******************************
        BOTTOM SCROLL TOP BUTTON
     ******************************/

    // declare variable
    // click() scroll top EMD
    $(".owl-carousel").owlCarousel({
        loop: true,
        margin: 10,
        autoplay: true,
        nav: true,
        responsiveClass: true,
        responsive: {
            0: {
                items: 1,

            },
            600: {
                items: 1,

            },
            1000: {
                items: 1,


            }
        }
    });




    $(".rateYo").each( function() {
        var rating = $(this).attr("data-rate");
        $(this).rateYo(
            {
                rating: rating,
                fullStar: true,
                readOnly: true
            }
        );
    });

    function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires="+d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }

    var delete_cookie = function(name) {
        document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
    };

    function getCookie(name) {
        var nameEQ = name + "=";
        var ca = document.cookie.split(';');
        for(var i=0;i < ca.length;i++) {
            var c = ca[i];
            while (c.charAt(0)==' ') c = c.substring(1,c.length);
            if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
        }
        return null;
    }

    function checkCookie() {
        var user = getCookie("username");
        if (user != "") {
            alert("Welcome again " + user);
        } else {
            user = prompt("Please enter your name:", "");
            if (user != "" && user != null) {
                setCookie("username", user, 365);
            }
        }
    }
    $(".themvaogio").on("click", function () {
        var dataCart = {};
        var pdInfo = $(this).data("product");
        var color = $(this).data("color");
        console.log(color);
        var price = $(this).closest(".sanPham").find('.price-product').data("price");

        dataCart.amount = 1;
        dataCart.color=color;
        dataCart.price=price;
        dataCart.productId = pdInfo;
        dataCart.guid = getCookie("guid");

        NProgress.start();

        var linkPost = "/api/cart-product/add";

        axios.post(linkPost, dataCart).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Success',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
                });
            } else {
                swal(
                    'Fail',
                    res.data.message,
                    'error'
                );
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Fail',
                'error'
            );
        });
    });


}); // ready() END
