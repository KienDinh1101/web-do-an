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


var galleryThumbs = new Swiper('.gallery-thumbs', {
    spaceBetween: 5,
    freeMode: true,
    watchSlidesVisibility: true,
    watchSlidesProgress: true,
    breakpoints: {
        0: {
            slidesPerView: 3,
        },
        992: {
            slidesPerView: 4,
        },
    }
});
var galleryTop = new Swiper('.gallery-top', {
    spaceBetween: 10,
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },
    thumbs: {
        swiper: galleryThumbs
    },
});
// change carousel item height
// gallery-top
var productCarouselTopWidth = $('.gallery-top').outerWidth();
$('.gallery-top').css('height', productCarouselTopWidth);

// gallery-thumbs
var productCarouselThumbsItemWith = $('.gallery-thumbs .swiper-slide').outerWidth();
$('.gallery-thumbs').css('height', productCarouselThumbsItemWith);

// activation zoom plugin
var $easyzoom = $('.easyzoom').easyZoom();



$(function() {
    var rateNumber = $('#rate-product').data("rate");
    $('#rate-product').val(rateNumber);
    $('#rate-product').barrating({
        theme: 'fontawesome-stars',
        initialRating: rateNumber
    });
});
/* chi tiet noi dung */
// $('.more').readmore({
//     collapsedHeight: 300,
//     speed: 500,
//     embedCSS: false,
//     moreLink: '<a href="#" class="a1">Đọc thêm</a>',
//     lessLink: '<a href="#" class="a1">Thu gọn</a>',
//     beforeToggle: function(trigger, element, expanded) {
//         if (expanded === false) {
//             element.addClass('remove-after');
//         } else {
//             element.removeClass('remove-after');
//         }
//     }
// });

var collapsedSize = '250px';
$('.more').each(function() {
    var h = this.scrollHeight;
    console.log(h);
    var div = $(this);
    if (h > 30) {
        div.css('height', collapsedSize);
        div.after('<a id="more" class="a1" href="#">Đọc thêm</a><br/>');
        var link = div.next();
        link.click(function(e) {
            e.stopPropagation();

            if (link.text() != 'Thu gọn') {
                link.text('Thu gọn');
                div.animate({
                    'height': h
                });

            } else {
                div.animate({
                    'height': collapsedSize
                });
                link.text('Đọc thêm');
            }

        });
    }

});


$('.btn-rate').on("click",function () {
    dataRate = {};
    var star =  $("#rate-product").val();
    var userName = $("#dropdownMenuButton").text().trim();
    var productId =  $(this).data("product-id");
    dataRate.star = star;
    dataRate.productId = productId;
    dataRate.userName = userName;
    var linkPost = "/api/rate/update";
    axios.post(linkPost, dataRate).then(function(res){
        if(res.data.success) {
            location.reload();
        } else {
            swal(
                'Fail',
                res.data.message,
                'error'
            ).then(function() {
                location.reload();
            });
        }
    }, function(err){

        swal(
            'Error',
            'Fail',
            'error'
        );
    });

});
$(".item-mau").each(function () {
    var color1 =  $(this).text();
    var color2 =  $(this).data("color");
    if(color1 === color2){
        $(this).addClass("active");
    }
})

$(".add-to-cart").on("click", function () {
    var dataCart = {};
    var pdInfo = $(this).data("product");
    console.log(pdInfo);
    var color = $(this).data("color");
    console.log(color);
    var amount = $('#getAmount').val();
    console.log(amount);
    var price = $('#price').data("price");
    console.log(price);
    dataCart.price = price;
    dataCart.amount = amount;
    dataCart.productId = pdInfo;
    dataCart.color = color;
    dataCart.guid = getCookie("guid");



    var linkPost = "/api/cart-product/add";

    axios.post(linkPost, dataCart).then(function(res){

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

        swal(
            'Error',
            'Fail',
            'error'
        );
    });
});



