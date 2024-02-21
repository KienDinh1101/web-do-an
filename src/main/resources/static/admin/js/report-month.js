
$(document).ready(function(){
    Chart.defaults.global.defaultFontFamily = "monospace";
    let ctx = document.getElementById("myChart");
    let myChart;

    $('#select_year').change(function () {
        var year = $(this).find(':selected').val();

        var linkGet = "/admin/report/month?year="+year;
        axios.post(linkGet).then(function(res){
            console.log(res.data);
            let dataBaby = [];
            let labels = [];
            res.data.forEach((object) => {
                var m = object.month;
                var m1 = "";
                switch (m) {
                    case 1 : m1 = "January"; break;
                    case 2 : m1 = "February"; break;
                    case 3 : m1 = "March"; break;
                    case 4 : m1 = "April"; break;
                    case 5 : m1 = "May"; break;
                    case 6 : m1 = "June"; break;
                    case 7 : m1 = "July"; break;
                    case 8 : m1 = "August"; break;
                    case 9 : m1 = "September"; break;
                    case 10 : m1 = "October"; break;
                    case 11: m1 = "November"; break;
                    case 12: m1 = "December"; break;
                    default: m1="none"; break;


                }

                dataBaby.push(object.price);
                labels.push(m1);

            })
            myData = {
                labels: labels,
                datasets: [
                    {
                        label: "Doanh thu tiền tháng theo năm " + year,
                        fill: false,
                        backgroundColor: 'rgb(190, 99, 255, 0.25)',
                        borderColor: 'rgb(190, 99, 255)',
                        data: dataBaby,
                    }]
                // {
                //     label: "More data, baby!",
                //     fill: true,
                //     backgroundColor: 'rgba(255, 99, 132, 0.25)',
                //     borderColor: 'rgb(255, 99, 132)',
                //     data: moreDataBaby,
                // }]
            };
            if(myChart !== undefined || myChart){
                myChart.update();
                myChart.destroy();
            }
            var line = $('#chartType').val();
            myChart = new Chart(ctx, {
                type: line,
                data: myData
            });



        }, function(err){

            swal(
                'Error',
                'Fail',
                'error'
            );
        });

        $('#chartType').on('change',function () {
            myChart.update();
            myChart.destroy();

            myChart = new Chart(ctx, {
                type: document.getElementById("chartType").value,
                data: myData,
            });

        })








    });

});






