import React from "react";
import axios from "axios";

class ProductService {
    fetchProduct(){
        return axios
      .get(API_URL + "token", {
        username,
        password
      })
      .then(response => {
        if (response) {
          console.log(response.data);
          localStorage.setItem("user", JSON.stringify(response.data));
          console.log(localStorage);
        }

        return response.data;
      });
    }
}
