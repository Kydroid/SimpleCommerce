import React from 'react';
import {Header} from "./components/header/header";
import {CategoryMenu} from "./components/category/CategoryMenu";
import './app.css';

function App() {
    return (
        <div id="app" className="App container-fluid">
            <Header/>
            <div className="container">
                <div className="row">
                    <CategoryMenu/>
                </div>
            </div>
        </div>
    );
}

export default App;
