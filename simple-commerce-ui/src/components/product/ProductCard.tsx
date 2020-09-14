import React from "react";
import {MoneyFormatted} from "./MoneyFormatted";
import {Link} from "react-router-dom";

export class ProductCard extends React.Component<any, any> {
    render() {
        return (
            <div className="col-lg-4 col-md-6 mb-4">
                <div className="card h-100">
                    <Link to="#"><img className="card-img-top" src="http://placehold.it/700x400" alt=""/></Link>
                    <div className="card-body">
                        <h4 className="card-title">
                            <span>{this.props.product.title}</span>
                        </h4>
                        <h5><MoneyFormatted money={this.props.product.money}/></h5>
                        <p className="card-text">{this.props.product.description}</p>
                    </div>
                    <div className="card-footer">
                        <small className="text-muted">Ref: {this.props.product.ref}</small>
                    </div>
                </div>
            </div>
        );
    }
}
