import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:9110/';

class UserService {

  getHomePage() {


    return axios.get(API_URL, { headers: authHeader() });
  }

  getProducts() {
    return axios.get(API_URL + 'product', { headers: authHeader() });
  }
  getProductById(event) {
    
    return axios.get(API_URL + `product/edit/${event.id}`, { headers: authHeader() });
  }

  getUsers() {
    console.log("inside GetUserPage");
    return axios.get(API_URL + 'user/list', { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL + 'admin', { headers: authHeader() });
  }

  DeleteProductbyId(event){
    console.log(event);
    return axios.delete(API_URL + `product/${event}`, { headers: authHeader() });
  }
}

export default new UserService();


// Now we define a service for accessing data in user.service.js: