
function dangkythanhcong(event){
    console.log("hi");

    if(kiemtraten()  && ValidateEmail() && checkPassword()) {

    } else{
        event.preventDefault();
    }




}

function kiemtraten(){
    var username_regex = /^(?!.*\.\.)(?!.*\.$)[^\W][\w.]{4,50}$/;
    var username = document.getElementById('username').value;

    if(username !== ""){
        if (username_regex.test(username) === true)
        {
            $(".check-dangky").css("display",  "none");
            return true;

        } else{
            $(".check-dangky").css("display",  "block");
            $(".check-dangky").text('Username của bạn phải có độ dài 5 - 50 kí tự!');
            return false;

        }

    } else {
        $(".check-dangky").css("display",  "block");
        $(".check-dangky").text('Username không được để rỗng');

        return false;
    }

}
function ValidateEmail(){
    var vnf_regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    var  email = document.getElementById("email").value;
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
function checkPassword(){
    var vnf_regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
    var  matkhau1 = document.getElementById("password").value;
    if(matkhau1 !==''){
        if (vnf_regex.test(matkhau1) === true)
        {
            $(".check-dangky").css("display",  "none");
            return true;

        } else{
            $(".check-dangky").css("display",  "block");
            $(".check-dangky").text('Mật khẩu ít nhất 8 kí tự chữ và số và 1 kí tự đặc biệt');

            return false;

        }
    }else{
        $(".check-dangky").css("display",  "block");
        $(".check-dangky").text('Bạn chưa điền mật khẩu!');

        return false;
    }
}


