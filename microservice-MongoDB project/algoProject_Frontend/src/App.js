import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
        <navbar>
        <div className="navbar">
          <div className="logo">
            <img src="img/logo.png" alt="" />
          </div>
          <div className="search">
          <div class="wrapper">
                <div class="text-box">
                        <input type="text" name="" placeholder="Type..." id="search"/>
                        <input type="submit" name="" value="search"/>
                </div>
                </div>
          </div>
        </div>
        </navbar>
        <left_side>
            <div className="main-bg">
              <div className="left">
                <div className="headline">
                  <h1>Category</h1>
                </div>
                <div className="content">
                  <h1>Electronica</h1>
                  <h2>Laptop</h2>
                  <h2>Mobiles</h2>
                  <h1>Clothing</h1>
                  <h1>Home & Application</h1>
                </div>
                <hr/>
                <div className="price">
                  <h1>Price Range</h1>
                  <input type="number" placeholder="0"/>
                  <input type="number" placeholder="100000"/>
                </div>
              </div>
            <div className="right">
              <div className="card">
                <div className="photo">
                  <img src="#" alt="Photo" />
                </div>
                <h1>HP PAVILLION V8</h1>
                <h1>98,000 Rs</h1>
                <hr/>
                <h2>Ram : 8GB</h2>
                <h2>Hard Disk : 500GB</h2>
                <h2>Processor : intel i7</h2>
                <h2>Core : Dual Core</h2>
                <button type="submit" id="buy">Buy Now</button>
                <br/><br/>
                <button type="submit" id="add">Add Card</button>
              </div>
            </div>
            </div>
        </left_side>
    </div>
  );
}

export default App;
