import React, { useState } from "react";
import ReactDOM from "react-dom";
import { BrowserRouter } from "react-router-dom";

import App from "./App";



// ReactDOM.render( kya deekhana hai , kaha deekhana hai , call back function );
// jsx -> javascript extension or javascript XML


ReactDOM.render(
  <BrowserRouter>
    <App />
  </BrowserRouter>,
  document.getElementById("root")
);

// var h1  = document.createElement("h1");
// h1.innerHTML = "This is Shashank's world";
// document.getElementById("root").appendChild(h1);

// In react v16 it's possible for render()
// to return an array of elements.
// [<h1></h1>,<div></div>,<p></p>]
// and -> 
{/* <React.Fragment>
  <h1></h1>
  <p></p>
  <h3></h3>
</React.Fragment> */}     //same as  =>

//  <>
//    <h1></h1>
//    <p></p>
//    <h3></h3>
//  </>; 


// template literals =>
// how to write combine string and javascript  by -> ``
// console.log(`my name is shashank ${variable}`);


// map =>
// arrayName.map(function(currentValue,index,array),thisValue)


// object destructuring =>
// let {a,b,c} =  props  => this is object destructuring

// Hooks =>
// React use can't be used inside class as it is made only to eliminate class and use of functional component
// this should also be used on the top level of react function


// useState =>
// it is used to redender the update value of object on HTML page
// current Data , updated Data  = initial Data
// const [count , setCount ] = useState(0)   => object destructing happening 
// what ever value we gave in useState(0) -> then this value assign to count 
// what ever the value pass on setCount(100) -> it will become the updated value
