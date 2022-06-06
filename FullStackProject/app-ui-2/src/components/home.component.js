
import { Component, useState } from "react";
import { Link } from "react-router-dom";
import UserService from "../services/user.service";
import centerImage from "../static/centerImage.jpg";
import background1 from "../static/background1.png";
import { ScrollMenu, VisibilityContext } from "react-horizontal-scrolling-menu";
import Arrow from "react-arrows";
import "react-responsive-carousel/lib/styles/carousel.min.css"; // requires a loader
import { Carousel } from "react-responsive-carousel";
import './home.css';
import AuthService from '../services/auth.service';
var React = require("react");




var currentUser  = "";
export default class Home extends Component {
  
  constructor(props) {
    super(props);

    this.state = {
      content: "",
    };
  }

  componentDidMount() {
    UserService.getHomePage().then(
      (response) => {
        this.setState({
          content: response.data,
        });
        console.log("inside response block 1",response.data);
        console.log("inside response block 2",response);
      },
      (error) => {
        this.setState({
          content:
            (error.response && error.response.data) ||
            error.message ||
            error.toString(),
        });
        console.log("inside error block ",error.response);
        console.log("inside error block 2",error);
        currentUser = 'error';
      }
    );
  }


  render() {
    const { content } = this.state.content;
    
    console.log("inside render", AuthService.getCurrentUser());
    
    const CorouselImageStyle={
      marginBottom: '10%'
    }
    return (
    
      <>
        {/* style={{width : '100%', height : '40%'  }} */}
      {AuthService.getCurrentUser() ? (
          <Carousel className="Corousel-image" style={CorouselImageStyle}>
          <div >
            <img src="https://picsum.photos/1000/650"/>
            <p className="legend">Legend 1</p>
          </div>
          <div>
            <img src="https://picsum.photos/1001/650" />
            <p className="legend">Legend 2</p>
          </div>
          <div>
            <img src="https://picsum.photos/1003/650" />
            <p className="legend">Legend 2</p>
          </div>
          <a href="http://www.google.com" target="_shashank">
          <img src="https://picsum.photos/1002/650"/>
            <p className="legend">Legend 3</p>
          </a>
          
        
        </Carousel>
        
      ): (
        this.props.history.push("/")   
      )}
      
      </>
       
      
    );
  }
}
