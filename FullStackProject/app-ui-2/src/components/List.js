import React, { useEffect, useState } from "react";
import Button from 'react-bootstrap/Button';
import EntityForm from "./EntityForm";
import { useHistory } from "react-router-dom";

let ExportButtonTypeFunction='';  




const List = ({ products, users }) => {
  const deleteItem = () => {

  };
  let [EditTypeValue,setEditTypeValue]=useState();
  let history = useHistory();
  
  const editItem = (e) => {
      console.log(e.target.getAttribute('buttontypevalue'));
      setEditTypeValue(e.target.getAttribute('buttontypevalue'));
      // history.replace('/addproduct');
      // return( <EntityForm />);
  };
  return (
    <tbody>
      {products ? (
        <tr>
          <td>{products.id}</td>
          <td>{products.name}</td>
          <td>{products.quantity}</td>
          <td>{products.price}</td>
          <td>{products.batchNo}</td>
          <td>
            <Button
              id="edit-link"
              onClick={editItem}
              // onClick={}
              buttontypevalue="product"
              variant="primary"
              style={{ marginRight: "5px" }}
            >
              Edit
            </Button>
            <Button
              id="delete-link"
              onClick={deleteItem}
              variant="danger"
              style={{ marginLeft: "5px" }}
            >
              Delete
            </Button>
          </td>
        </tr>
      ) : (
        <tr>
          <td>{users.userId}</td>
          <td>{users.username}</td>
          <td>{users.role}</td>
          <td>{users.email}</td>
          <td>
            <Button
              id="edit-link"
              onClick={editItem}
              buttontypevalue="user"
              variant="primary"
              style={{ marginRight: "5px" }}
            >
              Edit
            </Button>
            <Button
              id="delete-link"
              onClick={deleteItem}
              variant="danger"
              style={{ marginLeft: "5px" }}
            >
              Delete
            </Button>
            {/* <a id="edit-link" href="@{'/product/edit/' + ${prop.id}}">
              Edit
            </a>
            <a id="delete-link" href="@{'/product/delete/' + ${prop.id}}">
              Delete
            </a> */}
          </td>
        </tr>
      )}
    </tbody>
  );
};

export default List;
