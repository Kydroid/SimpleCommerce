import React from "react";
import Axios from "axios";
import {Category} from "../../entities/category";
import {Link} from "react-router-dom";

export class CategoryMenu extends React.Component<any, any> {
    constructor(props: any) {
        super(props);
        this.state = {
            categories: []
        };
    }

    componentDidMount() {
        Axios.get("http://localhost:8080/api/v1.0/categories")
            .then(res => {
                const categories = res.data;
                this.setState({
                    categories: categories
                })
            })
    }

    render() {
        return (
            <aside id="category-menu" className="col-lg-3">
                <h1 className="my-4">Shop Name</h1>
                <div className="list-group">
                    {
                        this.state.categories.map((category: Category) =>
                            <Link to={"/categories/" + category.id} key={category.id} className="list-group-item">
                                {category.title}
                            </Link>)
                    }
                </div>
            </aside>
        );
    }

}
