import React from "react";
import {Slider} from "./Slider";
import {ProductCard} from "../product/ProductCard";

export class HomePage extends React.Component<any, any> {
    render() {
        return (
            <div className="col-lg-9">
                <Slider/>
                <div className="row">
                    <ProductCard/>
                    <ProductCard/>
                    <ProductCard/>
                    <ProductCard/>
                    <ProductCard/>
                    <ProductCard/>
                </div>
            </div>
        );
    }

}
