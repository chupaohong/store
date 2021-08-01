// Toggle login form
const loginButton = document.querySelectorAll('.info-item .btn');
const active = document.querySelectorAll('.container-form .btn');

loginButton.forEach(button => {
    button.addEventListener('click', () => {
        document.querySelector('.container').classList.toggle('log-in');
    });
});

active.forEach(button => {
    button.addEventListener('click', () => {
        document.querySelector('.container').classList.add('active');
    });
});

// Add to cart animation
document.addEventListener("DOMContentLoaded", function () {
    const cartButtons = document.querySelectorAll('.cart-button');
    const checkout = document.getElementById('checkout');

    cartButtons.forEach(button => {
        button.addEventListener('click', cartClick);
    });

    // Clear item in localStorage
    localStorage.clear();

    document.getElementById('cart').addEventListener('click', () => {
        setTimeout(function () {
            checkout.submit();
        }, 200)

        // Get item from localStorage
        // Checkout action
        for (let [key, value] of Object.entries(localStorage)) {
            createCheckoutElement(key);
        }
    });

    function cartClick() {
        let button = this;
        let cart = document.getElementById('cart');
        let cartTotal = 0;
        for (let [key, value] of Object.entries(localStorage)) {
            cartTotal += parseInt(value);
        }
        button.classList.add('clicked');
        setTimeout(() => {
            cart.classList.add('shake');
            cart.setAttribute('data-totalitems', cartTotal.toString());
            setTimeout(() => {
                cart.classList.remove('shake');
            }, 500);
        }, 100);
        setTimeout(() => {
            button.classList.remove('clicked');
        }, 2000);
    }
});

// Scroll menu animation
const menu = document.getElementById('menu');
const profile = document.getElementById('profile');
const dashboard = document.getElementById('dashboard');
const money = document.getElementById('money');
const logoutButton = document.getElementById('logout-button');
const cart = document.getElementById('cart');

document.addEventListener('scroll', () => {
    let yPos = window.pageYOffset;
    if (yPos > 220) {
        setTimeout(() => {
            menu.classList.add('navbar-light');
            menu.classList.add('bg-light');
            profile.classList.add('btn-outline-primary');
            if (dashboard) {
                dashboard.classList.add('btn-outline-primary');
            }
            money.classList.add('btn-outline-primary');
            logoutButton.classList.add('btn-outline-primary');
            cart.classList.add('btn-outline-dark');
        }, 100)
        profile.classList.remove('btn-outline-warning');
        if (dashboard) {
            dashboard.classList.remove('btn-outline-warning');
        }
        money.classList.remove('btn-outline-warning');
        logoutButton.classList.remove('btn-outline-warning');
        cart.classList.remove('btn-outline-warning;');
    } else if (yPos < 220) {
        setTimeout(() => {
            menu.classList.remove('navbar-light');
            menu.classList.remove('bg-light');
            profile.classList.remove('btn-outline-primary');
            if (dashboard) {
                dashboard.classList.remove('btn-outline-primary');
            }
            money.classList.remove('btn-outline-primary');
            logoutButton.classList.remove('btn-outline-primary');
            cart.classList.remove('btn-outline-dark');
        }, 100)
        profile.classList.add('btn-outline-warning');
        if (dashboard) {
            dashboard.classList.add('btn-outline-warning');
        }
        money.classList.add('btn-outline-warning');
        logoutButton.classList.add('btn-outline-warning');
        cart.classList.add('btn-outline-warning');
    }
});

document.addEventListener('DOMContentLoaded', function () {
    if (window.pageYOffset > 220) {
        setTimeout(() => {
            menu.classList.add('navbar-light');
            menu.classList.add('bg-light');
            profile.classList.add('btn-outline-primary');
            logoutButton.classList.add('btn-outline-primary');
            cart.classList.add('btn-outline-dark');
        }, 100)
        profile.classList.remove('btn-outline-warning');
        logoutButton.classList.remove('btn-outline-warning');
        cart.classList.remove('btn-outline-warning;');
    }
});

// Avatar action
const avatar = document.getElementById('fileInput');
const thumbnail = document.getElementById('thumbnail');
const iconUpload = document.getElementById('icon-upload');

// Hide avatar first load page
document.addEventListener('DOMContentLoaded', () => {
    if (thumbnail.src.includes("null")) {
        thumbnail.style.display = 'none';
        iconUpload.style.display = 'block';
    }
});

// Preview avatar
avatar.addEventListener('change', (event) => {
    // Allowing file type
    let allowedExtensions = /(\.png)$/i;

    if (!allowedExtensions.exec(avatar.value)) {
        alert('Only PNG file type');
    } else {
        thumbnail.src = window.URL.createObjectURL(event.target.files[0]);
        // Hide icon upload
        iconUpload.style.display = 'none';
        thumbnail.style.display = 'block';
    }
});

// Checkout from cart action
// Checkout form element

function getItems(id) {
    // Get first time of each product
    // Check item exists in localStorage
    // List of item
    let itemList = Object.keys(localStorage).toString();
    if (itemList.includes(id.toString())) {
        let quantity = parseInt(localStorage.getItem(id)) + 1;
        localStorage.setItem(id, quantity.toString());
    } else {
        localStorage.setItem(id, "1");
    }

    if (localStorage.getItem(id) === "NaN") {
        localStorage.setItem(id, "1");
    }
}

// Create item checkout element
function createCheckoutElement(product) {
    const itemList = document.getElementById('item');
    let item = document.createElement("INPUT");
    item.setAttribute("type", "hidden");
    item.setAttribute("name", "item[]")
    item.setAttribute("value", product);
    itemList.appendChild(item);
}