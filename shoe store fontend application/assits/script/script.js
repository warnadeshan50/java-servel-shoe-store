$(document).ready(function(){
$('#customer_save').on('click', () => {
    console.log('clicked');
    let Cf_name = $('#firstName').val();
    let Cl_name = $('#lastName').val();
    let Cemail = $('#email').val();
    let Caddress = $('#address').val();
    console.log(Cf_name + " " + Cl_name + " " + Cemail + " " + Caddress);
    let CustomerObject = {
        first_name: Cf_name,
        last_name: Cl_name,
        email: Cemail,
        address: Caddress
    };
    const customerJSON = JSON.stringify(CustomerObject);
    console.log(customerJSON);


     $.ajax({
                url:"http://localhost:8080/shoe_store/customer",
                type:"POST",
                data:customerJSON,
                headers:{"Content-Type":"application/json"},
                success: (res) =>{
                    console.log(JSON.stringify(res))
                    Swal.fire(
                        'Success',
                        'Customer saved successfully',
                        'success'
                    )
                },
                error: (err)=>{
                    Swal.fire(
                        'Failed',
                        'Error',
                        'error'
                    )
                    console.error(err)
                }
              });

});
})


$(document).ready(function(){
    $('#customer_search').on('click', () => {
        console.log('clicked');
        let Cid = $('#id').val();
        console.log(Cid);
        let customerId = Cid;

        // Send data in the query parameter
        $.ajax({
            url: "http://localhost:8080/shoe_store/customer?cId="+customerId,
            type: "GET",
            headers:{"Content-Type":"application/json"}, // Pass data as an object
            success: (res) => {
                console.log(JSON.stringify(res))
                var f_name=res.first_name;
                var l_name=res.last_name;
                var email=res.email;
                var address=res.address;
                console.log(l_name);
                $('#firstName').val(f_name);
                $('#lastName').val(l_name);
                $('#email').val(email);
                $('#address').val(address);
                Swal.fire(
                    'Success',
                    'Customer Founded successfully',
                    'success'
                )
            },
            error: (err) => {
                Swal.fire(
                    'Failed',
                    'Error',
                    'error'
                )
                console.error(err)
            }
        });

    });
});

$(document).ready(function(){
    $('#customer_update').on('click', () => {
        console.log('clicked');
        let Cf_name = $('#firstName').val();
        let Cl_name = $('#lastName').val();
        let Cemail = $('#email').val();
        let Caddress = $('#address').val();
        console.log(Cf_name + " " + Cl_name + " " + Cemail + " " + Caddress);
        let CustomerObject = {
            first_name: Cf_name,
            last_name: Cl_name,
            email: Cemail,
            address: Caddress,
            
        };
        const customerJSON = JSON.stringify(CustomerObject);
        console.log(customerJSON);
    
    
         $.ajax({
            
                    url:"http://localhost:8080/shoe_store/customer",
                    type:"PUT",
                    data:customerJSON,
                    headers:{"Content-Type":"application/json"},
                    success: (res) =>{
                        console.log(JSON.stringify(res))
                        Swal.fire(
                            'Success',
                            'Customer updated successfully',
                            'success'
                        )
                    },
                    error: (err)=>{
                        Swal.fire(
                            'Failed',
                            'Error',
                            'error'
                        )
                        console.error(err)
                    }
                  });
    
    });
});

$(document).ready(function(){
    $('#customer_delete').on('click', () => {
        let Cid = $('#id').val();
        console.log(Cid);
        let customerId = Cid;
        // console.log('clicked');
        // let Cf_name = $('#firstName').val();
        // let Cl_name = $('#lastName').val();
        // let Cemail = $('#email').val();
        // let Caddress = $('#address').val();
        // console.log(Cf_name + " " + Cl_name + " " + Cemail + " " + Caddress);
        // let CustomerObject = {
        //     first_name: Cf_name,
        //     last_name: Cl_name,
        //     email: Cemail,
        //     address: Caddress,
            
        // };
        // const customerJSON = JSON.stringify(CustomerObject);
        // console.log(customerJSON);
    
    
        $.ajax({
            url: "http://localhost:8080/shoe_store/customer?cId="+customerId,
            type: "DELETE",
            headers:{"Content-Type":"application/json"}, // Pass data as an object
            success: (res) => {
                // console.log(JSON.stringify(res))
                // var f_name=res.first_name;
                // var l_name=res.last_name;
                // var email=res.email;
                // var address=res.address;
                // console.log(l_name);
                // $('#firstName').val(f_name);
                // $('#lastName').val(l_name);
                // $('#email').val(email);
                // $('#address').val(address);
                Swal.fire(
                    'Success',
                    'Customer Founded successfully',
                    'success'
                )
            },
            error: (err) => {
                Swal.fire(
                    'Failed',
                    'Error',
                    'error'
                )
                console.error(err)
            }
        });

    });
});