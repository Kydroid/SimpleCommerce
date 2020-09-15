import React from "react";
import {ProductCard} from "./ProductCard";
import {Product} from "../../entities/product";

export class ProductListCard extends React.Component<any, any> {
    render() {
        return (
            <div className="row my-4">
                {this.props.products.map((product: Product) => <ProductCard key={product.id} product={product}/>)}
            </div>
        );
    }
}
