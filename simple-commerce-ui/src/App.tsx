import React from 'react';
import {CategoryMenu} from "./components/category/CategoryMenu";
import './app.css';
import {HomePage} from "./components/contents/HomePage";
import {Footer} from "./components/asides/Footer";
import {Header} from "./components/asides/Header";

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
