$(document).ready(function() {

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('.user-avatar').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }


    $("#input-avatar").change(function(e) {
        readURL(this);
    });


    $("#myForm").submit(function (e) {
        e.preventDefault();
        var formData = new FormData();
        console.log("heelo");
        formData.append('file', $("#input-avatar")[0].files[0]);
        axios.post("/api/upload/upload-image", formData).then(function(res){

            if(res.data.success) {
                $("#avatar").val(res.data.link);
            }
            $("#myForm")[0].submit();
        }, function(err){

            $("#myForm")[0].submit();
        });
    });

});
