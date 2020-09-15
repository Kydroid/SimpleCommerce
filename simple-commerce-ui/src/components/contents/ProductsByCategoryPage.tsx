import React from "react";
import {ProductListCard} from "../product/ProductListCard";
import Axios from "axios";

export class ProductsByCategoryPage extends React.Component<any, any> {
    constructor(props: any) {
        super(props);
        this.state = {
            products: []
        };
    }

    componentDidMount() {
        this.loadProductsByCategory();
    }

    componentDidUpdate(prevProps: Readonly<any>, prevState: Readonly<any>, snapshot?: any) {
        if (this.props.match.params.categoryId !== prevProps.match.params.categoryId) {
            this.loadProductsByCategory();
        }
    }

    private loadProductsByCategory() {
        const categoryId = this.props.match.params.categoryId;
        Axios.get("http://localhost:8080/api/v1.0/categories/" + categoryId + "/products")
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
                <ProductListCard products={this.state.products}/>
            </div>
        );
    }
}
