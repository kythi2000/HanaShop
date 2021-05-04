/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
paypal.Buttons({
    style: {
        color: 'blue',
        shape: 'pill'
    },
    createOrder: function (data, actions) {
        return actions.order.create({
            purchase_units: [{
                    amount: {
                        value: '0.1'
                    }
                }]
        });
    },
    onApprove: function (data, actions) {
        return actions.order.capture().then(function (details) {
            // This function shows a transaction success message to your buyer.
            alert('Transaction completed by ' + details.payer.name.given_name);
            window.location.replace("http://localhost:8080/J3.L.P0013/ActionCartServlet")
        });
    },
    onCancel: function(data){
        window.location.replace("http://localhost:8080/J3.L.P0013/ActionCartServlet")
    }
}).render('#paypal-button-container');


