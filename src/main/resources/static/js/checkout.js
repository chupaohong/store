// Get item info in checkout page
document.addEventListener('DOMContentLoaded', () => {
    let itemID = Object.entries(localStorage);
    let totalPrice = 0;
    // Get quantity
    for (let [key, value] of itemID) {
        document.getElementById(key).value = value;
        // Get price each item
        let eachItemPrice = document.getElementById(`price-${key}`).textContent.split("$");
        let itemPrice = (eachItemPrice[1] * value);
        document.getElementById(`price-${key}`).innerText = '$' + itemPrice.toFixed(2);
        totalPrice += itemPrice;
    }
    document.getElementById('total-price').innerText = '$' + totalPrice.toFixed(2);
    document.getElementById('price-value').value = totalPrice.toFixed(2);
});

// Pay action
const pay = document.getElementById('pay');
const payButton = document.getElementById('pay-button');

payButton.addEventListener('click', () => {
   pay.submit();
});