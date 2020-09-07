import React from 'react';
import {Header} from "./components/header/header";
import {CategoryMenu} from "./components/category/CategoryMenu";
import './app.css';
import {HomePage} from "./components/pages/HomePage";
import {Footer} from "./components/pages/Footer";

function App() {
    return (
        <div id="app" className="App">
            <Header/>
            <div className="container">
                <div className="row">
                    <CategoryMenu/>
                    <HomePage/>
                </div>
            </div>
            <Footer/>
        </div>
    );
}

export default App;
