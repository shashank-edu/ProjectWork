import React, { Component, useState, useEffect } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import { email } from "validator";
import { useHistory , useParams } from "react-router-dom";

import AuthService from "../services/auth.service";
import { toast } from "react-toastify";
import Axios from "axios";
import userService from "../services/user.service";

const required = (value) => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

const vprice = (value) => {
  console.log("value :", typeof value);
  if (!typeof value === "number" || !(value >= 50 && value < 10000)) {
    return (
      <div className="alert alert-danger" role="alert">
        The price must be between 50 and 10000 value.
      </div>
    );
  }
};

const vProductName = (value) => {
  if (value.length < 3 || value.length > 20) {
    return (
      <div className="alert alert-danger" role="alert">
        The ProductName must be between 3 and 20 characters.
      </div>
    );
  }
};
const vquantity = (value) => {
  if (!typeof value === "number" || !(value >= 1 && value < 1000)) {
    return (
      <div className="alert alert-danger" role="alert">
        The Quantity must be between 1 and 100 value.
      </div>
    );
  }
};

const vbatchNo = (value) => {
  if (value.length < 3 || value.length > 10) {
    return (
      <div className="alert alert-danger" role="alert">
        The batchNo must be between 3 and 10 characters.
      </div>
    );
  }
};



const EditProduct = (event) => {
  // console.log(e);

  let history = useHistory();
  const [Product, setProduct] = useState([]);
  const [ProductName, setProductName] = useState();
  const [quantity, setquantity] = useState();
  const [price, setprice] = useState();
  const [batchNo, setbatchNo] = useState();
  const [success, setSuccess] = useState();
  const [message, setMessage] = useState();
  const [checkButton, setcheckButton] = useState();
  const [formValidation, setformValidation] = useState();

const id = useParams();

const load = (event) => {
setProductName(event.name);
setquantity(event.quantity);
setprice(event.price);
setbatchNo(event.batchNo);
} 

useEffect(() => {
    userService.getProductById(id).then((result) => {setProduct(result.data);  load(result.data);} );
    // load(result.data);
  }, []);
  
// console.log(Product);
// console.log(Product.name);
// useEffect((e) => {
 
//     load(Product);
// },[])


  // console.log("ProductId",event.ProductId);
  // console.log("EntityProduct",event.EntityProduct );
  // if(event.EntityProduct === "EditProduct"){
  //     document.title = "Edit Product";
  //       userService.getProductById(event.ProductId).then((result) => setProduct(result.data));
  //       console.log(Product);
     
  //     // userService.getProductById(event.ProductId)
      
  // }else{
  //   document.title = "Add  Product";
  // }
 
  useEffect(() => {
    document.title = "Add  Product";
    
  }, []);

  const onChangeProductName = (e) => {
    setProductName(e.target.value);
  };

  const onChangequantity = (e) => {
    setquantity(e.target.value);
  };

  const onChangeprice = (e) => {
    setprice(e.target.value);
  };

  const onChangebatchNo = (e) => {
    setbatchNo(e.target.value);
  };

  const handleSave = (e) => {
    e.preventDefault();

    // this.setState({
    //   message: "",
    //   successful: false,
    // });
    setSuccess(false);
    setMessage("");

    formValidation.validateAll();

    if (checkButton.context._errors.length === 0) {
      AuthService.saveProduct(id,ProductName, quantity, price, batchNo).then(
        (response) => {
          setSuccess(true);
          setMessage(response.data);
          toast.success(response.data);
          setTimeout(() => {
            history.push("/product");
          }, 2000);
        },
        (error) => {
          const resMessage =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();

          setSuccess(false);
          setMessage(resMessage);
        }
      );
    }
  };

  return (
    <div className="col-md-12">
      <div className="card card-container">
        {/* <img
            src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
            alt="profile-img"
            className="profile-img-card"
          /> */}

        <Form
          onSubmit={handleSave}
          ref={(c) => {
            setformValidation(c);
          }}
        >
          {!success && (
            <div>
              <div className="form-group">
                <label htmlFor="ProductName">Product Name</label>
                <Input
                  type="text"
                  className="form-control"
                  name="ProductName"
                  value={ProductName}
                  onChange={onChangeProductName}
                  validations={[required, vProductName]}
                />
              </div>

              <div className="form-group">
                <label htmlFor="quantity">Quantity</label>
                <Input
                  type="number"
                  className="form-control"
                  name="quantity"
                  value={quantity}
                  onChange={onChangequantity}
                  validations={[required, vquantity]}
                />
              </div>

              <div className="form-group">
                <label htmlFor="price">Price</label>
                <Input
                  type="number"
                  className="form-control"
                  name="price"
                  value={price}
                  onChange={onChangeprice}
                  validations={[required, vprice]}
                />
              </div>

              <div className="form-group">
                <label htmlFor="batchNo">Batch No.</label>
                <Input
                  type="batchNo"
                  className="form-control"
                  name="batchNo"
                  value={batchNo}
                  onChange={onChangebatchNo}
                  validations={[required, vbatchNo]}
                />
              </div>

              <div className="form-group">
                <button className="btn btn-primary btn-block">Save</button>
              </div>
            </div>
          )}

          {message && (
            <div className="form-group">
              <div
                className={
                  success ? "alert alert-success" : "alert alert-danger"
                }
                role="alert"
              >
                {message}
              </div>
            </div>
          )}
          <CheckButton
            style={{ display: "none" }}
            ref={(c) => {
              setcheckButton(c);
            }}
          />
        </Form>
      </div>
    </div>
  );
};

export default EditProduct;
