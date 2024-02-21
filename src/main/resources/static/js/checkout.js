
function dangkythanhcong(event){
    console.log("hi");

    if(checkName() && ValidateEmail() && checkAddress() && checkmobile()  ) {

    } else{
        event.preventDefault();
    }




}
function checkName() {
    var  mobile = document.getElementById("input-name").value;
    if(mobile !==''){
        $(".check-dangky").css("display",  "none");
        return true;
    } else {
        $(".check-dangky").css("display",  "block");
        $(".check-dangky").text('Bạn chưa điền tên!');

        return false;
    }
}
function checkAddress() {
    var  mobile = document.getElementById("input-address").value;
    if(mobile !==''){
        $(".check-dangky").css("display",  "none");
        return true;
    } else {
        $(".check-dangky").css("display",  "block");
        $(".check-dangky").text('Bạn chưa điền địa chỉ!');
        return false;
    }
}

function checkmobile(){
    var vnf_regex = /((09|03|07|08|05)+([0-9]{8})\b)/g;
    var  mobile = document.getElementById("input-phone-number").value;
    if(mobile !==''){
        if (vnf_regex.test(mobile) === true)
        {
            $(".check-dangky").css("display",  "none");
            return true;
        } else{
            $(".check-dangky").css("display",  "block");
            $(".check-dangky").text('Số điện thoại của bạn không đúng định dạng!');

            return false;

        }
    }else{
        $(".check-dangky").css("display",  "block");
        $(".check-dangky").text('Bạn chưa điền số điện thoại!');

        return false;
    }
}
function ValidateEmail(){
    var vnf_regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    var  email = document.getElementById("input-email").value;
    if(email !==''){
        if (vnf_regex.test(email) === true)
        {
            $(".check-dangky").css("display",  "none");
            return true;

        } else{
            $(".check-dangky").css("display",  "block");
            $(".check-dangky").text('Email của bạn không đúng định dạng!');
            return false;

        }
    }else{
        $(".check-dangky").css("display",  "block");
        $(".check-dangky").text('Bạn chưa điền email!');

        return false;
    }
}
