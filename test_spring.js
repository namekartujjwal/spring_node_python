const axios = require('axios');

const STRING_URL = 'http://localhost:8080/';

async function callSpring(){
    try{
        console.log("Connecting to Spring Boot...");
        const response = await axios.get(STRING_URL);
        console.log("=== Node.js received response ===");
        console.log("Status Code : ", response.status);
        console.log("Response Data : ", response.data);
    }
    catch(error){
        console.error("Connection failed : ", error.message);
    }
}

callSpring();