document.addEventListener('DOMContentLoaded', () => {
    
    const form = document.getElementById("songForm");

    form.addEventListener("submit", function (event) {
        const songTitle = document.getElementById('songTitle').value;
        const songKeyword= document.getElementById('songKeyword').value;
        event.preventDefault(); // Prevent form from submitting
       
        alert("song title entered: " + songTitle);
        alert("keyword entered: " + songKeyword);

        document.getElementById("target").textContent = "Value from backend";
    
    });


    // DOM Elements
    const loginSection = document.getElementById('login-section');
    const productSection = document.getElementById('product-section');
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');
    const loginButton = document.getElementById('login-button');
    const logoutButton = document.getElementById('logout-button');
    const errorMessage = document.getElementById('error-message');
    const productList = document.getElementById('product-list');

    // Configuration
    const API_BASE_URL = 'http://localhost:8080/api/products';
    const SONGS_API_BASE_URL = 'http://localhost:8080/api/songs';
    let authHeader = ''; // This will store our Basic Auth header

    // --- Utility Functions ---

    // Function to display messages to the user
    function displayMessage(element, message, isError = true) {
        element.textContent = message;
        element.style.color = isError ? 'red' : 'green';
        setTimeout(() => {
            element.textContent = ''; // Clear message after some time
        }, 5000);
    }

    // Function to update UI visibility
    function updateUIVisibility(isLoggedIn) {
        if (isLoggedIn) {
            loginSection.style.display = 'none';
            productSection.style.display = 'block';
        } else {
            loginSection.style.display = 'block';
            productSection.style.display = 'none';
        }
    }

    // --- API Interaction Functions ---
       async function getNumberOfCharacters() {
        errorMessage.textContent = ''; // Clear previous errors
        productList.innerHTML = ''; // Clear existing list

        try {
            // Include the Authorization header for secured endpoint
            const response = await fetch(API_BASE_URL, {
                method: 'GET',
                headers: {
                    'Authorization': authHeader
                }
            });

            if (response.ok) {
                const data = await response.json();
                // Assuming data.content is the array of products from paginated response
                const products = data.content || data; // Handle both paginated and non-paginated (just in case)
                if (products.length === 0) {
                    productList.innerHTML = '<li>No products found.</li>';
                } else {
                    products.forEach(product => {
                        const listItem = document.createElement('li');
                        listItem.innerHTML = `
                            <span><strong>${product.name}</strong> - $${product.price.toFixed(2)} (${product.description})</span>
                        `;
                        productList.appendChild(listItem);
                    });
                }
            } else if (response.status === 401) {
                // Unauthorized - likely session expired or invalid credentials
                displayMessage(errorMessage, 'Unauthorized. Please log in again.', true);
                logout(); // Force logout
            } else {
                const errorData = await response.json();
                displayMessage(errorMessage, `Failed to fetch products: ${errorData.message || response.statusText}`, true);
            }
        } catch (error) {
            console.error('Error fetching products:', error);
            displayMessage(errorMessage, 'Network error. Could not connect to the server.', true);
        }
    }

    // Function to fetch products from the backend
    async function fetchProducts() {
        errorMessage.textContent = ''; // Clear previous errors
        productList.innerHTML = ''; // Clear existing list

        try {
            // Include the Authorization header for secured endpoint
            const response = await fetch(SONGS_API_BASE_URLAPI_BASE_URL, {
                method: 'GET',
                headers: {
                    'Authorization': authHeader
                }
            });

            if (response.ok) {
                const data = await response.json();
                // Assuming data.content is the array of products from paginated response
                const products = data.content || data; // Handle both paginated and non-paginated (just in case)
               
                console.log(products);
               
                if (products.length === 0) {
                    productList.innerHTML = '<li>No products found.</li>';
                } else {
                    products.forEach(product => {
                        const listItem = document.createElement('li');
                        listItem.innerHTML = `
                            <span><strong>${product.name}</strong> - $${product.price.toFixed(2)} (${product.description})</span>
                        `;
                        productList.appendChild(listItem);
                    });
                }
            } else if (response.status === 401) {
                // Unauthorized - likely session expired or invalid credentials
                displayMessage(errorMessage, 'Unauthorized. Please log in again.', true);
                logout(); // Force logout
            } else {
                const errorData = await response.json();
                displayMessage(errorMessage, `Failed to fetch products: ${errorData.message || response.statusText}`, true);
            }
        } catch (error) {
            console.error('Error fetching products:', error);
            displayMessage(errorMessage, 'Network error. Could not connect to the server.', true);
        }
    }

    // --- Authentication Functions ---

    // Handle user login
    async function login() {
        const username = usernameInput.value;
        const password = passwordInput.value;

        if (!username || !password) {
            displayMessage(errorMessage, 'Please enter both username and password.');
            return;
        }

        // Encode credentials for HTTP Basic Auth
        const credentials = btoa(`${username}:${password}`); // btoa() performs Base64 encoding
        authHeader = `Basic ${credentials}`;

        errorMessage.textContent = ''; // Clear previous error messages

        try {
            // Attempt to fetch products as a way to verify credentials
            const response = await fetch(API_BASE_URL, {
                method: 'GET',
                headers: {
                    'Authorization': authHeader
                }
            });

            if (response.ok) {
                // Login successful!
                displayMessage(errorMessage, 'Login successful!', false);
                updateUIVisibility(true);
                fetchProducts(); // Fetch products after successful login
            } else if (response.status === 401) {
                displayMessage(errorMessage, 'Invalid username or password.', true);
            } else {
                const errorDetails = await response.json();
                displayMessage(errorMessage, `Login failed: ${errorDetails.message || response.statusText}`, true);
            }
        } catch (error) {
            console.error('Error during login:', error);
            displayMessage(errorMessage, 'Network error during login. Could not connect to the server.', true);
        }
    }

    // Handle user logout
    function logout() {
        authHeader = ''; // Clear authentication header
        updateUIVisibility(false);
        usernameInput.value = 'user'; // Reset inputs for next login
        passwordInput.value = 'password';
        productList.innerHTML = ''; // Clear product list on logout
        displayMessage(errorMessage, 'Logged out successfully.', false);
    }

    // --- Event Listeners ---
    loginButton.addEventListener('click', login);
    logoutButton.addEventListener('click', logout);

    // Initial UI state
    updateUIVisibility(false); // Start with login section visible
});