getAllEmployees();

function saveEmployee() {
    let name = $('#empName').val();
    let address = $('#empAddress').val();
    let number = $('#empMobile').val();

    $.ajax(
        {
            method: "POST",
            contentType: "application/json",
            url: "http://localhost:8080/api/v1/employee/saveEmployee",
            async: true,
            data: JSON.stringify(
                {
                    "empID": "",
                    "empName": name,
                    "empAddress": address,
                    "empMobile": number
                }),
            success: function (data) {
                alert("Saved Successfully")
                getAllEmployees();

            },
            error: function (xhr, exception) {
                alert("Error")
            }
        })

}

function updateEmployee() {
    let empID = $('#empID').val();
    let name = $('#empName').val();
    let address = $('#empAddress').val();
    let number = $('#empMobile').val();

    $.ajax(
        {
            method: "PUt",
            contentType: "application/json",
            url: "http://localhost:8080/api/v1/employee/updateEmployee",
            async: true,
            data: JSON.stringify(
                {
                    "empID": empID,
                    "empName": name,
                    "empAddress": address,
                    "empMobile": number
                }),
            success: function (data) {
                alert("Updated Successfully")
                getAllEmployees();

            },
            error: function (xhr, exception) {
                alert("Error")
            }
        })

}


function deleteEmployee() {
    let empID = $('#empID').val();


    $.ajax(
        {
            method: "DELETE",
            contentType: "application/json",
            url: "http://localhost:8080/api/v1/employee/deleteEmployeeById/"+empID,
            async: true,
            success: function (data) {
                alert("Deleted Successfully")
                getAllEmployees();

            },
            error: function (xhr, exception) {
                alert("Error")
            }
        })

}


function getAllEmployees() {

    $.ajax(
        {
            method: "GET",
            contentType: "application/json",
            url: "http://localhost:8080/api/v1/employee/getAllEmployees",
            async: true,
            success: function (data) {
                if(data.code==="00"){
                    $('#empTable').empty();
                    for(let emp of data.content){
                        let empID=emp.empID;
                        let empName=emp.empName;
                        let empAddress=emp.empAddress;
                        let empMobile=emp.empMobile;

                        var row= `<tr>
                            <td>${empID}</td>
                            <td>${empName}</td>
                            <td>${empAddress}</td>
                            <td>${empMobile}</td>
                        </tr>`;

                        $('#empTable').append(row)
                    }
                }
            },
            error: function (xhr, exception) {
                alert("Error fetching data")
            }
        })

}


$(document).ready(function (){
    $(document).on('click','#empTable tr',function (){
        var col0=$(this).find('td:eq(0)').text();
        var col1=$(this).find('td:eq(1)').text();
        var col2=$(this).find('td:eq(2)').text();
        var col3=$(this).find('td:eq(3)').text();

        $('#empID').val(col0);
        $('#empName').val(col1);
        $('#empAddress').val(col2);
        $('#empMobile').val(col3);
    })
})
