import React from "react";
import {Slider} from "./Slider";
import {ProductListCard} from "../product/ProductListCard";
import Axios from "axios";

export class HomePage extends React.Component<any, any> {
    constructor(props: any) {
        super(props);
        this.state = {
            products: []
        };
    }

    componentDidMount() {
        Axios.get("http://localhost:8080/api/v1.0/products")
            .then(res => {
                const products = res.data;
                this.setState({
                    products: products
                })
            })
    }

    render() {
        return (
            <div className="col-lg-9">
                <Slider/>
                <ProductListCard products={this.state.products}/>
            </div>
        );
    }

}
