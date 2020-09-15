import React from 'react';
import {CategoryMenu} from "./components/category/CategoryMenu";
import './app.css';
import {HomePage} from "./components/contents/HomePage";
import {Footer} from "./components/asides/Footer";
import {Header} from "./components/asides/Header";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import {ProductsByCategoryPage} from "./components/contents/ProductsByCategoryPage";

export class App extends React.Component<any, any> {
    render() {
        return (
            <Router>
                <div id="app" className="App">
                    <Header/>
                    <div className="container">
                        <div className="row">
                            <CategoryMenu/>
                            <Switch>
                                <Route exact path="/" component={HomePage}/>
                                <Route path="/categories/:categoryId" component={ProductsByCategoryPage}/>
                            </Switch>
                        </div>
                    </div>
                    <Footer/>
                </div>
            </Router>
        );
    };
}

export default App;
