import React, { Component } from "react";

import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import { useHistory } from "react-router-dom";
import List from "./List";
import authService from "../services/auth.service";
import { useEffect, useState } from "react";
import Button from 'react-bootstrap/Button';

let UserDetail = authService.getCurrentUser();

const addButtonStyle = {
  position: 'absolute',
  height: 'auto',
  /* right: 10%; */
  width: '10%',
  left: '2%'
}
const User = () => {

const [users, setUser] = useState([]);
let history = useHistory();

const addUser = () => {
  history.push("/adduser");
};

  useEffect(() => {
    document.title = "All Users";
  }, []);

  useEffect(() => {
    UserService.getUsers().then(
      (response) => {
        setUser(response.data);
        console.log(response);  
        console.log(response.data);
      },
      (error) => {
        setUser(
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
            error.message ||
            error.toString()
        );
        if (error.response && error.response.status === 401) {
          EventBus.dispatch("logout");
        }
      }
    );
  }, []);

  console.log("users",users);
  // console.log("length",users.length);
  console.log(typeof(users));
  return (
    <>
      <div>
        <h1 id="main-heading" style={{ textAlign: "center" }}>
          Manage Users
        </h1>
      </div>
     
        {/* <!-- <a id="add-product" href="@{/product/new}">Add New Product</a> --> */}
        <br />
        <br />
        <br />
        {UserDetail.role[0].authority == "ROLE_ADMIN" ? (
          <Button variant="primary" style={addButtonStyle}  id="add-user" type="button" onClick={addUser}>
            Add New User
          </Button>
        ) : (
          true
        )}
      

      <div>
        <table
          className="table-center"
          style={{ marginLeft: "auto", marginRight: "auto" }}
        >
          <thead>
            <tr>
              <th>User Id</th>
              <th>Username</th>
              <th>Role</th>
              <th>Email</th>
            </tr>
          </thead>

          {users.length > 0
            ?  users.map((item) => <List key={item.id} users={item} />)
            : "No User"}
        </table>
      </div>
    </>
  );
};

export default User;
