import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AuthService from "./services/auth.service";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import Login from "./components/login.component";
import Register from "./components/register.component";
import Home from "./components/home.component";
import Profile from "./components/profile.component";
import User from "./components/user.component";
import BoardModerator from "./components/board-moderator.component";
import BoardAdmin from "./components/board-admin.component";
import Footer from "./components/Footer.component";
// import AuthVerify from "./common/auth-verify";
import EventBus from "./common/EventBus";
import Products from "./components/Products.component";
import Button from 'react-bootstrap/Button';
import EntityForm from "./components/EntityForm";
import NotFound from "./components/NotFound";
import EditProduct from "./components/EditProduct";
import authService from "./services/auth.service";


let UserDetail = authService.getCurrentUser();
class App extends Component {
  constructor(props) {
    super(props);
    this.logOut = this.logOut.bind(this);

    this.state = {
      currentUser: undefined,
    };
  }

  componentDidMount() {
    const user = AuthService.getCurrentUser();

    if (user) {
      console.log(user);
      this.setState({
        currentUser: user,
      });
    }

    EventBus.on("logout", () => {
      this.logOut();
    });
  }

  componentWillUnmount() {
    EventBus.remove("logout");
  }

  logOut() {
    AuthService.logout();
    this.setState({
      showModeratorBoard: false,
      showAdminBoard: false,
      currentUser: undefined,
    });
  }

  render() {
    const { currentUser } = this.state;
    const notify = () =>
      toast.success("🦄 Wow so easy!", {
        position: "top-center",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });

    return (
      <>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <Link to={"/"} className="navbar-brand">
            ApanaMart
          </Link>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/home"} className="nav-link">
                Home
              </Link>
            </li>

            {currentUser && (
              <>
              {UserDetail.role[0].authority == "ROLE_ADMIN" ? 
              <li className="nav-item">
              <Link to={"/user"} className="nav-link">
                User
              </Link>
            </li> : <div></div>}
               
              <li className="nav-item">
                <Link to={"/product"} className="nav-link">
                  Products
                </Link>
              </li>
              </>

            )}
          </div>

          {currentUser ? (
            <div className="navbar-nav ml-auto">
              <li className="nav-item">
                {/* <span className="nav-link">{currentUser.username}</span> */}
                <Link className="nav-link">
                  {currentUser.username}
                </Link>
              </li>
              <li className="nav-item">
                <a href="/login" className="nav-link" onClick={this.logOut}>
                  LogOut
                </a>
              </li>
            </div>
          ) : (
            <div className="navbar-nav ml-auto">
              <li className="nav-item">
                <Link to={"/login"} className="nav-link">
                  Login
                </Link>
              </li>

              <li className="nav-item">
                <Link to={"/register"} className="nav-link">
                  Sign Up
                </Link>
              </li>
            </div>
          )}
        </nav>

        <div className="container mt-3">
          <Switch>
            <Route exact path={["/", "/login"]} component={Login} />
            (currentUser ?
            <Route exact path={"/home"} component={Home} />
            <Route exact path="/register" component={Register} />
            <Route exact path="/profile" component={Profile} />
            <Route exact  path="/user" component={User} />
            <Route exact  path="/product" component={Products} />
            <Route exact  path="/addproduct" component={EntityForm} />
            <Route exact  path="/adduser" component={BoardAdmin} />
            <Route exact  path="/editProduct/:id" component={EditProduct} />
            <Route  component={NotFound} />
            : <Route exact path={"*"} component={Login} />)
          </Switch>
        </div>
        <ToastContainer />
        {/*<AuthVerify logOut={this.logOut}/> */}
        {currentUser ? <Footer /> : true}
      </>
    );
  }
}

export default App;

// Add Navbar and define Routes
// Now we add a navigation bar in App component. This is the root container for our application.
// The navbar dynamically changes by login status and current User’s roles.

// Home: always
// Login & Sign Up: if user hasn’t signed in yet
// User: AuthService.getCurrentUser() returns a value
// Board Moderator: roles includes ROLE_MODERATOR
// Board Admin: roles includes ROLE_ADMIN
// src/App.js
