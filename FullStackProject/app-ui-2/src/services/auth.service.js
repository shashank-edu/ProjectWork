import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:9110/";

class AuthService {
  login(username, password) {
    return axios
      .post(API_URL + "token", {
        username,
        password,
      })
      .then((response) => {
        if (response) {
          console.log(response.data);
          localStorage.setItem("user", JSON.stringify(response.data));
          console.log(localStorage);
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("user");
  }

  registerUser(username, email, password, role) {
    return axios.post(API_URL + "user/new", {
      username,
      password,
      email,
      role,
    });
  }

  registerProduct(name, quantity, price, batchNo, id = 0) {

    return axios.post(
      API_URL + "product/save",{
        id,
        name,
        quantity,
        price,
        batchNo
      },
      { headers: authHeader() }
    );
  }

  saveProduct(event,name, quantity, price, batchNo) {
// console.log(
//   event,ProductName, quantity, price, batchNo
// )
    return axios.put(
      API_URL + `product/edit/${event.id}`,{
        event,
        name,
        quantity,
        price,
        batchNo
      },
      { headers: authHeader() }
    );
  }
  getCurrentUser() {
    return JSON.parse(localStorage.getItem("user"));
  }
}

export default new AuthService();

// Authentication service
// The service uses Axios for HTTP requests and Local Storage for user information & JWT.
// It provides following important methods:

// login(): POST {username, password} & save JWT to Local Storage
// logout(): remove JWT from Local Storage
// register(): POST {username, email, password}
// getCurrentUser(): get stored user information (including JWT)
