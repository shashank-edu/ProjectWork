import React from "react";
import authService from "../services/auth.service";
import userService from "../services/user.service";
import { useEffect, useState } from "react";
import List from "./List";
import { useHistory } from "react-router-dom";
import Button from "react-bootstrap/Button";
import EntityForm, { message } from "./EntityForm";

let UserDetail = authService.getCurrentUser();

const addButtonStyle = {
  position: "absolute",
  height: "auto",
  /* right: 10%; */
  width: "10%",
  left: "2%",
};
const Products = () => {

  let [EditTypeValue, setEditTypeValue] = useState();

  const editItem = (e) => {
    // console.log(e.target.getAttribute("ProductObjectId"));
    setEditTypeValue(e.target.getAttribute("ProductObjectId"));
    history.push(`/EditProduct/${e.target.getAttribute("ProductObjectId")}`);
    // return( <EntityForm />);
  };
  const [products, setProduct] = useState([]);
  const deleteItem = (e) => {
    console.log(e.target.getAttribute("ProductObjectId"));
    userService.DeleteProductbyId(e.target.getAttribute("ProductObjectId"));
    // history.push("/product");
     
    // history.push(`/EditProduct/${e.target.getAttribute("ProductObjectId")}`);
    // return( <EntityForm />);
  };

  let history = useHistory();

  useEffect(() => {
    document.title = "All Products";
  }, []);

  
  console.log(products);

  useEffect(() => {
    userService.getProducts().then((result) => setProduct(result.data));
    console.log(products);
  }, products);

  const addProduct = () => {
    history.push("/addproduct");
  };

  const ProductList = () =>{
    return (
      <>
      <div>
      <h1 id="main-heading" style={{ textAlign: "center" }}>
        Manage Products
      </h1>
    </div>
    <div>
      <br />
      <br />
      <br />
      {UserDetail.role[0].authority == "ROLE_ADMIN" ||
      UserDetail.role[0].authority == "ROLE_MODERATOR" ? (
        <Button
          variant="primary"
          style={addButtonStyle}
          id="add-product"
          type="button"
          onClick={addProduct}
        >
          Add New Product
        </Button>
      ) : (
        true
      )}
    </div>

    <div>
      <table
        className="table-center"
        style={{ marginLeft: "auto", marginRight: "auto" }}
      >
        <thead>
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Batch No.</th>
          </tr>
        </thead>

        {/* {products.length > 0
          ? products.map((item) => <List key={item.id} products={item} /> )
          : "No Products"} */}
        {products.length > 0
          ? products.map((item) => (
              <tbody>
                <tr>
                  <td>{item.id}</td>
                  <td>{item.name}</td>
                  <td>{item.quantity}</td>
                  <td>{item.price}</td>
                  <td>{item.batchNo}</td>
                  <td>
                    <Button
                      id="edit-link"
                      onClick={editItem}
                      ProductObjectId={item.id}
                      buttontypevalue="product"
                      variant="primary"
                      style={{ marginRight: "5px" }}
                    >
                      Edit
                    </Button>
                    <Button
                      id="delete-link"
                      onClick={deleteItem}
                      ProductObjectId={item.id}
                      variant="danger"
                      style={{ marginLeft: "5px" }}
                    >
                      Delete
                    </Button>
                  </td>
                </tr>
              </tbody>
            ))
          : "No Products"}
      </table>
    </div>
      </>
    );
  }

  return (
    <>
    {/* {EditTypeValue ? <EntityForm ProductId={EditTypeValue} EntityProduct="EditProduct"/> : <ProductList/> } */}
    <ProductList/>
    </>
  );
};

export default Products;
